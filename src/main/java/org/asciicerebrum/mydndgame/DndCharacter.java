package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class DndCharacter {

    private CharacterSetup setup;
    private Race race;
    private Stack<CharacterClass> classStack = new Stack<CharacterClass>();
    private Map<Ability, Long> abilityMap = new HashMap<Ability, Long>();
    private int maxHp = 0;
    private List<Long> baseAtkBoni = new ArrayList<Long>();

    private List<ClassLevel> classLevels = new ArrayList<ClassLevel>();

    private DndCharacter(Builder builder) {
        this.race = builder.context.getBean(
                builder.setup.getRace(), Race.class);

        for (Entry<String, Long> abilityEntry
                : builder.setup.getBaseAbilityMap().entrySet()) {
            Ability ability = builder.context.getBean(abilityEntry.getKey(),
                    Ability.class);
            this.abilityMap.put(ability, abilityEntry.getValue());
        }

        for (LevelAdvancement advance
                : builder.setup.getLevelAdvancementStack()) {
            CharacterClass chClass
                    = builder.context.getBean(advance.getClassName(),
                            CharacterClass.class);
            this.classStack.push(chClass);

            // adding class levels as they come
            Integer classCount = this.countClassLevelsByCharacterClass(chClass);
            this.classLevels.add(chClass.getClassLevelByLevel(classCount + 1));

            // calculate base atk boni
            List<Long> baseAtkIncrement = chClass.calculateBaseAtkIncrement(
                    classCount + 1);
            int size = Math.max(this.baseAtkBoni.size(),
                    baseAtkIncrement.size());
            for (int i = 0; i < size; i++) {
                // there is a chance that baseAtkIncrement might be longer!
                if (this.baseAtkBoni.size() > i) {
                    this.baseAtkBoni.set(i, this.baseAtkBoni.get(i)
                            + baseAtkIncrement.get(i));
                } else {
                    this.baseAtkBoni.add(baseAtkIncrement.get(i));
                }
            }

            // in case of null use max hp addition, defined by 
            // the class' hitdice
            if (advance.getHpAddition() != null) {
                this.maxHp += advance.getHpAddition();
            } else {
                this.maxHp += chClass.getHitDice().getSides();
            }

            if (advance.getAbilityName() != null) {
                Ability additionalAbility
                        = builder.context.getBean(
                                advance.getAbilityName(), Ability.class);
                this.abilityMap.put(additionalAbility,
                        this.abilityMap.get(additionalAbility) + 1);
            }
        }

        // hp add con-modifier for each class level
        Ability con = builder.context.getBean("con", Ability.class);
        this.maxHp += this.classStack.size() * this.calculateAbilityMod(con);
    }

    private Integer countClassLevelsByCharacterClass(
            final CharacterClass charCl) {
        Integer count = 0;
        for (ClassLevel cl : this.getClassLevels()) {
            if (cl.getCharacterClass().equals(charCl)) {
                count++;
            }
        }
        return count;
    }

    private long calculateAbilityMod(final Ability ability) {
        Long score = this.getAbilityMap().get(ability);

        return Math.round(Math.floor((score - 10) / 2.0));
    }

    /**
     * @return the maxHp
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * @param maxHp the maxHp to set
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * @return the abilityMap
     */
    public Map<Ability, Long> getAbilityMap() {
        return abilityMap;
    }

    /**
     * @param abilityMap the abilityMap to set
     */
    public void setAbilityMap(Map<Ability, Long> abilityMap) {
        this.abilityMap = abilityMap;
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

    public static class Builder {

        private final CharacterSetup setup;
        private final ApplicationContext context;

        public Builder(final CharacterSetup setupInput,
                final ApplicationContext contextInput) {
            this.setup = setupInput;
            this.context = contextInput;
        }

        public DndCharacter build() {
            return new DndCharacter(this);
        }
    }

    /**
     * @return the setup
     */
    public CharacterSetup getSetup() {
        return setup;
    }

    /**
     * @param setup the setup to set
     */
    public void setSetup(CharacterSetup setup) {
        this.setup = setup;
    }

    /**
     * @return the race
     */
    public Race getRace() {
        return race;
    }

    /**
     * @param race the race to set
     */
    public void setRace(Race race) {
        this.race = race;
    }

    /**
     * @return the classStack
     */
    public Stack<CharacterClass> getClassStack() {
        return classStack;
    }

    /**
     * @param classStack the classStack to set
     */
    public void setClassStack(Stack<CharacterClass> classStack) {
        this.classStack = classStack;
    }
}
