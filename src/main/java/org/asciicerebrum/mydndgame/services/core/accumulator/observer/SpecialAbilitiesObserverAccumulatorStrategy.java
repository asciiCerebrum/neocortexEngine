package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.rules.entities.SpecialAbilities;
import org.asciicerebrum.mydndgame.domain.rules.entities.SpecialAbility;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public class SpecialAbilitiesObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    private ObserverAccumulatorStrategy specialAbilityStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        final SpecialAbilities specialAbilities
                = (SpecialAbilities) observerSource;
        final Iterator<SpecialAbility> iterator = specialAbilities.iterator();

        while (iterator.hasNext()) {
            final SpecialAbility specialAbility = iterator.next();
            observers.add(this.specialAbilityStrategy.getObservers(
                    specialAbility, targetEntity));
        }

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

}
