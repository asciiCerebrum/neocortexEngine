package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;

/**
 *
 * @author species8472
 */
public class LevelAdvancementObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * Accumulator strategy for the feats.
     */
    private ObserverAccumulatorStrategy featStrategy;

    /**
     * Accumulator strategy for the class level of the level advancement.
     */
    private ObserverAccumulatorStrategy classLevelStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        final LevelAdvancement lvlAdv = (LevelAdvancement) observerSource;

        observers.add(this.featStrategy.getObservers(
                lvlAdv.getFeatAdvancement(), targetEntity));
        observers.add(this.classLevelStrategy.getObservers(
                lvlAdv.getClassLevel(), targetEntity));

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof LevelAdvancement;
    }

    /**
     * @param featStrategyInput the featStrategy to set
     */
    public final void setFeatStrategy(
            final ObserverAccumulatorStrategy featStrategyInput) {
        this.featStrategy = featStrategyInput;
    }

    /**
     * @param classLevelStrategyInput the classLevelStrategy to set
     */
    public final void setClassLevelStrategy(
            final ObserverAccumulatorStrategy classLevelStrategyInput) {
        this.classLevelStrategy = classLevelStrategyInput;
    }

}
