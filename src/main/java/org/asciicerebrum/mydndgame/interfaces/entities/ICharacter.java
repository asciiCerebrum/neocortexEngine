package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;
import java.util.Map;

/**
 *
 * @author species8472
 */
public interface ICharacter extends BonusSource {

    /**
     * @return the abilityMap
     */
    Map<IAbility, Long> getBaseAbilityMap();

    /**
     * This method is for statistical purpose only. What would be the ideal AC
     * of this character if nothing else (no special negative conditions, etc.)
     * would apply.
     *
     * @return the calculated standard armor class of this character.
     */
    Long getAcStandard();

    /**
     * This method is for statistcial purpose only. What would be AC be if the
     * character was flat-footed and nothing else would apply.
     *
     * @return the armor class under the condition flat-footed.
     */
    Long getAcFlatFooted();

    /**
     * This method is for statistical purpose only. What would the AC be if it
     * was a touch attack and nothing else would apply.
     *
     * @return the armor class when experiencing a touch attack.
     */
    Long getAcTouch();

    /**
     * It is this method that matters in this situation of being attacked - here
     * all real boni and conditions apply.
     *
     * @return the calculated armor class of this character depending on the
     * attack of the opponent.
     */
    Long getAc();

    /**
     * @return the acAction
     */
    IDiceAction getAcAction();

    /**
     * @return the attackAction
     */
    IDiceAction getAttackAction();

    /**
     *
     * @return the list of base attack boni for this character.
     */
    List<IBonus> getBaseAtkBoni();

    /**
     * @return the baseAttackBonus
     */
    IBonusType getBaseAttackBonus();

    /**
     * @return the boni
     */
    List<IBonus> getBoni();

    /**
     * @return the classLevels
     */
    List<ILevel> getClassLevels();

    /**
     * @return the classList
     */
    List<IClass> getClassList();

    /**
     *
     * @return the hp dice action.
     */
    IDiceAction getHp();

    /**
     * @return the hpAdditionList
     */
    List<Long> getHpAdditionList();

    /**
     * @return the maxHp
     */
    Long getMaxHp();

    /**
     * @return the race
     */
    IRace getRace();

    /**
     * @return the setup
     */
    ICharacterSetup getSetup();

    /**
     * @param abilityMapInput the abilityMap to set
     */
    void setBaseAbilityMap(final Map<IAbility, Long> abilityMapInput);

    /**
     * @param boniInput the boni to set
     */
    void setBoni(final List<IBonus> boniInput);

    /**
     * @param classLevelsInput the classLevels to set
     */
    void setClassLevels(final List<ILevel> classLevelsInput);

    /**
     * @param classListInput the classList to set
     */
    void setClassList(final List<IClass> classListInput);

    /**
     * @param hpAdditionListInput the hpAdditionList to set
     */
    void setHpAdditionList(final List<Long> hpAdditionListInput);

    /**
     * @param raceInput the race to set
     */
    void setRace(final IRace raceInput);

    /**
     *
     * @return the list of ability advancements.
     */
    List<IAbility> getAbilityAdvances();

    /**
     *
     * @param abilityAdvancesInput the complete list of advancements in
     * abilities.
     */
    void setAbilityAdvances(List<IAbility> abilityAdvancesInput);

    /**
     * Finds the body slot by its type.
     *
     * @param bsType the type of the body slot.
     * @return the found body slot or null if nothing was found.
     */
    Slotable getBodySlotByType(IBodySlotType bsType);

    /**
     * @param setup the setup to set
     */
    void setSetup(ICharacterSetup setup);

    /**
     * @return the bodySlots
     */
    List<Slotable> getBodySlots();

    /**
     *
     * @param charCl The character class which needs to be counted.
     * @return The number of class levels this character has for the given
     * character class.
     */
    Integer countClassLevelsByCharacterClass(IClass charCl);

    /**
     * @return the feats
     */
    List<IFeat> getFeats();

    /**
     * Calculates the number of attacks a dnd character has during a full attack
     * action.
     *
     * @return the maximum number of attacks.
     */
    Long getMaxAttackNumber();

    /**
     * Returns the list of boni for the given body slot type. All boni are
     * applied. The weapon in the slot is regarded as a melee weapon - e.g. you
     * can hit someone with a bow.
     *
     * @param bodySlotType the body slot type to calculate the boni for.
     * @return the list of boni.
     */
    List<Long> getMeleeAtkBonus(IBodySlotType bodySlotType);

    /**
     * Returns the list of boni for the given body slot type. All boni are
     * applied. The weapon in the slot is regarded as a ranged weapon - e.g. you
     * can throw a longsword at somebody.
     *
     * @param bodySlotType the body slot type to calculate the boni for.
     * @return the list of boni.
     */
    List<Long> getRangedAtkBonus(IBodySlotType bodySlotType);

    /**
     * Returns the damage bonus value for the weapon in the body slot of the
     * given type when used in melee mode. E.g. hitting someone with a bow.
     *
     * @param bodySlotType the body slot type holding the weapon.
     * @return the effective bonus value.
     */
    Long getMeleeDamageBonus(IBodySlotType bodySlotType);

    /**
     * Returns the damage bonus value for the weapon in the body slot of the
     * given type when used in ranged mode. E.g. shooting an arrow with a bow.
     *
     * @param bodySlotType the body slot type holding the weapon.
     * @return the effective bonus value.
     */
    Long getRangedDamageBonus(IBodySlotType bodySlotType);

    /**
     * Generate a really simple situation context object. This is suitable if
     * the character is not attacking and all attack values are of no interest.
     *
     * @return the created situation context.
     */
    ISituationContext generateSituationContextSimple();

    /**
     * @return the observers
     */
    List<IObserver> getObservers();

    /**
     * @param observers the observers to set
     */
    void setObservers(List<IObserver> observers);

    /**
     * Gets the list of worn armor. Only armor which is worn according to its
     * designated body slot types is taken into account.
     *
     * @return the list of worn armor.
     */
    List<IArmor> getWieldedArmor();
}
