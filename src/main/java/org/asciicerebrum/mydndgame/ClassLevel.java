package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class ClassLevel {

    private Integer level;
    private CharacterClass characterClass;
    private List<Long> baseAtkBoni = new ArrayList<Long>();

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return the baseAtkBoni
     */
    public List<Long> getBaseAtkBoni() {
        return baseAtkBoni;
    }

    /**
     * @param baseAtkBoni the baseAtkBoni to set
     */
    public void setBaseAtkBoni(List<Long> baseAtkBoni) {
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

}
