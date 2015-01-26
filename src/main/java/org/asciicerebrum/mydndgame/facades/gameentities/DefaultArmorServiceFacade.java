package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.gameentities.Armor;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.services.core.ObservableService;

/**
 *
 * @author species8472
 */
public class DefaultArmorServiceFacade implements ArmorServiceFacade {

    /**
     * The observable service.
     */
    private ObservableService observableService;

    /**
     * {@inheritDoc }
     */
    @Override
    public final BonusValue getArmorCheckPenalty(
            final BonusValue baseArmorCheckPenalty, final Armor armor,
            final DndCharacter dndCharacter) {

        return (BonusValue) this.observableService.triggerObservers(
                baseArmorCheckPenalty, armor,
                new ObserverSources(dndCharacter),
                new ObserverHooks(ObserverHook.ARMOR_CHECK_PENALTY),
                dndCharacter);
    }

    /**
     * @param observableServiceInput the observableService to set
     */
    public final void setObservableService(
            final ObservableService observableServiceInput) {
        this.observableService = observableServiceInput;
    }

    @Override
    public final BonusValue getMaxDexBonus(
            final BonusValue baseArmorCheckPenalty, final Armor armor,
            final DndCharacter dndCharacter) {

        return (BonusValue) this.observableService.triggerObservers(
                baseArmorCheckPenalty, armor,
                new ObserverSources(dndCharacter),
                new ObserverHooks(ObserverHook.ARMOR_MAX_DEX_BONUS),
                dndCharacter);
    }

}
