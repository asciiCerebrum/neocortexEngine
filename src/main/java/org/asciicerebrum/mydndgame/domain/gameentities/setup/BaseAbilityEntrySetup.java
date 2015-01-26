package org.asciicerebrum.mydndgame.domain.gameentities.setup;

import org.asciicerebrum.mydndgame.domain.core.attribution.BaseAbilityEntry;

/**
 *
 * @author species8472
 */
public class BaseAbilityEntrySetup
        extends AbstractEntitySetup<BaseAbilityEntry> {

    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.BASE_ABILITY,
                SetupProperty.BASE_ABILITY_VALUE};

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    public void setAbility(final String ability) {
        this.singleProperties.put(
                SetupProperty.BASE_ABILITY, ability);
    }

    public void setAbilityValue(final String abilityValue) {
        this.singleProperties.put(
                SetupProperty.BASE_ABILITY_VALUE, abilityValue);
    }

}
