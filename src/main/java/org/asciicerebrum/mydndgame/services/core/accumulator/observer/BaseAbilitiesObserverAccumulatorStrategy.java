package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.BaseAbilities;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;

/**
 *
 * @author species8472
 */
public class BaseAbilitiesObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * The observer accumulator strategy for the ability.
     */
    private ObserverAccumulatorStrategy abilityStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();

        if (!(observerSource instanceof BaseAbilities)) {
            return observers;
        }

        final BaseAbilities baseAbilities = (BaseAbilities) observerSource;

        final Iterator<Ability> abilityIterator
                = baseAbilities.abilityIterator();

        while (abilityIterator.hasNext()) {
            final Ability ability = abilityIterator.next();
            observers.add(this.getAbilityStrategy().getObservers(
                    ability, targetEntity));
        }

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof BaseAbilities;
    }

    /**
     * @param abilityStrategyInput the abilityStrategy to set
     */
    public final void setAbilityStrategy(
            final ObserverAccumulatorStrategy abilityStrategyInput) {
        this.abilityStrategy = abilityStrategyInput;
    }

    /**
     * @return the abilityStrategy
     */
    public final ObserverAccumulatorStrategy getAbilityStrategy() {
        return abilityStrategy;
    }

}
