package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.rules.entities.SpecialAbility;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Observers;

/**
 *
 * @author species8472
 */
public class SpecialAbilityObserverAccumulatorStrategy
        extends FeatureObserverAccumulatorStrategy {

    /**
     * The special abilites strategy.
     */
    private ObserverAccumulatorStrategy specialAbilitiesStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {

        final SpecialAbility specialAbility = (SpecialAbility) observerSource;

        Observers observers = super.getObservers(observerSource, targetEntity);
        observers.add(this.specialAbilitiesStrategy.getObservers(
                specialAbility.getSubAbilities(), targetEntity));
        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof SpecialAbility;
    }

    /**
     * @param specialAbilitiesStrategyInput the specialAbilitiesStrategy to set
     */
    public final void setSpecialAbilitiesStrategy(
            final ObserverAccumulatorStrategy specialAbilitiesStrategyInput) {
        this.specialAbilitiesStrategy = specialAbilitiesStrategyInput;
    }

}
