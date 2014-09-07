package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICondition;
import org.asciicerebrum.mydndgame.interfaces.entities.IConditionType;

/**
 *
 * @author species8472
 */
public class Condition implements ICondition {

    /**
     * The unique id of this condition.
     */
    private String id;

    /**
     * What kind of condition is active.
     */
    private IConditionType conditionType;

    /**
     * The world time (rounds) when the effect begins.
     */
    private Long startingTime;

    /**
     * How many rounds does the effect last.
     */
    private Long duration;

    /**
     * Who is responsible for the effect of this condition.
     */
    private ICharacter causeCharacter;

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IConditionType getConditionType() {
        return conditionType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setConditionType(
            final IConditionType conditionTypeInput) {
        this.conditionType = conditionTypeInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getStartingTime() {
        return startingTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setStartingTime(final Long startingTimeInput) {
        this.startingTime = startingTimeInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getDuration() {
        return duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setDuration(Long durationInput) {
        this.duration = durationInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ICharacter getCauseCharacter() {
        return causeCharacter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setCauseCharacter(final ICharacter causeCharacterInput) {
        this.causeCharacter = causeCharacterInput;
    }
}
