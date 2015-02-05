package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;

/**
 *
 * @author species8472
 */
public class DndCharacterObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    private ObserverAccumulatorStrategy baseAbilitiesStrategy;

    private ObserverAccumulatorStrategy personalizedBodySlotsStrategy;

    private ObserverAccumulatorStrategy conditionsStrategy;

    private ObserverAccumulatorStrategy levelAdvancementsStrategy;

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

}
