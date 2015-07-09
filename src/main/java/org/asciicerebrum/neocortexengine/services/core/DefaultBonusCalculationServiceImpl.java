package org.asciicerebrum.neocortexengine.services.core;

import java.util.HashMap;
import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusTarget;
import java.util.Iterator;
import java.util.Map;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusTargets;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHooks;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusType;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBonus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class DefaultBonusCalculationServiceImpl
        implements BonusCalculationService {

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            DefaultBonusCalculationServiceImpl.class);

    /**
     * The observable service.
     */
    private ObservableService observableService;

    /**
     * The entity pool service.
     */
    private EntityPoolService entityPoolService;

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
    public final ContextBoni accumulateBoniByTarget(
            final BonusSource bonusSource, final BonusTarget bonusTarget,
            final UniqueEntity targetEntity) {
        return this.accumulateBoni(bonusSource, targetEntity)
                .filterByTarget(bonusTarget);
    }

    @Override
    public final ContextBoni accumulateBoniByTargets(
            final BonusSources bonusSources, final BonusTargets bonusTargets,
            final UniqueEntity targetEntity) {
        return this.accumulateBoni(bonusSources, targetEntity)
                .filterByTargets(bonusTargets);
    }

    @Override
    public final ContextBoni accumulateBoni(final BonusSources bonusSources,
            final UniqueEntity targetEntity) {
        final ContextBoni boni = new ContextBoni();
        final Iterator<BonusSource> iterator
                = bonusSources.iterator();
        while (iterator.hasNext()) {
            final BonusSource bonusSource = iterator.next();
            boni.add(this.accumulateBoni(bonusSource, targetEntity));
        }
        return boni;
    }

    @Override
    public final ContextBoni accumulateBoni(final BonusSource bonusSource,
            final UniqueEntity targetEntity) {

        return bonusSource.getBoni(targetEntity, this.getEntityPoolService())
                .filterByScope(targetEntity);
    }

    @Override
    public final BonusValueTuple accumulateBonusValues(
            final DndCharacter dndCharacter, final ContextBoni foundBoni) {
        final BonusValueTuple bonusValueTuple = new BonusValueTuple();

        final Iterator<ContextBonus> bonusIterator
                = this.filterContextBoniByStackability(foundBoni, dndCharacter)
                .iterator();
        while (bonusIterator.hasNext()) {
            final ContextBonus ctxBonus = bonusIterator.next();

            final BonusValueTuple subTuple
                    = this.getEffectiveValues(ctxBonus, dndCharacter);

            // keep in mind that the effectValue might be null
            // --> the bonus does not exist --> continue!
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
     * @param ctxBonus the bonus to determine the value from.
     * @param dndCharacter the context for the dynamic version.
     * @return either the constant or dynamic value.
     *
     */
    @Override
    public final BonusValueTuple getEffectiveValues(final ContextBonus ctxBonus,
            final DndCharacter dndCharacter) {
        if (ctxBonus.getBonus().getConditionEvaluator() != null
                && !ctxBonus.getBonus().getConditionEvaluator()
                .evaluate(dndCharacter, ctxBonus.getContext())) {
            return null;
        }
        if (ctxBonus.getBonus().getValues() != null) {
            return ctxBonus.getBonus().getValues();
        }
        if (ctxBonus.getBonus().getDynamicValueProvider() != null) {
            BonusValueTuple bonusValues = new BonusValueTuple();
            bonusValues.addBonusValue(BonusRank.RANK_0,
                    new BonusValue(ctxBonus.getBonus().getDynamicValueProvider()
                            .getDynamicValue(dndCharacter,
                                    ctxBonus.getContext())
                            .getValue()));
            return bonusValues;
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
                = this.getObservableService().accumulateObserversByHooks(
                        observerSources, observerHooks, targetEntity);
        final ContextBoni boni = this.accumulateBoniByTargets(
                bonusSources, bonusTargets, targetEntity);

        LOG.debug("Accumulated {} observers and {} boni for character {}.",
                new Object[]{observers.size(), boni.size(),
                    dndCharacter.getUniqueId().getValue()});

        // applying observers on list of boni
        final ContextBoni modBoni
                = (ContextBoni) this.getObservableService().triggerObservers(
                        boni, targetEntity, observers, dndCharacter);

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

    /**
     * @return the observableService
     */
    public final ObservableService getObservableService() {
        return observableService;
    }

    /**
     * @return the entityPoolService
     */
    public final EntityPoolService getEntityPoolService() {
        return entityPoolService;
    }

    /**
     * @param entityPoolServiceInput the entityPoolService to set
     */
    public final void setEntityPoolService(
            final EntityPoolService entityPoolServiceInput) {
        this.entityPoolService = entityPoolServiceInput;
    }

    /**
     * Filters out non-stacking boni. If two boni of the same type are in the
     * list, only the higher one is considered. Boni of multiple ranks are
     * compared by their rank-0 value.
     *
     * @param ctxBoni the collection of contextual boni to filter.
     * @param dndCharacter the context giving character.
     * @return the filtered list.
     */
    public final ContextBoni filterContextBoniByStackability(
            final ContextBoni ctxBoni, final DndCharacter dndCharacter) {
        final ContextBoni filteredBoni = new ContextBoni();
        final Map<BonusType, ContextBonus> bonusStackMap
                = new HashMap<BonusType, ContextBonus>();
        final Iterator<ContextBonus> bonusIterator = ctxBoni.iterator();
        while (bonusIterator.hasNext()) {
            final ContextBonus ctxBonus = bonusIterator.next();

            // boni without bonus type always stack
            if (ctxBonus.getBonus().getBonusType() == null
                    || ctxBonus.getBonus().getBonusType().getDoesStack()
                    .isValue()) {
                filteredBoni.add(ctxBonus);
                continue;
            }

            final ContextBonus mappedCtxBonus = bonusStackMap
                    .get(ctxBonus.getBonus().getBonusType());
            if (mappedCtxBonus == null
                    || this.getEffectiveValues(mappedCtxBonus, dndCharacter)
                    .lessThan(this.getEffectiveValues(
                                    ctxBonus, dndCharacter))) {
                bonusStackMap.put(ctxBonus.getBonus().getBonusType(), ctxBonus);
            }
        }

        filteredBoni.add(bonusStackMap.values());
        return filteredBoni;
    }

}
