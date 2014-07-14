package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
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
     * The diceAction with id meleeAttackAction.
     */
    private final DiceAction meleeAttackAction;
    /**
     * The bonusType with id baseAttackBonus.
     */
    private final BonusType baseAttackBonus;
    /**
     * The list of specific body slots provided by the race of the dnd
     * character.
     */
    @BonusGranter
    private List<BodySlot> bodySlots = new ArrayList<BodySlot>();

    /**
     * The feats of a character.
     */
    private final List<Feat> feats = new ArrayList<Feat>();

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
        this.meleeAttackAction = builder.context.getBean("meleeAttack",
                DiceAction.class);

        this.race = builder.context.getBean(
                builder.setup.getRace(), Race.class);
        // setup of body slots by the race
        for (BodySlotType bsType : this.race.getProvidedBodySlotTypes()) {
            BodySlot bs = new BodySlot();
            bs.setBodySlotType(bsType);
            this.getBodySlots().add(bs);
        }

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
            if (StringUtils.isNotBlank(advance.getAbilityName())) {
                Ability additionalAbility
                        = builder.context.getBean(
                                advance.getAbilityName(), Ability.class);
                this.abilityMap.put(additionalAbility,
                        this.abilityMap.get(additionalAbility) + 1);
            }

            // adding feats
            if (StringUtils.isNotBlank(advance.getFeatName())) {
                Feat feat = builder.context.getBean(
                        advance.getFeatName(), Feat.class);
                this.feats.add(feat);
            }
        }

        // adding possessions
        for (Entry<String, String> posEntry
                : builder.setup.getPossessionContainer().entrySet()) {

            BodySlot slot = this.getBodySlotByType(
                    builder.context.getBean(posEntry.getValue(),
                            BodySlotType.class));
            InventoryItem item = builder.context.getBean(posEntry.getKey(),
                    InventoryItem.class);
            if (slot != null) {
                slot.setItem(item);
                //TODO throw exception if body slot is null
            }
        }

        this.acAction = builder.context.getBean("ac", DiceAction.class);
    }

    /**
     * Finds the body slot by its type.
     *
     * @param bsType the type of the body slot.
     * @return the found body slot or null if nothing was found.
     */
    private BodySlot getBodySlotByType(final BodySlotType bsType) {
        for (BodySlot bSlot : this.bodySlots) {
            if (bSlot.getBodySlotType().equals(bsType)) {
                return bSlot;
            }
        }
        return null;
    }

    /**
     * Returns the list of boni for the given body slot type. All boni are
     * applied. The weapon in the slot is regarded as a melee weapon - e.g. you
     * can hit someone with a bow.
     *
     * @param bodySlotType
     * @return the list of boni.
     */
    public List<Long> getMeleeAtkBonus(final BodySlotType bodySlotType) {
        // TODO get body slot by body slot type
        // is there a weapon in this slot?
        // is it ranged or melee? - no if-construct here. let the weapon
        // category decide and associate the bonus to it (Dex for ranged, Str
        // for melee)
        // collect all boni associated with attack
        // collect all boni associated with meleeattack/rangedattack, depending
        // on the weapon category in this slot
        // IMPORTANT: consider only this weapon's boni as the other weapon's
        // boni are irrelevant in this case!!!

        // KEEP THE FOLLOWING USE CASES IN MIND:
        // 1st: Feat Weapon Finesse: With a light weapon, rapier, whip, or
        // spiked chain made for a creature of your size category, you may use
        // your Dexterity modifier instead of your Strength modifier on attack
        // rolls. If you carry a shield, its armor check penalty applies to
        // your attack rolls.
        //
        // 2nd: Feat Power Attack: On your action, before making attack rolls
        // for a round, you may choose to subtract a number from all melee
        // attack rolls and add the same number to all melee damage rolls. This
        // number may not exceed your base attack bonus. The penalty on attacks
        // and bonus on damage apply until your next turn.
        //
        // 3rd: Masterwork weapon: Wielding it provides a +1 enhancement bonus
        // on attack rolls. This also affects the price of the weapon!
        //
        // 4th: a thrown dagger in your secondary hand (which is actually a
        // melee weapon). I could also hit someone with a bow. Could this be
        // solved by differentiating two methods: getMeleeAtkBonus() /
        // getRangedAtkBonus() and I as a player have to decide what to do with
        // the weapon in my hand (attack in melee or ranged)?
        List<Long> atkBoni = new ArrayList<Long>();

        Weapon weapon = null;
        InventoryItem item = this.getBodySlotByType(bodySlotType).getItem();
        if (item instanceof Weapon) {
            weapon = (Weapon) item;
        }
        // gather all non-weapon-dependent boni for melee attack
        List<Bonus> meleeBoni = this.bonusService
                .traverseBoniByTarget(this, this.meleeAttackAction);

        //TODO post processing of the bonus list, e.g. by registered feat
        // methods. (observer pattern)
        
        
        Long meleeBonusValue = this.bonusService.accumulateBonusValue(
                this, meleeBoni);

        for (Bonus baseAtkBonus : this.getBaseAtkBoni()) {
            atkBoni.add(baseAtkBonus.getValue() + meleeBonusValue);
        }

        return atkBoni;
    }

    /**
     * Returns the list of boni for the given body slot type. All boni are
     * applied. The weapon in the slot is regarded as a ranged weapon - e.g. you
     * can throw a longsword at somebody.
     *
     * @param bodySlotType
     * @return the list of boni.
     */
    public List<Long> getRangedAtkBonus(final BodySlotType bodySlotType) {
        //TODO to be implemented

        return new ArrayList<Long>();
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
     * @return the bodySlots
     */
    public List<BodySlot> getBodySlots() {
        return bodySlots;
    }

    /**
     * @param bodySlotsInput the bodySlots to set
     */
    public void setBodySlots(final List<BodySlot> bodySlotsInput) {
        this.bodySlots = bodySlotsInput;
    }

    /**
     * @return the feats
     */
    public List<Feat> getFeats() {
        return feats;
    }

    /**
     * Builder for the character.
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
            DndCharacter dndCharacter = new DndCharacter(this);

            // post processing - all that cannot be done in the constructor.
            // backreference for body slots to their owner
            for (BodySlot bs : dndCharacter.getBodySlots()) {
                bs.setHolder(dndCharacter);
            }

            return dndCharacter;
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
