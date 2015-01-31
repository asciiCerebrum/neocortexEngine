package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.core.attribution.BaseAbilities;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public class BaseAbilitiesObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    private ObserverAccumulatorStrategy abilityStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {

        final BaseAbilities baseAbilities = (BaseAbilities) observerSource;
        final Observers observers = new Observers();
        final Iterator<Ability> abilityIterator
                = baseAbilities.abilityIterator();

        while (abilityIterator.hasNext()) {
            final Ability ability = abilityIterator.next();
            observers.add(this.abilityStrategy.getObservers(
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

}
