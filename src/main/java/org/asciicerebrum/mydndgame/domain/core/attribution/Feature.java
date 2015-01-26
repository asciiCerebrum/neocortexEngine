package org.asciicerebrum.mydndgame.domain.core.attribution;

import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.observers.Observers;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public abstract class Feature implements BonusSource, ObserverSource {

    /**
     * The unique identifier of the feature.
     */
    private UniqueId uniqueId;

    /**
     * The list of quasi static boni that are granted by this feature.
     */
    private Boni boni;

    /**
     * The list of observers that hook into the entity to modify certain
     * attributes dynamically.
     */
    private Observers observers;

    /**
     * @return the uniqueId
     */
    public final UniqueId getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueIdInput the uniqueId to set
     */
    public final void setUniqueId(final UniqueId uniqueIdInput) {
        this.uniqueId = uniqueIdInput;
    }

    /**
     * @return the boni
     */
    @Override
    public final Boni getBoni() {
        return boni;
    }

    /**
     * @param boniInput the boni to set
     */
    public final void setBoni(final Boni boniInput) {
        this.boni = boniInput;
    }

    /**
     * @return the observers
     */
    @Override
    public final Observers getObservers() {
        return observers;
    }

    /**
     * @param observersInput the observers to set
     */
    public final void setObservers(final Observers observersInput) {
        this.observers = observersInput;
    }

    @Override
    public BonusSources getBonusSources() {
        return BonusSources.EMPTY_BONUSSOURCES;
    }

    @Override
    public ObserverSources getObserverSources() {
        return ObserverSources.EMPTY_OBSERVERSOURCES;
    }

}