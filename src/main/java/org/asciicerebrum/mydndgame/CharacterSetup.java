package org.asciicerebrum.mydndgame;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author species8472
 */
public class CharacterSetup {

    private String name;
    private Stack<LevelAdvancement> levelAdvancementStack
            = new Stack<LevelAdvancement>();
    // the abilities from character creation
    private Map<String, Long> baseAbilityMap
            = new HashMap<String, Long>();
    private String race;
    private int currentHp;
    private int currentHpNonlethal;
    private int currentXp;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the race
     */
    public String getRace() {
        return race;
    }

    /**
     * @param race the race to set
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * @return the currentHp
     */
    public int getCurrentHp() {
        return currentHp;
    }

    /**
     * @param currentHp the currentHp to set
     */
    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    /**
     * @return the currentHpNonlethal
     */
    public int getCurrentHpNonlethal() {
        return currentHpNonlethal;
    }

    /**
     * @param currentHpNonlethal the currentHpNonlethal to set
     */
    public void setCurrentHpNonlethal(int currentHpNonlethal) {
        this.currentHpNonlethal = currentHpNonlethal;
    }

    /**
     * @return the levelAdvancementStack
     */
    public Stack<LevelAdvancement> getLevelAdvancementStack() {
        return levelAdvancementStack;
    }

    /**
     * @param levelAdvancementStack the levelAdvancementStack to set
     */
    public void setLevelAdvancementStack(Stack<LevelAdvancement> levelAdvancementStack) {
        this.levelAdvancementStack = levelAdvancementStack;
    }

    /**
     * @return the baseAbilityMap
     */
    public Map<String, Long> getBaseAbilityMap() {
        return baseAbilityMap;
    }

    /**
     * @param baseAbilityMap the baseAbilityMap to set
     */
    public void setBaseAbilityMap(Map<String, Long> baseAbilityMap) {
        this.baseAbilityMap = baseAbilityMap;
    }

    /**
     * @return the currentXp
     */
    public int getCurrentXp() {
        return currentXp;
    }

    /**
     * @param currentXp the currentXp to set
     */
    public void setCurrentXp(int currentXp) {
        this.currentXp = currentXp;
    }

}
