package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;

/**
 *
 * @author species8472
 */
public class DndCharacterObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * The observer accumulator strategy for the base abilities.
     */
    private ObserverAccumulatorStrategy baseAbilitiesStrategy;

    /**
     * The observer accumulator strategy for the personalized body slots.
     */
    private ObserverAccumulatorStrategy personalizedBodySlotsStrategy;

    /**
     * The observer accumulator strategy for the conditions.
     */
    private ObserverAccumulatorStrategy conditionsStrategy;

    /**
     * The observer accumulator strategy for the level advancements.
     */
    private ObserverAccumulatorStrategy levelAdvancementsStrategy;

    /**
     * The observer accumulator strategy for the prototype special abilities.
     */
    private ObserverAccumulatorStrategy prototypeSpecialAbilitiesStrategy;

    /**
     * The observer accumulator strategy for the race.
     */
    private ObserverAccumulatorStrategy raceStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();

        if (!(observerSource instanceof DndCharacter)) {
            return observers;
        }

        final DndCharacter dndCharacter = (DndCharacter) observerSource;

        observers.add(this.getBaseAbilitiesStrategy().getObservers(
                dndCharacter.getBaseAbilities(), targetEntity));
        observers.add(this.getPersonalizedBodySlotsStrategy().getObservers(
                dndCharacter.getPersonalizedBodySlots(), targetEntity));
        observers.add(this.getConditionsStrategy().getObservers(
                dndCharacter.getConditions(), targetEntity));
        observers.add(this.getLevelAdvancementsStrategy().getObservers(
                dndCharacter.getLevelAdvancements(), targetEntity));
        observers.add(this.getRaceStrategy().getObservers(
                dndCharacter.getRace(), targetEntity));
        observers.add(this.getPrototypeSpecialAbilitiesStrategy().getObservers(
                dndCharacter.getPrototypeSpecialAbilities(), targetEntity));

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof DndCharacter;
    }

    /**
     * @param baseAbilitiesStrategyInput the baseAbilitiesStrategy to set
     */
    public final void setBaseAbilitiesStrategy(
            final ObserverAccumulatorStrategy baseAbilitiesStrategyInput) {
        this.baseAbilitiesStrategy = baseAbilitiesStrategyInput;
    }

    /**
     * @param conditionsStrategyInput the conditionsStrategy to set
     */
    public final void setConditionsStrategy(
            final ObserverAccumulatorStrategy conditionsStrategyInput) {
        this.conditionsStrategy = conditionsStrategyInput;
    }

    /**
     * @param levelAdvancementsStrategyInput the levelAdvancementsStrategy to
     * set
     */
    public final void setLevelAdvancementsStrategy(
            final ObserverAccumulatorStrategy levelAdvancementsStrategyInput) {
        this.levelAdvancementsStrategy = levelAdvancementsStrategyInput;
    }

    /**
     * @param raceStrategyInput the raceStrategy to set
     */
    public final void setRaceStrategy(
            final ObserverAccumulatorStrategy raceStrategyInput) {
        this.raceStrategy = raceStrategyInput;
    }

    /**
     * @param personalizedBodySlotsStrategyIn the personalizedBodySlotsStrategy
     * to set
     */
    public final void setPersonalizedBodySlotsStrategy(
            final ObserverAccumulatorStrategy personalizedBodySlotsStrategyIn) {
        this.personalizedBodySlotsStrategy = personalizedBodySlotsStrategyIn;
    }

    /**
     * @return the baseAbilitiesStrategy
     */
    public final ObserverAccumulatorStrategy getBaseAbilitiesStrategy() {
        return baseAbilitiesStrategy;
    }

    /**
     * @return the personalizedBodySlotsStrategy
     */
    public final ObserverAccumulatorStrategy
            getPersonalizedBodySlotsStrategy() {
        return personalizedBodySlotsStrategy;
    }

    /**
     * @return the conditionsStrategy
     */
    public final ObserverAccumulatorStrategy getConditionsStrategy() {
        return conditionsStrategy;
    }

    /**
     * @return the levelAdvancementsStrategy
     */
    public final ObserverAccumulatorStrategy getLevelAdvancementsStrategy() {
        return levelAdvancementsStrategy;
    }

    /**
     * @return the raceStrategy
     */
    public final ObserverAccumulatorStrategy getRaceStrategy() {
        return raceStrategy;
    }

    /**
     * @return the prototypeSpecialAbilitiesStrategy
     */
    public final ObserverAccumulatorStrategy
            getPrototypeSpecialAbilitiesStrategy() {
        return prototypeSpecialAbilitiesStrategy;
    }

    /**
     * @param protoSpecialAbilitiesStrategyIn the
     * prototypeSpecialAbilitiesStrategy to set
     */
    public final void setPrototypeSpecialAbilitiesStrategy(
            final ObserverAccumulatorStrategy protoSpecialAbilitiesStrategyIn) {
        this.prototypeSpecialAbilitiesStrategy
                = protoSpecialAbilitiesStrategyIn;
    }

}
