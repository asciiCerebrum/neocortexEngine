package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class CharacterClass {

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
        return null;
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
