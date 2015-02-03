package org.asciicerebrum.mydndgame.facades.game;

import org.asciicerebrum.mydndgame.domain.rules.Encumbrance;
import org.asciicerebrum.mydndgame.domain.rules.Proficiency;
import org.asciicerebrum.mydndgame.domain.rules.WeaponCategories;
import org.asciicerebrum.mydndgame.domain.rules.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.rules.WeaponType;
import org.asciicerebrum.mydndgame.domain.rules.WeaponTypes;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.rules.DiceAction;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;

/**
 *
 * @author species8472
 */
public class DefaultWeaponServiceFacade
        extends DefaultInventoryItemServiceFacade
        implements WeaponServiceFacade {

    @Override
    public final CriticalMinimumLevel getCriticalMinimumLevel(
            final Weapon weapon, final DndCharacter dndCharacter) {
        final CriticalMinimumLevel baseValue
                = weapon.getBaseCriticalMinimumLevel();
        return (CriticalMinimumLevel) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.CRITICAL_MIMINUM_LEVEL),
                        dndCharacter);
    }

    @Override
    public final CriticalFactor getCriticalFactor(
            final Weapon weapon, final DndCharacter dndCharacter) {
        final CriticalFactor baseValue = weapon.getBaseCriticalFactor();
        return (CriticalFactor) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.CRITICAL_FACTOR),
                        dndCharacter);
    }

    @Override
    public final DiceAction getDamage(final Weapon weapon,
            final DndCharacter dndCharacter) {
        final DiceAction baseValue = weapon.getBaseDamage();
        return (DiceAction) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.WEAPON_DAMAGE),
                        dndCharacter);
    }

    @Override
    public final WeaponTypes getWeaponTypes(final Weapon weapon,
            final DndCharacter dndCharacter) {
        final WeaponTypes baseValue = weapon.getBaseWeaponTypes();
        return (WeaponTypes) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.WEAPON_TYPES),
                        dndCharacter);
    }

    @Override
    public final Encumbrance getEncumbrance(final Weapon weapon,
            final DndCharacter dndCharacter) {
        final Encumbrance baseValue = weapon.getBaseEncumbrance();
        return (Encumbrance) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.ENCUMBRANCE),
                        dndCharacter);
    }

    @Override
    public final Proficiency getProficiency(final Weapon weapon,
            final DndCharacter dndCharacter) {
        final Proficiency baseValue = weapon.getBaseProficiency();
        return (Proficiency) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.PROFICIENCY),
                        dndCharacter);
    }

    @Override
    public final WeaponCategories getCategories(final Weapon weapon,
            final DndCharacter dndCharacter) {
        final WeaponCategories baseValue = weapon.getBaseCategories();
        return (WeaponCategories) this.observableService
                .triggerObservers(baseValue, weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.WEAPON_CATEGORIES),
                        dndCharacter);
    }

    @Override
    public final boolean hasWeaponType(final WeaponType weaponType,
            final Weapon weapon, final DndCharacter dndCharacter) {
        return this.getWeaponTypes(weapon, dndCharacter).contains(weaponType);
    }

    @Override
    public final boolean hasEncumbrance(final Encumbrance encumbrance,
            final Weapon weapon, final DndCharacter dndCharacter) {
        return this.getEncumbrance(weapon, dndCharacter).equals(encumbrance);
    }

    @Override
    public final boolean hasProficiency(final Proficiency proficiency,
            final Weapon weapon, final DndCharacter dndCharacter) {
        return this.getProficiency(weapon, dndCharacter).equals(proficiency);
    }

    @Override
    public final boolean isAttackModeCompatible(final WeaponCategory attackMode,
            final Weapon weapon, final DndCharacter dndCharacter) {
        return this.getCategories(weapon, dndCharacter).contains(attackMode);
    }

}
