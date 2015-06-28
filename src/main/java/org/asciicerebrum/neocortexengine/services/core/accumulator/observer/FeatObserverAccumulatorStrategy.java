package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Feat;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class FeatObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * The logger instance.
     */
    private static final Logger LOG
            = LoggerFactory.getLogger(FeatObserverAccumulatorStrategy.class);

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {

        if (!(observerSource instanceof Feat)) {
            return new Observers();
        }

        LOG.debug("Found {} observers through the feat {}.",
                ((Feat) observerSource).getObservers().size(),
                ((Feat) observerSource).getFeatType().getUniqueId().getValue());

        return ((Feat) observerSource).getObservers();
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof Feat;
    }

}
