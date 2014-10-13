package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICondition;
import org.asciicerebrum.mydndgame.interfaces.entities.IConditionType;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorldDate;

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
     * Who is responsible for the effect of this condition.
     */
    private ICharacter causeCharacter;

    /**
     * Beginning date of this condition.
     */
    private IWorldDate startingDate;

    /**
     * Ending date of this condition.
     */
    private IWorldDate expiryDate;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public final IWorldDate getStartingDate() {
        return this.startingDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setStartingDate(final IWorldDate startingDateInput) {
        this.startingDate = startingDateInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IWorldDate getExpiryDate() {
        return this.expiryDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setExpiryDate(final IWorldDate expiryDateInput) {
        this.expiryDate = expiryDateInput;
    }
}
