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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class DefaultObservableService implements ObservableService {

    /**
     * The logger instance.
     */
    private static final Logger LOG
            = LoggerFactory.getLogger(DefaultObservableService.class);

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
        int i = 1;
        while (observerIterator.hasNext()) {
            final Observer observer = observerIterator.next();
            LOG.debug("{} - Check observer {} for triggering.", i++,
                    observer.getTriggerStrategy().getClass().getSimpleName());
            if (observer.getConditionEvaluator() == null
                    || observer.getConditionEvaluator()
                    .evaluate(dndCharacter, targetEntity)) {

                LOG.debug("Now triggering for {}: {}.",
                        dndCharacter.getUniqueId().getValue(),
                        observer.getTriggerStrategy().getClass()
                        .getSimpleName());

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

        LOG.debug("Found a total of {} observers.", observers.size());

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

        LOG.debug("Found accumulation strategy {} of observer source {}.",
                new Object[]{strategy.getClass().getSimpleName(),
                    observerSource.getClass().getSimpleName()});

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
        LOG.info("Accumulating observers by the hooks {}.",
                observerHooks.toString());
        final Observers filteredObservers
                = this.accumulateObservers(observerSources, targetEntity)
                .filterByHooks(observerHooks);
        LOG.info("Hook filtering reduced to {}.", filteredObservers.size());
        return filteredObservers;
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
