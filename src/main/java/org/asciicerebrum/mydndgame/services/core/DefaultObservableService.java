package org.asciicerebrum.mydndgame.services.core;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observer;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.services.core.accumulator.observer.ObserverAccumulatorStrategies;
import org.asciicerebrum.mydndgame.services.core.accumulator.observer.ObserverAccumulatorStrategy;

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
            final UniqueEntity targetEntity,
            final Observers observers,
            final DndCharacter dndCharacter) {

        final Iterator<Observer> observerIterator
                = observers.iterator();
        Object modificatedObject = observerTarget;
        while (observerIterator.hasNext()) {
            final Observer observer = observerIterator.next();
            if (observer.getConditionEvaluator() != null
                    && observer.getConditionEvaluator()
                    .evaluate(dndCharacter, targetEntity)) {
                modificatedObject = observer.getTriggerStrategy()
                        .trigger(observerTarget, dndCharacter, targetEntity);
            }
        }
        return modificatedObject;
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
                observerTarget, targetEntity, observers, dndCharacter);
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
                = this.getAccumulatorStrategies().findForSource(observerSource);

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

    /**
     * @return the accumulatorStrategies
     */
    public final ObserverAccumulatorStrategies getAccumulatorStrategies() {
        return accumulatorStrategies;
    }

}
