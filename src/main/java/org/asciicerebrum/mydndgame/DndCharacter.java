package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;
import org.asciicerebrum.mydndgame.interfaces.services.BonusCalculationService;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asciicerebrum.mydndgame.interfaces.entities.IAbility;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IClass;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.ILevel;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.IRace;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.Slotable;
import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueContext;

/**
 *
 * @author species8472
 */
public final class DndCharacter implements ICharacter, BonusValueContext {

    /**
     * The setup for the character creation.
     */
    private CharacterSetup setup;

    /**
     * The race of this dnd character.
     */
    @BonusGranter
    private IRace race;

    /**
     * The ordered list of classes this character advanced in.
     */
    @BonusGranter
    private List<IClass> classList
            = new ArrayList<IClass>();
    /**
     * The ordered list of additions to the hit points this character acquired
     * during class level advancements.
     */
    private List<Long> hpAdditionList
            = new ArrayList<Long>();
    /**
     * The map of abilities together with their original base values.
     */
    @BonusGranter
    private Map<IAbility, Long> baseAbilityMap
            = new HashMap<IAbility, Long>();
    /**
     * When a level advancement grants an increase in the ability score, this
     * ability is recorded in this list. Later on the effective score is
     * calculated by the original base value from the abilityMap and the number
     * of entries of the same ability within this list.
     */
    private List<IAbility> abilityAdvances = new ArrayList<IAbility>();
    /**
     * The ordered list of class levels this character advanced in.
     */
    @BonusGranter
    private List<ILevel> classLevels = new ArrayList<ILevel>();

    /**
     * The complete collection of all boni which are associated with this
     * character.
     */
    private List<IBonus> boni = new ArrayList<IBonus>();

    //TODO make DndCharacter Spring Prototype and set these values via
    // application context xml
    /**
     * The bonus calculation service needed for dynamic bonus value calculation.
     */
    private BonusCalculationService bonusService;
    /**
     * The diceAction with id hp.
     */
    private DiceAction hp;
    /**
     * The diceAction with id acAction.
     */
    private DiceAction acAction;
    /**
     * The diceAction with id attackAction.
     */
    private DiceAction attackAction;
    /**
     * The diceAction with id meleeAttackAction.
     */
    private DiceAction meleeAttackAction;
    /**
     * The diceAction with id rangedAttackAction.
     */
    private DiceAction rangedAttackAction;
    /**
     * The bonusType with id baseAttackBonus.
     */
    private BonusType baseAttackBonus;
    /**
     * The list of specific body slots provided by the race of the dnd
     * character.
     */
    @BonusGranter
    private List<Slotable> bodySlots = new ArrayList<Slotable>();

    /**
     * The feats of a character.
     */
    private final List<Feat> feats = new ArrayList<Feat>();

    /**
     * Holder for the observer hooks and their corresponding list of observers.
     */
    private final Map<ObserverHook, List<IObserver>> observerMap
            = new EnumMap<ObserverHook, List<IObserver>>(ObserverHook.class);

    /**
     * Register an observer together with its designated hook enum.
     *
     * @param hook the enum hook for the given observer.
     * @param observer the observer object registered for this hook.
     */
    public void registerListener(final ObserverHook hook,
            final IObserver observer) {

        List<IObserver> hookList = this.observerMap.get(hook);
        if (hookList == null) {
            hookList = new ArrayList<IObserver>();
            this.observerMap.put(hook, hookList);
        }
        hookList.add(observer);
    }

    /**
     * Removes an observer from the hook's list.
     *
     * @param hook the enum hook for the given observer.
     * @param observer the observer object that is to be removed.
     */
    public void unregisterListener(final ObserverHook hook,
            final IObserver observer) {

        List<IObserver> hookList = this.observerMap.get(hook);
        if (hookList != null) {
            hookList.remove(observer);
        }
    }

