package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface ICondition extends Identifiable {

    /**
     * @return the conditionType
     */
    IConditionType getConditionType();

    /**
     * @param conditionType the conditionType to set
     */
    void setConditionType(IConditionType conditionType);

    /**
     * @return the startingTime
     */
    Long getStartingTime();

    /**
     * @param startingTime the startingTime to set
     */
    void setStartingTime(Long startingTime);

    /**
     * @return the duration
     */
    Long getDuration();

    /**
     * @param duration the duration to set
     */
    void setDuration(Long duration);

    /**
     * @return the causeCharacter
     */
    ICharacter getCauseCharacter();

    /**
     * @param causeCharacter the causeCharacter to set
     */
    void setCauseCharacter(ICharacter causeCharacter);

}
