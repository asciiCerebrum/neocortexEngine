package org.asciicerebrum.mydndgame.domain.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.DamageType;
import org.asciicerebrum.mydndgame.domain.core.attribution.Encumbrance;
import org.asciicerebrum.mydndgame.domain.core.attribution.Proficiency;
import org.asciicerebrum.mydndgame.domain.core.attribution.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.core.attribution.WeaponType;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.gameentities.prototypes.DiceAction;
import org.asciicerebrum.mydndgame.domain.gameentities.prototypes.WeaponPrototype;
import org.asciicerebrum.mydndgame.facades.gameentities.WeaponServiceFacade;

/**
 *
 * @author species8472
 */
public class Weapon extends InventoryItem {

    /**
     * Facade for all services participating in realtime-calculation of weapon
     * attributes.
     */
    private WeaponServiceFacade weaponServiceFacade;

    @Override
    protected final WeaponPrototype getInventoryItemPrototype() {
        return (WeaponPrototype) super.getInventoryItemPrototype();
    }

    public final boolean isOfWeaponPrototype(
            final WeaponPrototype weaponPrototype) {
        return this.getInventoryItemPrototype().equals(weaponPrototype);
    }

    //TODO all these private methods below must be changed:
    // the values they provide are changeable by boni/observers so they cannot
    // be given out without the facade/service applying those potential boni/
    // observers. This must be done analog to the price of the inventoryItem.
    public final boolean isAttackModeCompatible(
            final WeaponCategory attackMode,
            final DndCharacter dndCharacter) {
        return this.weaponServiceFacade.getCategories(
                this.getInventoryItemPrototype().getDefaultCategories(), this,
                dndCharacter).contains(attackMode);
    }

    public final DamageType getDefaultDamageType() {
        // the first from all the or-connected alternatives.
        return this.getInventoryItemPrototype().getDefaultDamgeTypes()
                .getFirst();
    }

    public final boolean hasProficiency(final Proficiency proficiency,
            final DndCharacter dndCharacter) {
        return proficiency.equals(this.weaponServiceFacade
                .getProficiency(
                        this.getInventoryItemPrototype().getProficiency(),
                        this, dndCharacter));
    }

    public final boolean hasEncumbrance(final Encumbrance encumbrance,
            final DndCharacter dndCharacter) {
        return encumbrance.equals(this.weaponServiceFacade
                .getEncumbrance(
                        this.getInventoryItemPrototype().getEncumbrance(),
                        this, dndCharacter));
    }

    public final boolean hasWeaponType(final WeaponType weaponType,
            final DndCharacter dndCharacter) {
        return this.weaponServiceFacade.getWeaponTypes(
                this.getInventoryItemPrototype().getWeaponTypes(),
                this, dndCharacter).contains(weaponType);
    }

    public final CriticalMinimumLevel getCriticalMinimumLevel(
            final DndCharacter dndCharacter) {
        return this.weaponServiceFacade.getCriticalMinimumLevel(
                this.getInventoryItemPrototype().getCriticalMinimumLevel(),
                this, dndCharacter);
    }

    public final CriticalFactor getCriticalFactor(
            final DndCharacter dndCharacter) {
        return this.weaponServiceFacade.getCriticalFactor(
                this.getInventoryItemPrototype().getCriticalFactor(),
                this, dndCharacter);
    }

    public final DiceAction getDamage(
            final DndCharacter dndCharacter) {
        return this.weaponServiceFacade.getDamage(
                this.getInventoryItemPrototype().getBaseDamage(),
                this, dndCharacter);
    }

}
