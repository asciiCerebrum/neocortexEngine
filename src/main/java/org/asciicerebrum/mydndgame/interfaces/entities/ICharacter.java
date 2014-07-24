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
     *
     * @return the calculated armor class of this character.
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

}
