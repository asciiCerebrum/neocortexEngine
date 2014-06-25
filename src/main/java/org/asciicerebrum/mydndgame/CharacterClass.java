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
     * Between classLevel and level - 1.
     *
     * @param level
     * @return
     */
    public List<Long> calculateBaseAtkIncrement(final Integer level) {
        ClassLevel baseLevel = this.getClassLevelByLevel(level);
        ClassLevel previousLevel = this.getClassLevelByLevel(level - 1);
        if (previousLevel == null) {
            return baseLevel.getBaseAtkBoni();
        } else {
            List<Long> atkIncrement = new ArrayList<Long>();

            int size = Math.max(baseLevel.getBaseAtkBoni().size(),
                    previousLevel.getBaseAtkBoni().size());
            for (int i = 0; i < size; i++) {
                Long singleIncrement = baseLevel.getBaseAtkBoni().get(i);
                // previousLevel might have a smaller list of baseAtk boni
                // but if not, it's value must be substracted.
                if (previousLevel.getBaseAtkBoni().size() > i) {
                    singleIncrement = singleIncrement
                            - previousLevel.getBaseAtkBoni().get(i);
                }
                atkIncrement.add(singleIncrement);
            }

            return atkIncrement;
        }
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
