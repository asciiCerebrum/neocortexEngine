package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author species8472
 */
public class CharacterSetup {

    /**
     * The name of the character.
     */
    private String name;
    /**
     * The list of character class level advancements, the character has.
     */
    private List<LevelAdvancement> levelAdvancementStack
            = new ArrayList<LevelAdvancement>();
    /**
     * The abilities from character creation together with their values.
     */
    private Map<String, Long> baseAbilityMap
            = new HashMap<String, Long>();
    /**
     * The race of the character as a string, which is the bean-id.
     */
    private String race;
    /**
     * The number of hitpints, the character currently has.
     */
    private int currentHp;
    /**
     * The number of nonlethal hitpoints, the character currently has.
     */
    private int currentHpNonlethal;
    /**
     * The number of experience points, the character currently has.
     */
    private int currentXp;

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param nameInput the name to set
     */
    public final void setName(final String nameInput) {
        this.name = nameInput;
    }

    /**
     * @return the race
     */
    public final String getRace() {
        return race;
    }

    /**
     * @param raceInput the race to set
     */
    public final void setRace(final String raceInput) {
        this.race = raceInput;
    }

    /**
     * @return the currentHp
     */
    public final int getCurrentHp() {
        return currentHp;
    }

    /**
     * @param currentHpInput the currentHp to set
     */
    public final void setCurrentHp(final int currentHpInput) {
        this.currentHp = currentHpInput;
    }

    /**
     * @return the currentHpNonlethal
     */
    public final int getCurrentHpNonlethal() {
        return currentHpNonlethal;
    }

    /**
     * @param currentHpNonlethalInput the currentHpNonlethal to set
     */
    public final void setCurrentHpNonlethal(final int currentHpNonlethalInput) {
        this.currentHpNonlethal = currentHpNonlethalInput;
    }

    /**
     * @return the baseAbilityMap
     */
    public final Map<String, Long> getBaseAbilityMap() {
        return baseAbilityMap;
    }

    /**
     * @param baseAbilityMapInput the baseAbilityMap to set
     */
    public final void setBaseAbilityMap(
            final Map<String, Long> baseAbilityMapInput) {
        this.baseAbilityMap = baseAbilityMapInput;
    }

    /**
     * @return the currentXp
     */
    public final int getCurrentXp() {
        return currentXp;
    }

    /**
     * @param currentXpInput the currentXp to set
     */
    public final void setCurrentXp(final int currentXpInput) {
        this.currentXp = currentXpInput;
    }

    /**
     * @return the levelAdvancementStack
     */
    public final List<LevelAdvancement> getLevelAdvancementStack() {
        return levelAdvancementStack;
    }

    /**
     * @param levelAdvancementStackInput the levelAdvancementStack to set
     */
    public final void setLevelAdvancementStack(
            final List<LevelAdvancement> levelAdvancementStackInput) {
        this.levelAdvancementStack = levelAdvancementStackInput;
    }

}
