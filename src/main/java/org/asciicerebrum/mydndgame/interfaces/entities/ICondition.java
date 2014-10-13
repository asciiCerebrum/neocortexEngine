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
     * @return the starting date.
     */
    IWorldDate getStartingDate();

    /**
     * @param startingDate the startingDate to set
     */
    void setStartingDate(IWorldDate startingDate);

    /**
     * @return the expiry date.
     */
    IWorldDate getExpiryDate();

    /**
     * @param expiryDate the expiryDate to set
     */
    void setExpiryDate(IWorldDate expiryDate);

    /**
     * @return the causeCharacter
     */
    ICharacter getCauseCharacter();

    /**
     * @param causeCharacter the causeCharacter to set
     */
    void setCauseCharacter(ICharacter causeCharacter);

}
