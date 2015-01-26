package org.asciicerebrum.mydndgame.domain.gameentities;

import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.domain.core.attribution.Ability;
import org.asciicerebrum.mydndgame.domain.core.attribution.ClassLevel;
import org.asciicerebrum.mydndgame.domain.core.particles.AdvancementNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupProperty;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class LevelAdvancementFactory
        implements EntityFactory<LevelAdvancement> {

    /**
     * Spring context to get the beans from.
     */
    private ApplicationContext context;

    /**
     * Factory for the feat and its binding.
     */
    private EntityFactory<Feat> featFactory;

    @Override
    public LevelAdvancement newEntity(final EntitySetup<LevelAdvancement> setup,
            final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the level "
                    + "advancement is not complete.");
        }

        LevelAdvancement levelAdv = new LevelAdvancement();

        levelAdv.setAdvNumber(new AdvancementNumber(setup.getProperty(
                SetupProperty.ADVANCEMENT_NUMBER)));
        levelAdv.setHpAdvancement(new HitPoints(setup.getProperty(
                SetupProperty.HIT_POINTS_ADVANCEMENT)));
        levelAdv.setClassLevel(this.context.getBean(setup.getProperty(
                SetupProperty.CLASS_LEVEL), ClassLevel.class));

        String abilityId = setup.getProperty(SetupProperty.ABILITY_ADVANCEMENT);
        if (StringUtils.isNotBlank(abilityId)) {
            levelAdv.setAbilityAdvancement(this.context.getBean(
                    abilityId, Ability.class));
        }

        EntitySetup featSetup = setup.getPropertySetup(
                SetupProperty.FEAT_ADVANCEMENT);
        if (featSetup != null) {
            levelAdv.setFeatAdvancement(this.featFactory.newEntity(featSetup,
                    reassignments));
        }

        return levelAdv;
    }

    @Override
    public void reAssign(final EntitySetup<LevelAdvancement> setup,
            final LevelAdvancement entity) {
        // nothing to do here
    }

    /**
     * @param contextInput the context to set
     */
    public final void setContext(final ApplicationContext contextInput) {
        this.context = contextInput;
    }

    /**
     * @param featFactoryInput the featFactory to set
     */
    public final void setFeatFactory(
            final EntityFactory<Feat> featFactoryInput) {
        this.featFactory = featFactoryInput;
    }

}
