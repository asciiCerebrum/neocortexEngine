package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class CharacterClass {

    private String id;
    private Dice hitDice;
    private List<ClassLevel> classLevels = new ArrayList<ClassLevel>();

    public ClassLevel getClassLevelByLevel(final Integer level) {
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
    public String getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * @return the hitDice
     */
    public Dice getHitDice() {
        return hitDice;
    }

    /**
     * @param hitDice the hitDice to set
     */
    public void setHitDice(Dice hitDice) {
        this.hitDice = hitDice;
    }

    /**
     * @return the classLevels
     */
    public List<ClassLevel> getClassLevels() {
        return classLevels;
    }

    /**
     * @param classLevels the classLevels to set
     */
    public void setClassLevels(List<ClassLevel> classLevels) {
        this.classLevels = classLevels;
    }

}
