package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import org.asciicerebrum.neocortexengine.domain.ruleentities.ClassLevel;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class ClassLevelObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * The logger instance.
     */
    private static final Logger LOG
            = LoggerFactory.getLogger(
                    ClassLevelObserverAccumulatorStrategy.class);

    /**
     * The class feats of the character class.
     */
    private ObserverAccumulatorStrategy characterClassStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        if (!(observerSource instanceof ClassLevel)) {
            return observers;
        }

        final ClassLevel classLevel = (ClassLevel) observerSource;

        LOG.debug("Accumulating over class level {} for target {}.",
                classLevel.getLevel().getValue(),
                targetEntity.getUniqueId().getValue());

        observers.add(this.getCharacterClassStrategy().getObservers(
                classLevel.getCharacterClass(), targetEntity));

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof ClassLevel;
    }

    /**
     * @param characterClassStrategyInput the characterClassStrategy to set
     */
    public final void setCharacterClassStrategy(
            final ObserverAccumulatorStrategy characterClassStrategyInput) {
        this.characterClassStrategy = characterClassStrategyInput;
    }

    /**
     * @return the characterClassStrategy
     */
    public final ObserverAccumulatorStrategy getCharacterClassStrategy() {
        return characterClassStrategy;
    }

}
