package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class ConditionSetup {

    /**
     * Unique identification of the condition effect.
     */
    private String id;

    /**
     * Name of the condition type (spring bean id).
     */
    private String name;

    /**
     * Starting date as round.
     */
    private Long startingRoundNumber;

    /**
     * Starting date within the round.
     */
    private String startingRoundPosition;

    /**
     * Expiry date as round.
     */
    private Long expiryRoundNumber;

    /**
     * Expiry date within the round.
     */
    private String expiryRoundPosition;

    /**
     * Character id responsible for the effect.
     */
    private String causeCharacterId;

    /**
     * Id of the characters affected by the condition.
     */
    private String affectedCharacterId;

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param nameInput the name to set
     */
    public final void setName(final String nameInput) {
        this.name = nameInput;
    }

    /**
     * @return the causeCharacterId
     */
    public final String getCauseCharacterId() {
        return causeCharacterId;
    }

    /**
     * @param causeCharacterIdInput the causeCharacterId to set
     */
    public final void setCauseCharacterId(final String causeCharacterIdInput) {
        this.causeCharacterId = causeCharacterIdInput;
    }

    /**
     * @return the affectedCharacterId
     */
    public final String getAffectedCharacterId() {
        return affectedCharacterId;
    }

    /**
     * @param affectedCharacterIdInput the affectedCharacterIds to set
     */
    public final void setAffectedCharacterId(
            final String affectedCharacterIdInput) {
        this.affectedCharacterId = affectedCharacterIdInput;
    }

    /**
     * @return the startingRoundNumber
     */
    public final Long getStartingRoundNumber() {
        return startingRoundNumber;
    }

    /**
     * @param startingRoundNumberInput the startingRoundNumber to set
     */
    public final void setStartingRoundNumber(
            final Long startingRoundNumberInput) {
        this.startingRoundNumber = startingRoundNumberInput;
    }

    /**
     * @return the startingRoundPosition
     */
    public final String getStartingRoundPosition() {
        return startingRoundPosition;
    }

    /**
     * @param startingRoundPositionInput the startingRoundPosition to set
     */
    public final void setStartingRoundPosition(
            final String startingRoundPositionInput) {
        this.startingRoundPosition = startingRoundPositionInput;
    }

    /**
     * @return the expiryRoundNumber
     */
    public final Long getExpiryRoundNumber() {
        return expiryRoundNumber;
    }

    /**
     * @param expiryRoundNumberInput the expiryRoundNumber to set
     */
    public final void setExpiryRoundNumber(final Long expiryRoundNumberInput) {
        this.expiryRoundNumber = expiryRoundNumberInput;
    }

    /**
     * @return the expiryRoundPosition
     */
    public final String getExpiryRoundPosition() {
        return expiryRoundPosition;
    }

    /**
     * @param expiryRoundPositionInput the expiryRoundPosition to set
     */
    public final void setExpiryRoundPosition(
            final String expiryRoundPositionInput) {
        this.expiryRoundPosition = expiryRoundPositionInput;
    }

}
