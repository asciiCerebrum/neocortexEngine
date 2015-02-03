package org.asciicerebrum.mydndgame.domain.rules;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.Level;

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

    public final BonusValue getBaseAtkBonusByRank(final BonusRank rank) {
        return getBaseAtkBoni().getBonusValueByRank(rank);
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * @return the baseAtkBoni
     */
    public BonusValueTuple getBaseAtkBoni() {
        return baseAtkBoni;
    }

    /**
     * @param baseAtkBoni the baseAtkBoni to set
     */
    public void setBaseAtkBoni(BonusValueTuple baseAtkBoni) {
        this.baseAtkBoni = baseAtkBoni;
    }

    /**
     * @return the characterClass
     */
    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    /**
     * @param characterClass the characterClass to set
     */
    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    /**
     * @return the previousClassLevel
     */
    public ClassLevel getPreviousClassLevel() {
        return previousClassLevel;
    }

    /**
     * @param previousClassLevel the previousClassLevel to set
     */
    public void setPreviousClassLevel(ClassLevel previousClassLevel) {
        this.previousClassLevel = previousClassLevel;
    }

    public final BonusValueTuple getBaseAtkBoniDelta() {
        if (this.previousClassLevel == null) {
            return this.baseAtkBoni;
        }
        return this.baseAtkBoni.subtract(this.previousClassLevel.baseAtkBoni);
    }

    public final BonusValue getBaseAtkBoniDeltaByRank(
            final BonusRank rank) {

        return this.getBaseAtkBoniDelta().getBonusValueByRank(rank);
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        bonusSources.add(this.characterClass);

        return bonusSources;
    }

}
