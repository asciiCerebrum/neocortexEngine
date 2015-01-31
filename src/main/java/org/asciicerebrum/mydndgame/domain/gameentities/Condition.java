package org.asciicerebrum.mydndgame.domain.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.ConditionType;
import org.asciicerebrum.mydndgame.domain.core.attribution.WorldDate;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;

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
    private UniqueEntity causeEntity;

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

    /**
     * @return the causeEntity
     */
    public final UniqueEntity getCauseEntity() {
        return causeEntity;
    }

    /**
     * @param causeEntityInput the causeEntity to set
     */
    public final void setCauseEntity(final UniqueEntity causeEntityInput) {
        this.causeEntity = causeEntityInput;
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        bonusSources.add(this.conditionType);

        return bonusSources;
    }

}
