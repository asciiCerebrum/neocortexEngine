package org.asciicerebrum.mydndgame;

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

    private final CharacterSetup setup;

    @BonusGranter
    private Race race;
    // classMap: tupel from characterClass, Hp-Addition for this class
    @BonusGranter
    private List<CharacterClass> classList
            = new ArrayList<CharacterClass>();
    private List<Long> hpAdditionList
            = new ArrayList<Long>();
    @BonusGranter
    private Map<Ability, Long> abilityMap
            = new HashMap<Ability, Long>();
    @BonusGranter
    private List<ClassLevel> classLevels = new ArrayList<ClassLevel>();

    private List<Bonus> boni = new ArrayList<Bonus>();

    //TODO make DndCharacter Spring Prototype and set these values via
    // application context xml
    private final BonusCalculationService bonusService;
    private final DiceAction hp;
    private final DiceAction acAction;
    private final DiceAction attackAction;
    private final BonusType baseAttackBonus;

    private DndCharacter(Builder builder) {
        this.hp = builder.context.getBean("hp", DiceAction.class);
        this.attackAction = builder.context.getBean("attack",
                DiceAction.class);
        this.baseAttackBonus = builder.context.getBean("baseAttackBonus",
                BonusType.class);
        this.bonusService = builder.context.getBean("bonusCalculationService",
                BonusCalculationService.class);

        this.race = builder.context.getBean(
                builder.setup.getRace(), Race.class);

        this.setup = builder.setup;

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

            this.classList.add(chClass);
            this.hpAdditionList.add(advance.getHpAddition());

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

            if (advance.getAbilityName() != null) {
                Ability additionalAbility
                        = builder.context.getBean(
                                advance.getAbilityName(), Ability.class);
                this.abilityMap.put(additionalAbility,
                        this.abilityMap.get(additionalAbility) + 1);
            }
        }

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

    public List<Bonus> getBaseAtkBoni() {
        List<Bonus> baseAtkBoni = new ArrayList<Bonus>();

        for (Bonus potentialBonus : this.getBoni()) {
            if (potentialBonus.getBonusType().equals(
                    this.getBaseAttackBonus())) {
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

        return this.getAcAction().getConstValue();
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

    /**
     * @return the maxHp
     */
    public Long getMaxHp() {

        Long maxHp = 0L;

        for (int i = 0; i < this.getClassList().size(); i++) {
            // in case of null use max hp addition, defined by 
            // the class' hitdice
            if (this.getHpAdditionList().get(i) != null) {
                maxHp += this.getHpAdditionList().get(i);
            } else {
                maxHp += this.getClassList().get(i).getHitDice().getSides();
            }

        }
        // hp add con-modifier for each class level
        maxHp += this.getClassList().size()
                * this.bonusService.retrieveEffectiveBonusValueByTarget(
                        this, this, this.hp);

        return maxHp;
    }

    /**
     * @return the bonusService
     */
    public BonusCalculationService getBonusService() {
        return bonusService;
    }

    /**
     * @return the hp
     */
    public DiceAction getHp() {
        return hp;
    }

    /**
     * @return the baseAttackBonus
     */
    public BonusType getBaseAttackBonus() {
        return baseAttackBonus;
    }

    /**
     * @return the classList
     */
    public List<CharacterClass> getClassList() {
        return classList;
    }

    /**
     * @param classList the classList to set
     */
    public void setClassList(List<CharacterClass> classList) {
        this.classList = classList;
    }

    /**
     * @return the hpAdditionList
     */
    public List<Long> getHpAdditionList() {
        return hpAdditionList;
    }

    /**
     * @param hpAdditionList the hpAdditionList to set
     */
    public void setHpAdditionList(List<Long> hpAdditionList) {
        this.hpAdditionList = hpAdditionList;
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
