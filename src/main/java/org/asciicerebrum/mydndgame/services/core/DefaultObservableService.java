package org.asciicerebrum.mydndgame.services.core;

import org.asciicerebrum.mydndgame.services.core.accumulator.observer.ObserverAccumulatorStrategies;
import org.asciicerebrum.mydndgame.services.core.accumulator.observer.ObserverAccumulatorStrategy;
import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.observers.Observers;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;

/**
 *
 * @author species8472
 */
public class DefaultObservableService implements ObservableService {

    /**
     * Collections of all availabe observer accumulator strategies. This is used
     * to determine the entry point for the accumulation process.
     */
    private ObserverAccumulatorStrategies accumulatorStrategies;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object triggerObservers(final Object observerTarget,
            final Observers observers,
            final DndCharacter dndCharacter) {

        return observers.trigger(observerTarget, dndCharacter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object triggerObservers(final Object observerTarget,
            final UniqueEntity targetEntity,
            final ObserverSources observerSources,
            final ObserverHooks observerHooks,
            final DndCharacter dndCharacter) {

        final Observers observers = this.accumulateObserversByHooks(
                observerSources, observerHooks, targetEntity);
        return this.triggerObservers(
                observerTarget, observers, dndCharacter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Observers accumulateObservers(
            final ObserverSources observerSources,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();

        final Iterator<ObserverSource> iterator
                = observerSources.iterator();
        while (iterator.hasNext()) {
            final ObserverSource observerSource = iterator.next();
            observers.add(this.accumulateObservers(observerSource,
                    targetEntity));
        }

        return observers;
    }

    /**
     * {@inheritDoc} Observers come in two scopes: observers that target
     * everything (ALL) and observers that target only the unique entity that
     * they are part of (SPECIFIC). So when accumulating these observers only
     * the ALL and SPECIFIC with the given unique entity as the specificum must
     * be taken into account!!!
     */
    @Override
    public final Observers accumulateObservers(
            final ObserverSource observerSource,
            final UniqueEntity targetEntity) {

        // find the starting point of all the accumulator strategies
        final ObserverAccumulatorStrategy strategy
                = this.accumulatorStrategies.findForSource(observerSource);

        return strategy.getObservers(observerSource, targetEntity);
    }

    @Override
    public final Observers accumulateObserversByHook(
            final ObserverSource observerSource,
            final ObserverHook observerHook,
            final UniqueEntity targetEntity) {
        return this.accumulateObservers(observerSource, targetEntity)
                .filterByHook(observerHook);
    }

    @Override
    public final Observers accumulateObserversByHooks(
            final ObserverSources observerSources,
            final ObserverHooks observerHooks,
            final UniqueEntity targetEntity) {
        return this.accumulateObservers(observerSources, targetEntity)
                .filterByHooks(observerHooks);
    }

    /**
     * @param accumulatorStrategiesInput the accumulatorStrategies to set
     */
    public final void setAccumulatorStrategies(
            final ObserverAccumulatorStrategies accumulatorStrategiesInput) {
        this.accumulatorStrategies = accumulatorStrategiesInput;
    }

}
