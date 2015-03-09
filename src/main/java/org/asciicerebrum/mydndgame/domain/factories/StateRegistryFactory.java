package org.asciicerebrum.mydndgame.domain.factories;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry.StateParticle;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry.StateValueType;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class StateRegistryFactory implements EntityFactory<StateRegistry> {

    /**
     * The campaign holding the basic data of encounters and characters.
     */
    private Campaign campaign;

    /**
     * The spring application context to find beans.
     */
    private ApplicationContext context;

    @Override
    public final StateRegistry newEntity(final EntitySetup setup,
            final Reassignments reassignments) {

        StateRegistry stateReg = new StateRegistry();

        final List<EntitySetup> registryEntrySetups
                = setup.getPropertySetups(SetupProperty.STATE_REGISTRY_ENTRY);
        if (registryEntrySetups != null) {
            for (EntitySetup entrySetup : registryEntrySetups) {
                this.addSingleState(stateReg, entrySetup, reassignments);
            }
        }

        return stateReg;
    }

    /**
     * Builds up a single state for the state registry.
     *
     * @param stateReg the state registry to put the state in.
     * @param entrySetup the setup for the registry entry.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void addSingleState(final StateRegistry stateReg,
            final EntitySetup entrySetup, final Reassignments reassignments) {

        if (!entrySetup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the state "
                    + " registry entry is not complete.");
        }

        final StateParticle particle
                = StateParticle.valueOf(entrySetup.getProperty(
                                SetupProperty.STATE_REGISTRY_PARTICLE));

        UniqueEntity contextObject = null;
        final String contextObjectId
                = entrySetup.getProperty(
                        SetupProperty.STATE_REGISTRY_CONTEXT_OBJECT_ID);
        if (StringUtils.isNotBlank(contextObjectId)) {
            contextObject = this.getCampaign().getEntityById(
                    new UniqueId(contextObjectId));
        }
        if (contextObject == null
                && StringUtils.isNotBlank(contextObjectId)) {
            // something did not work - reassigning for later resolution
            //TODO log this with info
            reassignments.addEntry(this, entrySetup, stateReg);
            return;
        }

        final StateValueType stateValueType
                = StateValueType.valueOf(entrySetup.getProperty(
                                SetupProperty.STATE_REGISTRY_VALUE_TYPE));
        final String stateValueString
                = entrySetup.getProperty(
                        SetupProperty.STATE_REGISTRY_VALUE);

        stateReg.putState(particle, contextObject,
                stateValueType.parseToType(stateValueString, this.getCampaign(),
                        this.getContext()));
    }

    @Override
    public final void reAssign(final EntitySetup setup,
            final StateRegistry entity, final Reassignments reassignments) {

        this.addSingleState(entity, setup, reassignments);
    }

    /**
     * @param campaignInput the campaign to set
     */
    public final void setCampaign(final Campaign campaignInput) {
        this.campaign = campaignInput;
    }

    /**
     * @param contextInput the context to set
     */
    public final void setContext(final ApplicationContext contextInput) {
        this.context = contextInput;
    }

    /**
     * @return the campaign
     */
    public final Campaign getCampaign() {
        return campaign;
    }

    /**
     * @return the context
     */
    public final ApplicationContext getContext() {
        return context;
    }

}
