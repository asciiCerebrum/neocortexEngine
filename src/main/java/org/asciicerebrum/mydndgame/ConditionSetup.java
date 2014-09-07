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
     * Starting time as round.
     */
    private Long startingTime;

    /**
     * Duration of the effect in rounds.
     */
    private Long duration;

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
     * @return the startingTime
     */
    public final Long getStartingTime() {
        return startingTime;
    }

    /**
     * @param startingTimeInput the startingTime to set
     */
    public final void setStartingTime(final Long startingTimeInput) {
        this.startingTime = startingTimeInput;
    }

    /**
     * @return the duration
     */
    public final Long getDuration() {
        return duration;
    }

    /**
     * @param durationInput the duration to set
     */
    public final void setDuration(final Long durationInput) {
        this.duration = durationInput;
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

}
