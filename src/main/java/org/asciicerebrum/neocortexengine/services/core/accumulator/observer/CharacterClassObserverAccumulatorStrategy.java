package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import org.asciicerebrum.neocortexengine.domain.ruleentities.CharacterClass;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class CharacterClassObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * The logger instance.
     */
    private static final Logger LOG
            = LoggerFactory.getLogger(
                    CharacterClassObserverAccumulatorStrategy.class);

    /**
     * The class feats of the character class.
     */
    private ObserverAccumulatorStrategy classFeatsStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        if (!(observerSource instanceof CharacterClass)) {
            return observers;
        }

        final CharacterClass characterClass = (CharacterClass) observerSource;

        observers.add(this.getClassFeatsStrategy().getObservers(
                characterClass.getClassFeats(), targetEntity));

        LOG.debug("Found {} observers for character class {}.",
                observers.size(), characterClass.getId().getValue());

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

    /**
     * @return the classFeatsStrategy
     */
    public final ObserverAccumulatorStrategy getClassFeatsStrategy() {
        return classFeatsStrategy;
    }

}
