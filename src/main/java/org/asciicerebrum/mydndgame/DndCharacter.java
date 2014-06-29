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
public final class DndCharacter implements BonusSource {

    /**
     * The setup for the character creation.
     */
    private final CharacterSetup setup;

    /**
     * The race of this dnd character.
     */
    @BonusGranter
    private Race race;

    /**
     * The ordered list of classes this character advanced in.
     */
    @BonusGranter
    private List<CharacterClass> classList
            = new ArrayList<CharacterClass>();
    /**
     * The ordered list of additions to the hit points this character acquired
     * during class level advancements.
     */
    private List<Long> hpAdditionList
            = new ArrayList<Long>();
    /**
     * The map of abilities together with their values.
     */
    @BonusGranter
    private Map<Ability, Long> abilityMap
            = new HashMap<Ability, Long>();
    /**
     * The ordered list of class levels this character advanced in.
     */
    @BonusGranter
    private List<ClassLevel> classLevels = new ArrayList<ClassLevel>();

    /**
     * The complete collection of all boni which are associated with this
     * character.
     */
    private List<Bonus> boni = new ArrayList<Bonus>();

    //TODO make DndCharacter Spring Prototype and set these values via
    // application context xml
    /**
     * The bonus calculation service needed for dynamic bonus value calculation.
     */
    private final BonusCalculationService bonusService;
    /**
     * The diceAction with id hp.
     */
    private final DiceAction hp;
    /**
     * The diceAction with id acAction.
     */
    private final DiceAction acAction;
    /**
     * The diceAction with id attackAction.
     */
    private final DiceAction attackAction;
    /**
     * The bonusType with id baseAttackBonus.
     */
    private final BonusType baseAttackBonus;

    /**
     *
     * @param builder Character creation with a builder.
     */
    private DndCharacter(final Builder builder) {
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

    /**
     *
     * @param charCl The character class which needs to be counted.
     * @return The number of class levels this character has for the given
     * character class.
     */
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

    /**
     *
     * @return the list of base attack boni for this character.
     */
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

    /**
     * @return the abilityMap
     */
    public Map<Ability, Long> getAbilityMap() {
        return abilityMap;
    }

    /**
     * @param abilityMapInput the abilityMap to set
     */
    public void setAbilityMap(final Map<Ability, Long> abilityMapInput) {
        this.abilityMap = abilityMapInput;
    }

    /**
     * @return the classLevels
     */
    public List<ClassLevel> getClassLevels() {
        return classLevels;
    }

    /**
     * @param classLevelsInput the classLevels to set
     */
    public void setClassLevels(final List<ClassLevel> classLevelsInput) {
        this.classLevels = classLevelsInput;
    }

    /**
     * @return the boni
     */
    public List<Bonus> getBoni() {
        return boni;
    }

    /**
     * @param boniInput the boni to set
     */
    public void setBoni(final List<Bonus> boniInput) {
        this.boni = boniInput;
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
     *
     * @return the calculated armor class of this character.
     */
    public Long getAc() {
        return this.acAction.getConstValue()
                + this.bonusService.retrieveEffectiveBonusValueByTarget(
                        this, this, this.acAction);
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
     * @param classListInput the classList to set
     */
    public void setClassList(final List<CharacterClass> classListInput) {
        this.classList = classListInput;
    }

    /**
     * @return the hpAdditionList
     */
    public List<Long> getHpAdditionList() {
        return hpAdditionList;
    }

    /**
     * @param hpAdditionListInput the hpAdditionList to set
     */
    public void setHpAdditionList(final List<Long> hpAdditionListInput) {
        this.hpAdditionList = hpAdditionListInput;
    }

    /**
     *
     */
    public static class Builder {

        /**
         * The setup needed for character creation.
         */
        private final CharacterSetup setup;
        /**
         * The spring application context to find beans.
         */
        private final ApplicationContext context;

        /**
         *
         * @param setupInput the setup object with information of how to create
         * the character.
         * @param contextInput the spring application context to find the beans.
         */
        public Builder(final CharacterSetup setupInput,
                final ApplicationContext contextInput) {
            this.setup = setupInput;
            this.context = contextInput;
        }

        /**
         *
         * @return the newly created character.
         */
        public final DndCharacter build() {
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
     * @param raceInput the race to set
     */
    public void setRace(final Race raceInput) {
        this.race = raceInput;
    }
}
