package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.rules.composition.LevelAdvancement;
import org.asciicerebrum.mydndgame.domain.rules.composition.LevelAdvancements;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.impl.Observers;

/**
 *
 * @author species8472
 */
public class LevelAdvancementsObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * Accumulator strategy for the single level advancements of the collection.
     */
    private ObserverAccumulatorStrategy lvlAdvStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        final LevelAdvancements lvlAdvs = (LevelAdvancements) observerSource;
        final Iterator<LevelAdvancement> lvlIterator = lvlAdvs.iterator();

        while (lvlIterator.hasNext()) {
            final LevelAdvancement lvl = lvlIterator.next();
            observers.add(this.lvlAdvStrategy.getObservers(lvl, targetEntity));
        }

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof LevelAdvancements;
    }

    /**
     * @param lvlAdvStrategyInput the lvlAdvStrategy to set
     */
    public final void setLvlAdvStrategy(
            final ObserverAccumulatorStrategy lvlAdvStrategyInput) {
        this.lvlAdvStrategy = lvlAdvStrategyInput;
    }

}
