package org.asciicerebrum.neocortexengine.domain.factories;

import org.apache.commons.lang3.StringUtils;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ConditionType;
import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.domain.setup.SetupProperty;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.Condition;
import org.asciicerebrum.neocortexengine.infrastructure.ApplicationContextProvider;

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
