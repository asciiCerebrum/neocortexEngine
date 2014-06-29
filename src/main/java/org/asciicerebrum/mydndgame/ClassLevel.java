package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class ClassLevel {

    /**
     * The level number of this class level.
     */
    private Integer level;
    /**
     * The character class associated with this class level.
     */
    private CharacterClass characterClass;
    /**
     * The list of base attack boni that are granted with this level of this
     * character class.
     */
    private List<Bonus> baseAtkBoni = new ArrayList<Bonus>();

    /**
     *
     * @param rank the rank of the base attack bonus. E.g. when you have
     * +10/+5/+1 then the bonus +5 is of rank 1, as rank starts with 0.
     * @return the one base attack bonus of the given rank.
     */
    public final Bonus getBaseAtkBonusByRank(final Long rank) {
        for (Bonus bonus : baseAtkBoni) {
            if (bonus.getRank().equals(rank)) {
                return bonus;
            }
        }
        return null;
    }

    /**
     * @return the level
     */
    public final Integer getLevel() {
        return level;
    }

    /**
     * @param levelInput the level to set
     */
    public final void setLevel(final Integer levelInput) {
        this.level = levelInput;
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
     * @return the baseAtkBoni
     */
    public final List<Bonus> getBaseAtkBoni() {
        return baseAtkBoni;
    }

    /**
     * @param baseAtkBoniInput the baseAtkBoni to set
     */
    public final void setBaseAtkBoni(final List<Bonus> baseAtkBoniInput) {
        this.baseAtkBoni = baseAtkBoniInput;
    }

}
