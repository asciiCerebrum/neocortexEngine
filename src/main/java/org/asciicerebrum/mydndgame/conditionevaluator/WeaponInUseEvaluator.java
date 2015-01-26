package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.observers.Observers;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.gameentities.Weapon;
import org.asciicerebrum.mydndgame.observers.IObserver;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;
import org.asciicerebrum.mydndgame.services.core.ObservableService;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 * This evaluator is meant for all the boni/observers that only apply when the
 * specific weapon they originate from is really used actively. E.g. you cannot
 * get your +2 attack bonus from your magical sword that you are holding in your
 * other hand that is NOT attacking the two-headed dread-ogre of doom.
 *
 * @author species8472
 */
public class WeaponInUseEvaluator implements ConditionEvaluator {

    /**
     * Accumulates observers.
     */
    private ObservableService observableService;

    /**
     * Accumulates boni.
     */
    private BonusCalculationService bonusService;

    /**
     * Getting settings from the character.
     */
    private SituationContextService ctxService;

    /**
     * {@inheritDoc} Strategy: gather all boni/observers from the current weapon
     * in the situation context. Then check if this bonus/observer is part of
     * the list.
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final IObserver referenceObserver) {

        final InventoryItem item = this.ctxService.getActiveItem(dndCharacter);

        if (!(item instanceof Weapon)) {
            return false;
        }

        Observers weaponObservers = this.observableService
                .accumulateObservers((Weapon) item, (Weapon) item);

        return weaponObservers.contains(referenceObserver);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {

        final InventoryItem item = this.ctxService.getActiveItem(dndCharacter);

        if (!(item instanceof Weapon)) {
            return false;
        }

        Boni weaponBoni = this.bonusService
                .accumulateBoni((Weapon) item, (Weapon) item);

        return weaponBoni.contains(referenceBonus);
    }

    /**
     * @param observableServiceInput the observableService to set
     */
    public final void setObservableService(
            final ObservableService observableServiceInput) {
        this.observableService = observableServiceInput;
    }

    /**
     * @param bonusServiceInput the bonusService to set
     */
    public final void setBonusService(
            final BonusCalculationService bonusServiceInput) {
        this.bonusService = bonusServiceInput;
    }

    /**
     * @param ctxServiceInput the ctxService to set
     */
    public final void setCtxService(
            final SituationContextService ctxServiceInput) {
        this.ctxService = ctxServiceInput;
    }

}
