package org.asciicerebrum.mydndgame;

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
    Map<Ability, Long> getAbilityMap();

    /**
     *
     * @return the calculated armor class of this character.
     */
    Long getAc();

    /**
     * @return the acAction
     */
    DiceAction getAcAction();

    /**
     * @return the attackAction
     */
    DiceAction getAttackAction();

    /**
     *
     * @return the list of base attack boni for this character.
     */
    List<Bonus> getBaseAtkBoni();

    /**
     * @return the baseAttackBonus
     */
    BonusType getBaseAttackBonus();

    /**
     * @return the boni
     */
    List<Bonus> getBoni();

    /**
     * @return the classLevels
     */
    List<ClassLevel> getClassLevels();

    /**
     * @return the classList
     */
    List<CharacterClass> getClassList();

    /**
     *
     * @return the hp dice action.
     */
    DiceAction getHp();

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
    Race getRace();

    /**
     * @return the setup
     */
    CharacterSetup getSetup();

    /**
     * @param abilityMapInput the abilityMap to set
     */
    void setAbilityMap(final Map<Ability, Long> abilityMapInput);

    /**
     * @param boniInput the boni to set
     */
    void setBoni(final List<Bonus> boniInput);

    /**
     * @param classLevelsInput the classLevels to set
     */
    void setClassLevels(final List<ClassLevel> classLevelsInput);

    /**
     * @param classListInput the classList to set
     */
    void setClassList(final List<CharacterClass> classListInput);

    /**
     * @param hpAdditionListInput the hpAdditionList to set
     */
    void setHpAdditionList(final List<Long> hpAdditionListInput);

    /**
     * @param raceInput the race to set
     */
    void setRace(final Race raceInput);

}
