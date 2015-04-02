package org.asciicerebrum.mydndgame.domain.factories;

import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.domain.ruleentities.ConditionType;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Condition;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class ConditionFactory implements EntityFactory<Condition> {

    /**
     * Factory for the world date.
     */
    private EntityFactory<WorldDate> worldDateFactory;

    @Override
    public final Condition newEntity(final EntitySetup setup) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the condition "
                    + " is not complete.");
        }

        final Condition condition = new Condition();

        final String causeEntityId = setup.getProperty(
                SetupProperty.CONDITION_CAUSE_ENTITY);

        if (StringUtils.isNotBlank(causeEntityId)) {
            condition.setCauseEntityId(new UniqueId(causeEntityId));
        }

        condition.setConditionType(ApplicationContextProvider
                .getApplicationContext().getBean(
                        setup.getProperty(SetupProperty.CONDITION_TYPE),
                        ConditionType.class));
        condition.setExpiryDate(this.getWorldDateFactory().newEntity(
                setup.getPropertySetup(SetupProperty.CONDITION_EXPIRY_DATE)));
        condition.setStartingDate(this.getWorldDateFactory().newEntity(
                setup.getPropertySetup(SetupProperty.CONDITION_START_DATE)));

        return condition;
    }

    /**
     * @param worldDateFactoryInput the worldDateFactory to set
     */
    public final void setWorldDateFactory(
            final EntityFactory<WorldDate> worldDateFactoryInput) {
        this.worldDateFactory = worldDateFactoryInput;
    }

    /**
     * @return the worldDateFactory
     */
    public final EntityFactory<WorldDate> getWorldDateFactory() {
        return worldDateFactory;
    }

}
