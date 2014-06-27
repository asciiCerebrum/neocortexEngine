package org.asciicerebrum.mydndgame;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class DndCharacter implements BonusSource {

    private CharacterSetup setup;

    @BonusGranter
    private Race race;
    @BonusGranter
    private List<CharacterClass> classStack = new ArrayList<CharacterClass>();
    @BonusGranter
    private Map<Ability, Long> abilityMap = new HashMap<Ability, Long>();
    private int maxHp = 0;

    private List<Bonus> boni = new ArrayList<Bonus>();

    @BonusGranter
    private List<ClassLevel> classLevels = new ArrayList<ClassLevel>();

    private final DiceAction acAction;
    private final DiceAction attackAction;
    private final BonusType baseAttackBonus;

    private DndCharacter(Builder builder) {
        this.attackAction = builder.context.getBean("attack",
                DiceAction.class);
        this.baseAttackBonus = builder.context.getBean("baseAttackBonus",
                BonusType.class);

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
            this.classStack.add(chClass);

            // adding class levels as they come
            Integer classCount = this.countClassLevelsByCharacterClass(chClass);
            ClassLevel cLevel = chClass.getClassLevelByLevel(classCount + 1);
            this.classLevels.add(cLevel);

            // merge baseAtk boni
            List<Bonus> newBoni = cLevel.getBaseAtkBoni();
            for (Bonus newBonus : newBoni) {
                final Long currentRank = newBonus.getRank();
                Long valueDelta = newBonus.getValue();
                boolean bonusFound = false;

                for (Bonus existingBonus : this.boni) {
                    if (existingBonus.getRank().equals(currentRank)) {
                        // bonus of previous level with same rank

                        final ClassLevel prevLevel
                                = chClass.getClassLevelByLevel(
                                        cLevel.getLevel() - 1);

                        if (prevLevel != null) {
                            final Bonus prevBonus
                                    = prevLevel.getBaseAtkBonusByRank(
                                            currentRank);

                            valueDelta = valueDelta
                                    - prevBonus.getValue();
                        }
                        existingBonus.setValue(existingBonus.getValue()
                                + valueDelta);
                        bonusFound = true;
                        break;
                    }
                }
                if (!bonusFound) {
                    this.boni.add(newBonus.makeCopy());
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

        this.acAction = builder.context.getBean("ac", DiceAction.class);
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
    
    public List<Bonus> getBaseAtkBoni() {
        List<Bonus> baseAtkBoni = new ArrayList<Bonus>();
        
        for (Bonus potentialBonus : this.boni) {
            if (potentialBonus.getBonusType().equals(this.baseAttackBonus)) {
                baseAtkBoni.add(potentialBonus);
            }
        }
        
        return baseAtkBoni;
    }

    public Long getAc() {
        //TODO search for anything in the object graph of this
        // character that grants boni for ac.
        // ProTipp: Use annotations to mark the path to follow
        // for the corresponding fields!

        System.out.println("Examining fields.");
        for (Field field : DndCharacter.class.getDeclaredFields()) {
            System.out.println("Field: " + field.getType()
                    + " :: " + field.getGenericType());
        }

        return this.getAcAction().getConstValue();
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
     * @return the classStack
     */
    public List<CharacterClass> getClassStack() {
        return classStack;
    }

    /**
     * @param classStack the classStack to set
     */
    public void setClassStack(List<CharacterClass> classStack) {
        this.classStack = classStack;
    }

    /**
     * @return the boni
     */
    public List<Bonus> getBoni() {
        return boni;
    }

    /**
     * @param boni the boni to set
     */
    public void setBoni(List<Bonus> boni) {
        this.boni = boni;
    }

    /**
     * @return the acAction
     */
    public DiceAction getAcAction() {
        return acAction;
    }

    /**
     * @return the attackAction
     */
    public DiceAction getAttackAction() {
        return attackAction;
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
}
