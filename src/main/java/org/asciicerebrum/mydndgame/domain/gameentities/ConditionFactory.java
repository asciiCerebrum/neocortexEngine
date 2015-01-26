package org.asciicerebrum.mydndgame.domain.gameentities;

import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.domain.core.attribution.ConditionType;
import org.asciicerebrum.mydndgame.domain.core.attribution.WorldDate;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupProperty;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class ConditionFactory implements EntityFactory<Condition> {

    private Campaign campaign;

    private ApplicationContext context;

    private EntityFactory<WorldDate> worldDateFactory;

    @Override
    public Condition newEntity(final EntitySetup<Condition> setup,
            final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the condition "
                    + " is not complete.");
        }

        Condition condition = new Condition();

        final String causeEntityId = setup.getProperty(
                SetupProperty.CONDITION_CAUSE_ENTITY);
        UniqueEntity uEntity = null;
        if (StringUtils.isNotBlank(causeEntityId)) {
            uEntity = this.campaign.getEntityById(new UniqueId(causeEntityId));
            condition.setCauseEntity(uEntity);
        }
        if (StringUtils.isNotBlank(causeEntityId) && uEntity == null) {
            // add to list of reassignments to reassign later when cyclic
            // dependencies are resolvable
            reassignments.addEntry(this, setup, condition);
        }

        condition.setConditionType(this.context.getBean(
                setup.getProperty(SetupProperty.CONDITION_TYPE),
                ConditionType.class));
        condition.setExpiryDate(this.worldDateFactory.newEntity(
                setup.getPropertySetup(SetupProperty.CONDITION_EXPIRY_DATE),
                reassignments));
        condition.setStartingDate(this.worldDateFactory.newEntity(
                setup.getPropertySetup(SetupProperty.CONDITION_START_DATE),
                reassignments));

        return condition;
    }

    @Override
    public void reAssign(EntitySetup<Condition> setup, Condition entity) {
        entity.setCauseEntity(this.campaign.getEntityById(
                new UniqueId(setup.getProperty(
                                SetupProperty.CONDITION_CAUSE_ENTITY))));
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
     * @param worldDateFactoryInput the worldDateFactory to set
     */
    public final void setWorldDateFactory(
            final EntityFactory<WorldDate> worldDateFactoryInput) {
        this.worldDateFactory = worldDateFactoryInput;
    }

}
