package org.asciicerebrum.mydndgame.domain.factories;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry.StateParticle;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry.StateValueType;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class StateRegistryFactory implements EntityFactory<StateRegistry> {

    @Override
    public final StateRegistry newEntity(final EntitySetup setup,
            final Campaign campaign) {

        StateRegistry stateReg = new StateRegistry();

        final List<EntitySetup> registryEntrySetups
                = setup.getPropertySetups(SetupProperty.STATE_REGISTRY_ENTRY);
        if (registryEntrySetups != null) {
            for (EntitySetup entrySetup : registryEntrySetups) {
                this.addSingleState(stateReg, entrySetup, campaign);
            }
        }

        return stateReg;
    }

    /**
     * Builds up a single state for the state registry.
     *
     * @param stateReg the state registry to put the state in.
     * @param entrySetup the setup for the registry entry.
     * @param campaign the campaign as the central entity map.
     */
    final void addSingleState(final StateRegistry stateReg,
            final EntitySetup entrySetup, final Campaign campaign) {

        if (!entrySetup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the state "
                    + " registry entry is not complete.");
        }

        final StateParticle particle
                = StateParticle.valueOf(entrySetup.getProperty(
                                SetupProperty.STATE_REGISTRY_PARTICLE));

        UniqueEntity contextObject = null;
        final String contextObjectId = entrySetup.getProperty(
                SetupProperty.STATE_REGISTRY_CONTEXT_OBJECT_ID);
        if (StringUtils.isNotBlank(contextObjectId)) {
            contextObject = campaign.getEntityById(
                    new UniqueId(contextObjectId));
        }
        if (contextObject == null
                && StringUtils.isNotBlank(contextObjectId)) {
            // something did not work - reassigning for later resolution
            //TODO log this with info
            campaign.addReassignmentEntry(this, entrySetup, stateReg);
            return;
        }

        final StateValueType stateValueType
                = StateValueType.valueOf(entrySetup.getProperty(
                                SetupProperty.STATE_REGISTRY_VALUE_TYPE));
        final String stateValueString
                = entrySetup.getProperty(
                        SetupProperty.STATE_REGISTRY_VALUE);

        stateReg.putState(particle, contextObject,
                stateValueType.parseToType(stateValueString, campaign,
                        ApplicationContextProvider
                        .getApplicationContext()));
    }

    @Override
    public final void reAssign(final EntitySetup setup,
            final StateRegistry entity, final Campaign campaign) {

        this.addSingleState(entity, setup, campaign);
    }

}
