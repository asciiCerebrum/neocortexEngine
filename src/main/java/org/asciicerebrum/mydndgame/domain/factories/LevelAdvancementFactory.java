package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.ruleentities.Feat;
import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.ruleentities.ClassLevel;
import org.asciicerebrum.mydndgame.domain.core.particles.AdvancementNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class LevelAdvancementFactory
        implements EntityFactory<LevelAdvancement> {

    /**
     * Factory for the feat and its binding.
     */
    private EntityFactory<Feat> featFactory;

    @Override
    public final LevelAdvancement newEntity(final EntitySetup setup) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the level "
                    + "advancement is not complete.");
        }

        LevelAdvancement levelAdv = new LevelAdvancement();

        levelAdv.setAdvNumber(new AdvancementNumber(setup.getProperty(
                SetupProperty.ADVANCEMENT_NUMBER)));
        levelAdv.setHpAdvancement(new HitPoints(setup.getProperty(
                SetupProperty.HIT_POINTS_ADVANCEMENT)));
        levelAdv.setClassLevel(ApplicationContextProvider
                .getApplicationContext().getBean(setup.getProperty(
                                SetupProperty.CLASS_LEVEL), ClassLevel.class));

        String abilityId = setup.getProperty(SetupProperty.ABILITY_ADVANCEMENT);
        if (StringUtils.isNotBlank(abilityId)) {
            levelAdv.setAbilityAdvancement(ApplicationContextProvider
                    .getApplicationContext().getBean(
                            abilityId, Ability.class));
        }

        EntitySetup featSetup = setup.getPropertySetup(
                SetupProperty.FEAT_ADVANCEMENT);
        if (featSetup != null) {
            levelAdv.setFeatAdvancement(this.getFeatFactory().newEntity(
                    featSetup));
        }

        return levelAdv;
    }

    /**
     * @param featFactoryInput the featFactory to set
     */
    public final void setFeatFactory(
            final EntityFactory<Feat> featFactoryInput) {
        this.featFactory = featFactoryInput;
    }

    /**
     * @return the featFactory
     */
    public final EntityFactory<Feat> getFeatFactory() {
        return featFactory;
    }

}
