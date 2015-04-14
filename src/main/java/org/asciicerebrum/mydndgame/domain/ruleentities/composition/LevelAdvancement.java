package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.ruleentities.ClassLevel;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.AdvancementNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.mydndgame.domain.ruleentities.Feats;

/**
 *
 * @author species8472
 */
public class LevelAdvancement implements BonusSource, ObserverSource {

    /**
     * Gives an order of the level advancements for a character.
     */
    private AdvancementNumber advNumber;

    /**
     * The class level that was gained.
     */
    private ClassLevel classLevel;

    /**
     * The additional hit points that were won.
     */
    private HitPoints hpAdvancement;

    /**
     * The further ability where one point was achieved.
     */
    private Ability abilityAdvancement;

    /**
     * Feat type plus its binding to a special entity.
     */
    private Feats featAdvancements = new Feats();

    /**
     * @return the advNumber
     */
    public final AdvancementNumber getAdvNumber() {
        return advNumber;
    }

    /**
     * @param advNumberInput the advNumber to set
     */
    public final void setAdvNumber(final AdvancementNumber advNumberInput) {
        this.advNumber = advNumberInput;
    }

    /**
     * @return the classLevel
     */
    public final ClassLevel getClassLevel() {
        return classLevel;
    }

    /**
     * @param classLevelInput the classLevel to set
     */
    public final void setClassLevel(final ClassLevel classLevelInput) {
        this.classLevel = classLevelInput;
    }

    /**
     * In case of the very first character class level (adv number 0) the
     * maximum number of the class's hit dice is returned.
     *
     * @return the hpAdvancement
     */
    public final HitPoints getHpAdvancement() {
        if (AdvancementNumber.ADV_NO_0.equals(this.advNumber)) {
            return new HitPoints(this.getClassLevel().getCharacterClass()
                    .getHitDice().getSides().getValue());
        }
        return hpAdvancement;
    }

    /**
     * @param hpAdvancementInput the hpAdvancement to set
     */
    public final void setHpAdvancement(final HitPoints hpAdvancementInput) {
        this.hpAdvancement = hpAdvancementInput;
    }

    /**
     * @return the abilityAdvancement
     */
    public final Ability getAbilityAdvancement() {
        return abilityAdvancement;
    }

    /**
     * @param abilityAdvancementInput the abilityAdvancement to set
     */
    public final void setAbilityAdvancement(
            final Ability abilityAdvancementInput) {
        this.abilityAdvancement = abilityAdvancementInput;
    }

    /**
     * @return the featAdvancement
     */
    public final Feats getFeatAdvancements() {
        return featAdvancements;
    }

    /**
     * @param featAdvancementsInput the featAdvancement to set
     */
    public final void setFeatAdvancements(final Feats featAdvancementsInput) {
        this.featAdvancements = featAdvancementsInput;
    }

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni ctxBoni = new ContextBoni();

        if (this.classLevel != null) {
            ctxBoni.add(this.classLevel.getBoni(context, resolver));
        }
        ctxBoni.add(this.featAdvancements.getBoni(context, resolver));

        return ctxBoni;
    }

}
