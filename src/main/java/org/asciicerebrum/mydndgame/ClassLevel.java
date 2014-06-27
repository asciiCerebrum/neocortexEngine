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
    private List<Bonus> baseAtkBoni = new ArrayList<Bonus>();

    public Bonus getBaseAtkBonusByRank(final Long rank) {
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
     * @return the baseAtkBoni
     */
    public List<Bonus> getBaseAtkBoni() {
        return baseAtkBoni;
    }

    /**
     * @param baseAtkBoni the baseAtkBoni to set
     */
    public void setBaseAtkBoni(List<Bonus> baseAtkBoni) {
        this.baseAtkBoni = baseAtkBoni;
    }

}
