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
public final class DndCharacter implements ICharacter {

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
            this.mergeBaseAtkBoni(chClass, cLevel);

            // ability increment with level advancement
            if (advance.getAbilityName() == null) {
                continue;
            }
            Ability additionalAbility
                    = builder.context.getBean(
                            advance.getAbilityName(), Ability.class);
            this.abilityMap.put(additionalAbility,
                    this.abilityMap.get(additionalAbility) + 1);

        }

        this.acAction = builder.context.getBean("ac", DiceAction.class);
    }

    /**
     *
     * @param chClass the character class to merge to boni from.
     * @param cLevel the specific level of the given character class.
     */
    private void mergeBaseAtkBoni(final CharacterClass chClass,
            final ClassLevel cLevel) {

        // iteration over all base Atk boni of NEW class level
        for (Bonus newBonus : cLevel.getBaseAtkBoni()) {

            final Long currentRank = newBonus.getRank();
            boolean bonusFound = false;

            // iteration over all accumulated boni of the dnd character to
            // find matches based on rank - then the difference to the previous
            // level's bonus can be added
            for (Bonus existingBonus : this.boni) {
                if (existingBonus.getRank().equals(currentRank)) {

                    Long valueDelta
                            = chClass.getBaseAtkBonusValueDeltaByLevelAndRank(
                                    cLevel, currentRank);

                    existingBonus.setValue(existingBonus.getValue()
                            + valueDelta);

                    bonusFound = true;
                    break;
                }
            }
            // when the rank is not yet present, the whole bonus is added as a
            // clone
            if (!bonusFound) {
                this.boni.add(newBonus.makeCopy());
            }
        }
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
    @Override
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
    @Override
    public Map<Ability, Long> getAbilityMap() {
        return abilityMap;
    }

    /**
     * @param abilityMapInput the abilityMap to set
     */
    @Override
    public void setAbilityMap(final Map<Ability, Long> abilityMapInput) {
        this.abilityMap = abilityMapInput;
    }

    /**
     * @return the classLevels
     */
    @Override
    public List<ClassLevel> getClassLevels() {
        return classLevels;
    }

    /**
     * @param classLevelsInput the classLevels to set
     */
    @Override
    public void setClassLevels(final List<ClassLevel> classLevelsInput) {
        this.classLevels = classLevelsInput;
    }

    /**
     * @return the boni
     */
    @Override
    public List<Bonus> getBoni() {
        return boni;
    }

    /**
     * @param boniInput the boni to set
     */
    @Override
    public void setBoni(final List<Bonus> boniInput) {
        this.boni = boniInput;
    }

    /**
     * @return the acAction
     */
    @Override
    public DiceAction getAcAction() {
        return acAction;
    }

    /**
     * @return the attackAction
     */
    @Override
    public DiceAction getAttackAction() {
        return attackAction;
    }

    /**
     * @return the maxHp
     */
    @Override
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
    @Override
    public Long getAc() {
        return this.acAction.getConstValue()
                + this.bonusService.retrieveEffectiveBonusValueByTarget(
                        this, this, this.acAction);
    }

    /**
     *
     * @return the hp dice action.
     */
    @Override
    public DiceAction getHp() {
        return hp;
    }

    /**
     * @return the baseAttackBonus
     */
    @Override
    public BonusType getBaseAttackBonus() {
        return baseAttackBonus;
    }

    /**
     * @return the classList
     */
    @Override
    public List<CharacterClass> getClassList() {
        return classList;
    }

    /**
     * @param classListInput the classList to set
     */
    @Override
    public void setClassList(final List<CharacterClass> classListInput) {
        this.classList = classListInput;
    }

    /**
     * @return the hpAdditionList
     */
    @Override
    public List<Long> getHpAdditionList() {
        return hpAdditionList;
    }

    /**
     * @param hpAdditionListInput the hpAdditionList to set
     */
    @Override
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
    @Override
    public CharacterSetup getSetup() {
        return setup;
    }

    /**
     * @return the race
     */
    @Override
    public Race getRace() {
        return race;
    }

    /**
     * @param raceInput the race to set
     */
    @Override
    public void setRace(final Race raceInput) {
        this.race = raceInput;
    }
}
