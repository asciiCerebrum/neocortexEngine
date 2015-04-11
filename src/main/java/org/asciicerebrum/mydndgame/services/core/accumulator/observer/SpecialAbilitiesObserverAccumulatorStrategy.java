package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import com.google.common.collect.Iterators;
import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.ruleentities.SpecialAbilities;
import org.asciicerebrum.mydndgame.domain.ruleentities.SpecialAbility;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class SpecialAbilitiesObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * The logger instance.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            SpecialAbilitiesObserverAccumulatorStrategy.class);

    /**
     * The observer accumulator strategy for the special ability.
     */
    private ObserverAccumulatorStrategy specialAbilityStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        if (!(observerSource instanceof SpecialAbilities)) {
            return observers;
        }
        final SpecialAbilities specialAbilities
                = (SpecialAbilities) observerSource;
        final Iterator<SpecialAbility> iterator = specialAbilities.iterator();

        LOG.debug("Found {} special abilities for the item {}.",
                Iterators.size(specialAbilities.iterator()),
                targetEntity.getUniqueId().getValue());

        while (iterator.hasNext()) {
            final SpecialAbility specialAbility = iterator.next();
            observers.add(this.getSpecialAbilityStrategy().getObservers(
                    specialAbility, targetEntity));
        }

        LOG.debug("Found {} observers for the special abilities.",
                observers.size());

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof SpecialAbilities;
    }

    /**
     * @param specialAbilityStrategyInput the specialAbilityStrategy to set
     */
    public final void setSpecialAbilityStrategy(
            final ObserverAccumulatorStrategy specialAbilityStrategyInput) {
        this.specialAbilityStrategy = specialAbilityStrategyInput;
    }

    /**
     * @return the specialAbilityStrategy
     */
    public final ObserverAccumulatorStrategy getSpecialAbilityStrategy() {
        return specialAbilityStrategy;
    }

}
