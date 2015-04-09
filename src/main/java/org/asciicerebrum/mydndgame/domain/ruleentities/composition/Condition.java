package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.ruleentities.ConditionType;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public class Condition implements BonusSource, ObserverSource {

    /**
     * What kind of condition is active.
     */
    private ConditionType conditionType;

    /**
     * Who is responsible for the effect of this condition.
     */
    private UniqueId causeEntityId;

    /**
     * Beginning date of this condition.
     */
    private WorldDate startingDate;

    /**
     * Ending date of this condition.
     */
    private WorldDate expiryDate;

    /**
     * @return the conditionType
     */
    public final ConditionType getConditionType() {
        return conditionType;
    }

    /**
     * @param conditionTypeInput the conditionType to set
     */
    public final void setConditionType(final ConditionType conditionTypeInput) {
        this.conditionType = conditionTypeInput;
    }

    /**
     * @return the startingDate
     */
    public final WorldDate getStartingDate() {
        return startingDate;
    }

    /**
     * @param startingDateInput the startingDate to set
     */
    public final void setStartingDate(final WorldDate startingDateInput) {
        this.startingDate = startingDateInput;
    }

    /**
     * @return the expiryDate
     */
    public final WorldDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDateInput the expiryDate to set
     */
    public final void setExpiryDate(final WorldDate expiryDateInput) {
        this.expiryDate = expiryDateInput;
    }

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni ctxBoni = new ContextBoni();
        ctxBoni.add(this.getConditionType().getBoni(context, resolver));
        return ctxBoni;
    }

    /**
     * @return the causeEntityId
     */
    public final UniqueId getCauseEntityId() {
        return causeEntityId;
    }

    /**
     * @param causeEntityIdInput the causeEntityId to set
     */
    public final void setCauseEntityId(final UniqueId causeEntityIdInput) {
        this.causeEntityId = causeEntityIdInput;
    }

}
