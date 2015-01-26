package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.Encumbrance;
import org.asciicerebrum.mydndgame.domain.core.attribution.Proficiency;
import org.asciicerebrum.mydndgame.domain.core.attribution.WeaponCategories;
import org.asciicerebrum.mydndgame.domain.core.attribution.WeaponTypes;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.Weapon;
import org.asciicerebrum.mydndgame.domain.gameentities.prototypes.DiceAction;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.services.core.ObservableService;

/**
 *
 * @author species8472
 */
public class DefaultWeaponServiceFacade implements WeaponServiceFacade {

    private ObservableService observableService;

    @Override
    public final CriticalMinimumLevel getCriticalMinimumLevel(
            final CriticalMinimumLevel baseValue,
            final Weapon weapon,
            final DndCharacter dndCharacter) {
        return (CriticalMinimumLevel) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.CRITICAL_MIMINUM_LEVEL),
                        dndCharacter);
    }

    @Override
    public final CriticalFactor getCriticalFactor(
            final CriticalFactor baseValue,
            final Weapon weapon,
            final DndCharacter dndCharacter) {
        return (CriticalFactor) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.CRITICAL_FACTOR),
                        dndCharacter);
    }

    @Override
    public final DiceAction getDamage(final DiceAction baseValue,
            final Weapon weapon,
            final DndCharacter dndCharacter) {
        return (DiceAction) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.WEAPON_DAMAGE),
                        dndCharacter);
    }

    @Override
    public final WeaponTypes getWeaponTypes(final WeaponTypes baseValue,
            final Weapon weapon,
            final DndCharacter dndCharacter) {
        return (WeaponTypes) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.WEAPON_TYPES),
                        dndCharacter);
    }

    @Override
    public final Encumbrance getEncumbrance(final Encumbrance baseValue,
            final Weapon weapon,
            final DndCharacter dndCharacter) {
        return (Encumbrance) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.ENCUMBRANCE),
                        dndCharacter);
    }

    @Override
    public final Proficiency getProficiency(final Proficiency baseValue,
            final Weapon weapon,
            final DndCharacter dndCharacter) {
        return (Proficiency) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.PROFICIENCY),
                        dndCharacter);
    }

    @Override
    public final WeaponCategories getCategories(
            final WeaponCategories baseValue,
            final Weapon weapon,
            final DndCharacter dndCharacter) {
        return (WeaponCategories) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.WEAPON_CATEGORIES),
                        dndCharacter);
    }

    /**
     * @param observableServiceInput the observableService to set
     */
    public final void setObservableService(
            final ObservableService observableServiceInput) {
        this.observableService = observableServiceInput;
    }

}
