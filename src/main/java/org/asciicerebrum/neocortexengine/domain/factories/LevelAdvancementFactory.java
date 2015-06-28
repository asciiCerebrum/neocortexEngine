package org.asciicerebrum.neocortexengine.domain.factories;

import java.util.List;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Feat;
import org.apache.commons.lang3.StringUtils;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ClassLevel;
import org.asciicerebrum.neocortexengine.domain.core.particles.AdvancementNumber;
import org.asciicerebrum.neocortexengine.domain.core.particles.HitPoints;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Feats;
import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.domain.setup.SetupProperty;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.neocortexengine.infrastructure.ApplicationContextProvider;

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

        List<EntitySetup> featsSetups = setup.getPropertySetups(
                SetupProperty.FEAT_ADVANCEMENTS);
        if (featsSetups != null) {
            final Feats featAdvancements = levelAdv.getFeatAdvancements();

            for (final EntitySetup featSetup : featsSetups) {
                featAdvancements.addFeat(this.getFeatFactory().newEntity(
                        featSetup));
            }
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
