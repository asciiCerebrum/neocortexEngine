package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public class DndCharacterObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    private ObserverAccumulatorStrategy baseAbilitiesStrategy;

    private ObserverAccumulatorStrategy bodySlotsStrategy;

    private ObserverAccumulatorStrategy conditionsStrategy;

    private ObserverAccumulatorStrategy levelAdvancementsStrategy;

    private ObserverAccumulatorStrategy raceStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final DndCharacter dndCharacter = (DndCharacter) observerSource;

        final Observers observers = new Observers();

        observers.add(this.baseAbilitiesStrategy.getObservers(
                dndCharacter.getBaseAbilities(), targetEntity));
        observers.add(this.bodySlotsStrategy.getObservers(
                dndCharacter.getBodySlots(), targetEntity));
        observers.add(this.conditionsStrategy.getObservers(
                dndCharacter.getConditions(), targetEntity));
        observers.add(this.levelAdvancementsStrategy.getObservers(
                dndCharacter.getLevelAdvancements(), targetEntity));
        observers.add(this.raceStrategy.getObservers(
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
     * @param bodySlotsStrategyInput the bodySlotsStrategy to set
     */
    public final void setBodySlotsStrategy(
            final ObserverAccumulatorStrategy bodySlotsStrategyInput) {
        this.bodySlotsStrategy = bodySlotsStrategyInput;
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

}
