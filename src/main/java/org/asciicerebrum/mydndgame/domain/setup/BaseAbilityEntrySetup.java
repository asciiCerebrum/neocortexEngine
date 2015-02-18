package org.asciicerebrum.mydndgame.domain.setup;

/**
 *
 * @author species8472
 */
public class BaseAbilityEntrySetup
        extends AbstractEntitySetup {

    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.BASE_ABILITY,
                SetupProperty.BASE_ABILITY_VALUE};

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    public void setAbility(final String ability) {
        this.getSingleProperties().put(
                SetupProperty.BASE_ABILITY, ability);
    }

    public void setAbilityValue(final String abilityValue) {
        this.getSingleProperties().put(
                SetupProperty.BASE_ABILITY_VALUE, abilityValue);
    }

}
