package org.asciicerebrum.mydndgame.domain.setup;

/**
 *
 * @author species8472
 */
public class ConditionSetup extends AbstractEntitySetup {

    /**
     * The required properties.
     */
    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.CONDITION_START_DATE,
                SetupProperty.CONDITION_EXPIRY_DATE,
                SetupProperty.CONDITION_TYPE};

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    /**
     * @param conditionType the condition type.
     */
    public final void setConditionType(final String conditionType) {
        this.getSingleProperties().put(SetupProperty.CONDITION_TYPE,
                conditionType);
    }

    /**
     * @param causeEntity the causing entity.
     */
    public final void setCauseEntity(final String causeEntity) {
        this.getSingleProperties().put(SetupProperty.CONDITION_CAUSE_ENTITY,
                causeEntity);
    }

    /**
     * @param setup the starting world date.
     */
    public final void setStartingDate(final WorldDateSetup setup) {
        this.getSingleSetup().put(SetupProperty.CONDITION_START_DATE,
                (EntitySetup) setup);
    }

    /**
     * @param setup the expiry world date.
     */
    public final void setExpiryDate(final WorldDateSetup setup) {
        this.getSingleSetup().put(SetupProperty.CONDITION_EXPIRY_DATE,
                (EntitySetup) setup);
    }

}