    /**
     * Runs the list of overservers associated with the given hook.
     *
     * @param hook the hook to identify the correct list of observers.
     * @param object the object to modify and return again.
     * @param sitCon the situation context needed to make the correct
     * modifications.
     * @return the modified object.
     */
    private Object triggerObservers(final ObserverHook hook,
            final Object object, final ISituationContext sitCon) {
        List<IObserver> hookList = this.observerMap.get(hook);
        if (hookList == null) {
            return object;
        }
        Object modifiableObject = object;
        for (IObserver observer : hookList) {
            modifiableObject = observer.trigger(modifiableObject, sitCon);
        }
        return modifiableObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Slotable getBodySlotByType(final IBodySlotType bsType) {
        for (Slotable bSlot : this.bodySlots) {
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
     * @param bodySlotType the body slot type to calculate the boni for.
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
        IInventoryItem item = this.getBodySlotByType(bodySlotType).getItem();
        if (item instanceof Weapon) {
            weapon = (Weapon) item;
        }
        // gather all non-weapon-dependent boni for melee attack
        List<IBonus> meleeBoni = this.bonusService
                .traverseBoniByTarget(this, this.meleeAttackAction);

        // post processing of the bonus list, e.g. by registered feat
        // methods. (observer pattern)
        meleeBoni = (List<IBonus>) this.triggerObservers(
                ObserverHook.MELEE_ATTACK, meleeBoni,
                this.generateSituationContext(bodySlotType));

        Long meleeBonusValue = this.bonusService.accumulateBonusValue(
                this, meleeBoni);

        for (IBonus baseAtkBonus : this.getBaseAtkBoni()) {
            atkBoni.add(baseAtkBonus.getEffectiveValue(this) + meleeBonusValue);
        }

        return atkBoni;
    }

    /**
     * Generates a specific situation context by this character and all relevant
     * active entities.
     *
     * @param bsType the body slot type important for context.
     * @return the created situation context.
     */
    private ISituationContext generateSituationContext(
            final IBodySlotType bsType) {
        ISituationContext sitCon = new SituationContext();
        sitCon.setCharacter(this);
        sitCon.setBodySlotType(bsType);
        return sitCon;
    }

    /**
     * Returns the list of boni for the given body slot type. All boni are
     * applied. The weapon in the slot is regarded as a ranged weapon - e.g. you
     * can throw a longsword at somebody.
     *
     * @param bodySlotType the body slot type to calculate the boni for.
     * @return the list of boni.
     */
    public List<Long> getRangedAtkBonus(final BodySlotType bodySlotType) {
        //TODO to be implemented

        return new ArrayList<Long>();
    }

    /**
     *
     * @param charCl The character class which needs to be counted.
     * @return The number of class levels this character has for the given
     * character class.
     */
    public Integer countClassLevelsByCharacterClass(
            final CharacterClass charCl) {

        return Collections.frequency(this.getClassList(), charCl);
    }

    /**
     * The list is rank-ordered from low to high. Its size is equal to the max
     * attack number.
     *
     * @return the list of base attack boni for this character.
     */
    @Override
    public List<IBonus> getBaseAtkBoni() {
        List<IBonus> baseAtkBoni = new ArrayList<IBonus>();

        Long maxSize = this.getMaxAttackNumber();

        for (IBonus potentialBonus : this.getBoni()) {
            if (potentialBonus.getBonusType().equals(
                    this.getBaseAttackBonus())) {
                baseAtkBoni.add(potentialBonus);
            }
        }

        // order the list
        Collections.sort(baseAtkBoni, new Bonus.RankComparator());

        // make a sublist of size maxSize
        baseAtkBoni = baseAtkBoni.subList(0, maxSize.intValue());

        return baseAtkBoni;
    }

    /**
     * @return the classLevels
     */
    @Override
    public List<ILevel> getClassLevels() {
        return classLevels;
    }

    /**
     * @param classLevelsInput the classLevels to set
     */
    @Override
    public void setClassLevels(final List<ILevel> classLevelsInput) {
        this.classLevels = classLevelsInput;
    }

    /**
     * @return the boni
     */
    @Override
    public List<IBonus> getBoni() {
        return boni;
    }

    /**
     * @param boniInput the boni to set
     */
    @Override
    public void setBoni(final List<IBonus> boniInput) {
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
    public List<IClass> getClassList() {
        return classList;
    }

    /**
     * @param classListInput the classList to set
     */
    @Override
    public void setClassList(final List<IClass> classListInput) {
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
    public List<Slotable> getBodySlots() {
        return bodySlots;
    }

    /**
     * @param bodySlotsInput the bodySlots to set
     */
    public void setBodySlots(final List<Slotable> bodySlotsInput) {
        this.bodySlots = bodySlotsInput;
    }

    /**
     * @return the feats
     */
    public List<Feat> getFeats() {
        return feats;
    }

    /**
     * @param bonusServiceInput the bonusService to set
     */
    public void setBonusService(
            final BonusCalculationService bonusServiceInput) {
        this.bonusService = bonusServiceInput;
    }

    /**
     * @param hpInput the hp to set
     */
    public void setHp(final DiceAction hpInput) {
        this.hp = hpInput;
    }

    /**
     * @param acActionInput the acAction to set
     */
    public void setAcAction(final DiceAction acActionInput) {
        this.acAction = acActionInput;
    }

    /**
     * @param attackActionInput the attackAction to set
     */
    public void setAttackAction(final DiceAction attackActionInput) {
        this.attackAction = attackActionInput;
    }

    /**
     * @param meleeAttackActionInput the meleeAttackAction to set
     */
    public void setMeleeAttackAction(final DiceAction meleeAttackActionInput) {
        this.meleeAttackAction = meleeAttackActionInput;
    }

    /**
     * @param baseAttackBonusInput the baseAttackBonus to set
     */
    public void setBaseAttackBonus(final BonusType baseAttackBonusInput) {
        this.baseAttackBonus = baseAttackBonusInput;
    }

    /**
     * @return the rangedAttackAction
     */
    public DiceAction getRangedAttackAction() {
        return rangedAttackAction;
    }

    /**
     * @param rangedAttackActionInput the rangedAttackAction to set
     */
    public void setRangedAttackAction(
            final DiceAction rangedAttackActionInput) {
        this.rangedAttackAction = rangedAttackActionInput;
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
    public IRace getRace() {
        return race;
    }

    /**
     * @param raceInput the race to set
     */
    @Override
    public void setRace(final IRace raceInput) {
        this.race = raceInput;
    }

    /**
     * @param setupInput the setup to set
     */
    public void setSetup(final CharacterSetup setupInput) {
        this.setup = setupInput;
    }

    /**
     * Calculates the number of attacks a dnd character has during a full attack
     * action.
     *
     * @return the maximum number of attacks.
     */
    public Long getMaxAttackNumber() {
        Long maxAttackNumber = 0L;
        for (ILevel cLevel : this.classLevels) {
            if (cLevel.getBaseAtkBoni().size() > maxAttackNumber) {
                maxAttackNumber = Long.valueOf(cLevel.getBaseAtkBoni().size());
            }
        }
        return maxAttackNumber;
    }

    /**
     * @return the baseAbilityMap
     */
    public Map<IAbility, Long> getBaseAbilityMap() {
        return baseAbilityMap;
    }

    /**
     * @param baseAbilityMapInput the baseAbilityMap to set
     */
    public void setBaseAbilityMap(
            final Map<IAbility, Long> baseAbilityMapInput) {
        this.baseAbilityMap = baseAbilityMapInput;
    }

    /**
     * @return the abilityAdvances
     */
    public List<IAbility> getAbilityAdvances() {
        return abilityAdvances;
    }

    /**
     * @param abilityAdvancesInput the abilityAdvances to set
     */
    public void setAbilityAdvances(final List<IAbility> abilityAdvancesInput) {
        this.abilityAdvances = abilityAdvancesInput;
    }
}
