package org.asciicerebrum.mydndgame.domain.rules;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public class ConditionType implements BonusSource, ObserverSource {

    /**
     * The unique id of this condition type.
     */
    private UniqueId id;
    /**
     * The observers of this condition. They are designed to be registered in
     * the targeted character to modify certain values of all kinds of
     * attributes.
     */
    private Observers observers;

    private Boni boni;

    /**
     * @return the id
     */
    public UniqueId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UniqueId id) {
        this.id = id;
    }

    /**
     * @return the observers
     */
    public final Observers getObservers() {
        return observers;
    }

    /**
     * @param observers the observers to set
     */
    public void setObservers(Observers observers) {
        this.observers = observers;
    }

    /**
     * @return the boni
     */
    @Override
    public final Boni getBoni() {
        return boni;
    }

    /**
     * @param boni the boni to set
     */
    public void setBoni(Boni boni) {
        this.boni = boni;
    }

    @Override
    public final BonusSources getBonusSources() {
        return BonusSources.EMPTY_BONUSSOURCES;
    }

}