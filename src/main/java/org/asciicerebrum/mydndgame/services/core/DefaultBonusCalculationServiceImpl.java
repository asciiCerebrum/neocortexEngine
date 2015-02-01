package org.asciicerebrum.mydndgame.services.core;

import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusTarget;
import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Observers;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;

/**
 *
 * @author species8472
 */
public class DefaultBonusCalculationServiceImpl
        implements BonusCalculationService {

    private ObservableService observableService;

    @Override
    public final BonusValueTuple calculateBonusValues(
            final BonusSource bonusSource, final BonusTarget bonusTarget,
            final UniqueEntity targetEntity,
            final ObserverSource observerSource,
            final ObserverHook observerHook,
            final DndCharacter dndCharacter) {

        return this.calculateBonusValues(
                new BonusSources(bonusSource), new BonusTargets(bonusTarget),
                targetEntity,
                new ObserverSources(observerSource),
                new ObserverHooks(observerHook), dndCharacter);
    }

    @Override
    public final Boni accumulateBoniByTarget(final BonusSource bonusSource,
            final BonusTarget bonusTarget, final UniqueEntity targetEntity) {
        return this.accumulateBoni(bonusSource, targetEntity)
                .filterByTarget(bonusTarget);
    }

    @Override
    public final Boni accumulateBoniByTargets(final BonusSources bonusSources,
            final BonusTargets bonusTargets, final UniqueEntity targetEntity) {
        return this.accumulateBoni(bonusSources, targetEntity)
                .filterByTargets(bonusTargets);
    }

    @Override
    public final Boni accumulateBoni(final BonusSources bonusSources,
            final UniqueEntity targetEntity) {
        final Boni boni = new Boni();
        final Iterator<BonusSource> iterator
                = bonusSources.iterator();
        while (iterator.hasNext()) {
            final BonusSource bonusSource = iterator.next();
            boni.addBoni(this.accumulateBoni(bonusSource, targetEntity));
        }
        return boni;
    }

    @Override
    public final Boni accumulateBoni(final BonusSource bonusSource,
            final UniqueEntity targetEntity) {
        // See DefaultObservableService for that.
        UniqueEntity trackedEntity = targetEntity;
        if (bonusSource instanceof UniqueEntity) {
            trackedEntity = (UniqueEntity) bonusSource;
        }

        final Boni boni = new Boni();
        if (trackedEntity != targetEntity) {
            boni.addBoni(bonusSource.getBoni()
                    .filterByScope(Bonus.BonusScope.ALL));
        } else {
            boni.addBoni(bonusSource.getBoni());
        }

        final Iterator<BonusSource> iterator
                = bonusSource.getBonusSources().iterator();
        while (iterator.hasNext()) {
            final BonusSource subBonusSource = iterator.next();
            if (subBonusSource == null) {
                continue;
            }

            boni.addBoni(this.accumulateBoni(subBonusSource, targetEntity));
        }
        return boni;
    }

    @Override
    public final BonusValueTuple accumulateBonusValues(
            final DndCharacter dndCharacter, final Boni foundBoni) {
        //TODO filter out non-stacking boni
        //TODO track the origin of the bonus, e.g. from ability Constitution
        //TODO skip boni of value 0 - really erase them from the list or don't
        // put them into the list in the first place
        // for hp
        final BonusValueTuple bonusValueTuple = new BonusValueTuple();

        Iterator<Bonus> bonusIterator = foundBoni.iterator();
        while (bonusIterator.hasNext()) {
            Bonus bonus = bonusIterator.next();

            final BonusValueTuple subTuple
                    = this.getEffectiveValues(bonus, dndCharacter);

            // keep in mind that the effectValue might be null
            // --> the bonus does not exist --> continue!
            if (subTuple == null) {
                continue;
            }

            bonusValueTuple.add(subTuple);
        }
        return bonusValueTuple;
    }

    /**
     * Use either the constant value of the bonus or (if null) the dynamic
     * value. Remember there is a difference between an effective value of null
     * and 0. Null: the bonus is defacto non-existent. 0: the bonus applies with
     * a value of 0.
     *
     * @param bonus the bonus to determine the value from.
     * @param dndCharacter the context for the dynamic version.
     * @return either the constant or dynamic value.
     *
     */
    @Override
    public final BonusValueTuple getEffectiveValues(final Bonus bonus,
            final DndCharacter dndCharacter) {
        if (bonus.getConditionEvaluator() != null
                && !bonus.getConditionEvaluator()
                .evaluate(dndCharacter, bonus)) {
            return null;
        }
        if (bonus.getValues() != null) {
            return bonus.getValues();
        }
        //TODO remove this limitation that a dynamic value provider can only
        // provide single-ranked boni (that means with rank 0).
        if (bonus.getDynamicValueProvider() != null) {
            BonusValueTuple bonusValues = new BonusValueTuple();
            bonusValues.addBonusValue(BonusRank.RANK_0,
                    new BonusValue(bonus.getDynamicValueProvider()
                            .getDynamicValue(dndCharacter).getValue()));
        }
        return null;
    }

    @Override
    public final BonusValueTuple calculateBonusValues(
            final BonusSources bonusSources, final BonusTargets bonusTargets,
            final UniqueEntity targetEntity,
            final ObserverSources observerSources,
            final ObserverHooks observerHooks,
            final DndCharacter dndCharacter) {

        final Observers observers
                = this.observableService.accumulateObserversByHooks(
                        observerSources, observerHooks, targetEntity);
        final Boni boni = this.accumulateBoniByTargets(
                bonusSources, bonusTargets, targetEntity);

        // applying observers on list of boni
        final Boni modBoni
                = (Boni) this.observableService.triggerObservers(
                        boni, observers, dndCharacter);

        // calclulating bonus value tuple from the mofified bonus list
        return this.accumulateBonusValues(dndCharacter, modBoni);
    }

    /**
     * @param observableServiceInput the observableService to set
     */
    public final void setObservableService(
            final ObservableService observableServiceInput) {
        this.observableService = observableServiceInput;
    }

}
