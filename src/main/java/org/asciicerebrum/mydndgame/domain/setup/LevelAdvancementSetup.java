package org.asciicerebrum.mydndgame.domain.setup;

/**
 *
 * @author species8472
 */
public class LevelAdvancementSetup extends AbstractEntitySetup {

    //TODO make this definable in spring xml
    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.ADVANCEMENT_NUMBER, SetupProperty.CLASS_LEVEL,
                SetupProperty.HIT_POINTS_ADVANCEMENT};

    public final void setAdvancementNumber(final String advNumber) {
        this.getSingleProperties()
                .put(SetupProperty.ADVANCEMENT_NUMBER, advNumber);
    }

    public final void setClassLevel(final String classLevel) {
        this.getSingleProperties().put(SetupProperty.CLASS_LEVEL, classLevel);
    }

    public final void setHpAdvancement(final String hpAdvancement) {
        this.getSingleProperties().put(SetupProperty.HIT_POINTS_ADVANCEMENT,
                hpAdvancement);
    }

    public final void setAbilityAdvancement(final String abilityAdvancement) {
        this.getSingleProperties().put(SetupProperty.ABILITY_ADVANCEMENT,
                abilityAdvancement);
    }

    public final void setFeatAdvancement(final EntitySetup featSetup) {
        this.getSingleSetup().put(SetupProperty.FEAT_ADVANCEMENT, featSetup);
    }

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

}
