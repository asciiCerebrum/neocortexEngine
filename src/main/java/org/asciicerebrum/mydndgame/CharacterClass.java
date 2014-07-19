package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.asciicerebrum.mydndgame.exceptions.UndefinedCharacterClassLevelException;

/**
 *
 * @author species8472
 */
public class CharacterClass {

    /**
     * Specific logger for this class.
     */
    private static final Logger LOG = Logger.getLogger(CharacterClass.class);

    /**
     * The unique id of the character class.
     */
    private String id;
    /**
     * The type of hit die associated with this character class.
     */
    private Dice hitDice;
    /**
     * The list of class levels which define the character advancement within
     * this character class.
     */
    private List<ClassLevel> classLevels = new ArrayList<ClassLevel>();

    /**
     *
     * @param level the position in the classLevel list. That is the class
     * level.
     * @return the level-th element of the classLevel list.
     */
    public final ClassLevel getClassLevelByLevel(final Integer level) {
        for (ClassLevel cl : this.classLevels) {
            if (cl.getLevel().equals(level)) {
                return cl;
            }
        }
        throw new UndefinedCharacterClassLevelException(this, level);
    }

    /**
     *
     * @param cLevel the base class level.
     * @param currentRank the rank of interest.
     * @return the difference between the bonus of level and level - 1 of the
     * given rank.
     */
    public final Long getBaseAtkBonusValueDeltaByLevelAndRank(
            final ClassLevel cLevel, final Long currentRank) {
        Long valueDelta = cLevel.getBaseAtkBonusByRank(currentRank).getValue();

        try {
            // bonus of previous level with same rank
            final ClassLevel prevLevel
                    = this.getClassLevelByLevel(
                            cLevel.getLevel() - 1);
            // prevBonus could be null if the given currentRank is not
            // available in the previous class level!
            final Bonus prevBonus
                    = prevLevel.getBaseAtkBonusByRank(
                            currentRank);

            if (prevBonus != null) {
                // the bonus difference between the two levels
                valueDelta = valueDelta
                        - prevBonus.getValue();
            }
        } catch (final UndefinedCharacterClassLevelException e) {
            //TODO test this output
            LOG.info(e.getMessage() + " Using original value for delta.");
        }
        return valueDelta;
    }

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * @return the hitDice
     */
    public final Dice getHitDice() {
        return hitDice;
    }

    /**
     * @param hitDiceInput the hitDice to set
     */
    public final void setHitDice(final Dice hitDiceInput) {
        this.hitDice = hitDiceInput;
    }

    /**
     * @return the classLevels
     */
    public final List<ClassLevel> getClassLevels() {
        return classLevels;
    }

    /**
     * @param classLevelsInput the classLevels to set
     */
    public final void setClassLevels(final List<ClassLevel> classLevelsInput) {
        this.classLevels = classLevelsInput;
    }

}
