package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.Level;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public class ClassLevel implements BonusSource, ObserverSource {

    /**
     * The level number of this class level.
     */
    private Level level;
    /**
     * The list of base attack boni that are granted with this level of this
     * character class.
     */
    private BonusValueTuple baseAtkBoni;

    /**
     * There is a reference to the character class holding this level.
     */
    private CharacterClass characterClass;

    /**
     * The reference to the previous class level. This is needed for calculating
     * attack bonus value deltas, for example.
     */
    private ClassLevel previousClassLevel;

    /**
     * Retrieves the base attack bonus by a given rank.
     *
     * @param rank the rank of the attack bonus.
     * @return the value of the bonus.
     */
    public final BonusValue getBaseAtkBonusByRank(final BonusRank rank) {
        return getBaseAtkBoni().getBonusValueByRank(rank);
    }

    /**
     * @return the level
     */
    public final Level getLevel() {
        return level;
    }

    /**
     * @param levelInput the level to set
     */
    public final void setLevel(final Level levelInput) {
        this.level = levelInput;
    }

    /**
     * @return the baseAtkBoni
     */
    public final BonusValueTuple getBaseAtkBoni() {
        return baseAtkBoni;
    }

    /**
     * @param baseAtkBoniInput the baseAtkBoni to set
     */
    public final void setBaseAtkBoni(final BonusValueTuple baseAtkBoniInput) {
        this.baseAtkBoni = baseAtkBoniInput;
    }

    /**
     * @return the characterClass
     */
    public final CharacterClass getCharacterClass() {
        return characterClass;
    }

    /**
     * @param characterClassInput the characterClass to set
     */
    public final void setCharacterClass(
            final CharacterClass characterClassInput) {
        this.characterClass = characterClassInput;
    }

    /**
     * @return the previousClassLevel
     */
    public final ClassLevel getPreviousClassLevel() {
        return previousClassLevel;
    }

    /**
     * @param previousClassLevelInput the previousClassLevel to set
     */
    public final void setPreviousClassLevel(
            final ClassLevel previousClassLevelInput) {
        this.previousClassLevel = previousClassLevelInput;
    }

    /**
     * Returns the difference of the base attack bonus from this instance and
     * the previous class level of the same class.
     *
     * @return the difference as a bonus value tuple for each rank.
     */
    public final BonusValueTuple getBaseAtkBoniDelta() {
        if (this.previousClassLevel == null) {
            return this.baseAtkBoni;
        }
        return this.baseAtkBoni.subtract(this.previousClassLevel.baseAtkBoni);
    }

    /**
     * Returns the difference of the base attack bonus from this instance and
     * the previous class level of the same class.
     *
     * @param rank the rank of the bonus.
     * @return the difference as a bonus value for the given rank.
     */
    public final BonusValue getBaseAtkBoniDeltaByRank(
            final BonusRank rank) {

        return this.getBaseAtkBoniDelta().getBonusValueByRank(rank);
    }

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni ctxBoni = new ContextBoni();

        ctxBoni.add(this.characterClass.getBoni(context, resolver));

        return ctxBoni;
    }

    /**
     * Retrieves the feat bindings by the given feat type.
     *
     * @param featType the feat type the bindings are needed for.
     * @return the collection of feat bindings.
     */
    public final FeatBindings getFeatBindingsByFeatType(
            final FeatType featType) {

        final FeatBindings featBindings = new FeatBindings();

        featBindings.add(this.getCharacterClass()
                .getFeatBindingsByFeatType(featType));

        return featBindings;
    }

}
