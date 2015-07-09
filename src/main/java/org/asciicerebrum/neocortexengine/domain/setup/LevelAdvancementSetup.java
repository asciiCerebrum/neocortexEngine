package org.asciicerebrum.neocortexengine.domain.setup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class LevelAdvancementSetup extends AbstractEntitySetup {

    /**
     * The required properties.
     */
    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.ADVANCEMENT_NUMBER, SetupProperty.CLASS_LEVEL,
                SetupProperty.HIT_POINTS_ADVANCEMENT};

    /**
     * @param advNumber the advancement number.
     */
    public final void setAdvancementNumber(final String advNumber) {
        this.getSingleProperties()
                .put(SetupProperty.ADVANCEMENT_NUMBER, advNumber);
    }

    /**
     * @param classLevel the class level. The name of the class with the level,
     * e.g. fighter1.
     */
    public final void setClassLevel(final String classLevel) {
        this.getSingleProperties().put(SetupProperty.CLASS_LEVEL, classLevel);
    }

    /**
     * @param hpAdvancement the HP advancement.
     */
    public final void setHpAdvancement(final String hpAdvancement) {
        this.getSingleProperties().put(SetupProperty.HIT_POINTS_ADVANCEMENT,
                hpAdvancement);
    }

    /**
     * @param abilityAdvancement the ability advancement.
     */
    public final void setAbilityAdvancement(final String abilityAdvancement) {
        this.getSingleProperties().put(SetupProperty.ABILITY_ADVANCEMENT,
                abilityAdvancement);
    }

    /**
     * @param featSetup the feat advancement.
     */
    public final void addFeatAdvancement(final EntitySetup featSetup) {
        List<EntitySetup> featAdvancements
                = this.getListSetup().get(SetupProperty.FEAT_ADVANCEMENTS);
        if (featAdvancements == null) {
            featAdvancements = new ArrayList<EntitySetup>();
            this.getListSetup().put(SetupProperty.FEAT_ADVANCEMENTS,
                    featAdvancements);
        }
        featAdvancements.add(featSetup);
    }

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

}
