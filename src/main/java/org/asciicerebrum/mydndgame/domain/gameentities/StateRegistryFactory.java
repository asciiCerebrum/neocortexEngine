package org.asciicerebrum.mydndgame.domain.gameentities;

import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.gameentities.StateRegistry.StateParticle;
import org.asciicerebrum.mydndgame.domain.gameentities.StateRegistry.StateValueType;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupProperty;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class StateRegistryFactory implements EntityFactory<StateRegistry> {

    private Campaign campaign;

    private ApplicationContext context;

    @Override
    public final StateRegistry newEntity(final EntitySetup<StateRegistry> setup,
            final Reassignments reassignments) {

        StateRegistry stateReg = new StateRegistry();

        for (EntitySetup entrySetup
                : setup.getPropertySetups(SetupProperty.STATE_REGISTRY_ENTRY)) {
            this.addSingleState(stateReg, entrySetup, reassignments);
        }

        return stateReg;
    }

    final void addSingleState(final StateRegistry stateReg,
            final EntitySetup entrySetup, final Reassignments reassignments) {

        if (!entrySetup.isSetupComplete()) {
            //TODO log warning
            return;
        }

        final StateParticle particle
                = StateParticle.valueOf(entrySetup.getProperty(
                                SetupProperty.STATE_REGISTRY_PARTICLE));

        UniqueEntity contextObject = null;
        final String contextObjectId
                = entrySetup.getProperty(
                        SetupProperty.STATE_REGISTRY_CONTEXT_OBJECT_ID);
        if (StringUtils.isNotBlank(contextObjectId)) {
            contextObject = this.campaign.getEntityById(
                    new UniqueId(contextObjectId));
        }
        if (reassignments != null
                && contextObject == null
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
                stateValueType.parseToType(stateValueString,
                        this.campaign, this.context));
    }

    public final void reAssign(final EntitySetup<StateRegistry> setup,
            final StateRegistry entity) {

        this.addSingleState(entity, setup, null);
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

}
