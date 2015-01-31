package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.core.attribution.CharacterClass;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public class CharacterClassObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * The class feats of the character class.
     */
    private ObserverAccumulatorStrategy classFeatsStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final CharacterClass characterClass = (CharacterClass) observerSource;
        final Observers observers = new Observers();

        observers.add(this.classFeatsStrategy.getObservers(
                characterClass.getClassFeats(), targetEntity));

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof CharacterClass;
    }

    /**
     * @param classFeatsStrategyInput the classFeatsStrategy to set
     */
    public final void setClassFeatsStrategy(
            final ObserverAccumulatorStrategy classFeatsStrategyInput) {
        this.classFeatsStrategy = classFeatsStrategyInput;
    }

}
