package org.asciicerebrum.mydndgame.domain.setup;

/**
 *
 * @author species8472
 */
public class BaseAbilityEntrySetup
        extends AbstractEntitySetup {

    /**
     * The required properties.
     */
    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.BASE_ABILITY,
                SetupProperty.BASE_ABILITY_VALUE};

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    /**
     * @param ability the ability.
     */
    public final void setAbility(final String ability) {
        this.getSingleProperties().put(
                SetupProperty.BASE_ABILITY, ability);
    }

    /**
     * @param abilityValue the ability value.
     */
    public final void setAbilityValue(final String abilityValue) {
        this.getSingleProperties().put(
                SetupProperty.BASE_ABILITY_VALUE, abilityValue);
    }

}
