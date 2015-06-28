package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import java.util.Iterator;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.LevelAdvancements;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class LevelAdvancementsObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * The logger instance.
     */
    private static final Logger LOG
            = LoggerFactory.getLogger(
                    LevelAdvancementsObserverAccumulatorStrategy.class);

    /**
     * Accumulator strategy for the single level advancements of the collection.
     */
    private ObserverAccumulatorStrategy lvlAdvStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        if (!(observerSource instanceof LevelAdvancements)) {
            return observers;
        }
        final LevelAdvancements lvlAdvs = (LevelAdvancements) observerSource;
        final Iterator<LevelAdvancement> lvlIterator = lvlAdvs.iterator();

        LOG.debug("Accumulating over {} level advancements for target {}.",
                lvlAdvs.size(), targetEntity.getUniqueId().getValue());

        while (lvlIterator.hasNext()) {
            final LevelAdvancement lvl = lvlIterator.next();
            observers.add(this.getLvlAdvStrategy()
                    .getObservers(lvl, targetEntity));
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

    /**
     * @return the lvlAdvStrategy
     */
    public final ObserverAccumulatorStrategy getLvlAdvStrategy() {
        return lvlAdvStrategy;
    }

}
