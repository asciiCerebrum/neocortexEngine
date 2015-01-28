package org.asciicerebrum.mydndgame.domain.gameentities.setup;

/**
 *
 * @author species8472
 */
public class ConditionSetup extends AbstractEntitySetup {

    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.CONDITION_START_DATE,
                SetupProperty.CONDITION_EXPIRY_DATE,
                SetupProperty.CONDITION_TYPE};

    @Override
    public boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    public final void setConditionType(final String conditionType) {
        this.singleProperties.put(SetupProperty.CONDITION_TYPE, conditionType);
    }

    public final void setCauseEntity(final String causeEntity) {
        this.singleProperties.put(SetupProperty.CONDITION_CAUSE_ENTITY,
                causeEntity);
    }

    public final void setStartingDate(final WorldDateSetup setup) {
        this.singleSetup.put(SetupProperty.CONDITION_START_DATE,
                (EntitySetup) setup);
    }

    public final void setExpiryDate(final WorldDateSetup setup) {
        this.singleSetup.put(SetupProperty.CONDITION_EXPIRY_DATE,
                (EntitySetup) setup);
    }

}
