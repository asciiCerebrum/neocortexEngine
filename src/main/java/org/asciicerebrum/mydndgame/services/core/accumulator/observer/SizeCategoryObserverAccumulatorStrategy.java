package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.ruleentities.SizeCategory;

/**
 *
 * @author species8472
 */
public class SizeCategoryObserverAccumulatorStrategy
        extends FeatureObserverAccumulatorStrategy {

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {

        if (!(observerSource instanceof SizeCategory)) {
            return new Observers();
        }
        final SizeCategory sizeCategory = (SizeCategory) observerSource;

        return sizeCategory.getObservers();
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof SizeCategory;
    }

}
