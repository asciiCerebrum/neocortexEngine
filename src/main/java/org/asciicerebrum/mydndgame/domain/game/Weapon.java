package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.rules.DamageType;
import org.asciicerebrum.mydndgame.domain.rules.DiceAction;
import org.asciicerebrum.mydndgame.domain.rules.Encumbrance;
import org.asciicerebrum.mydndgame.domain.rules.Proficiency;
import org.asciicerebrum.mydndgame.domain.rules.WeaponCategories;
import org.asciicerebrum.mydndgame.domain.rules.WeaponPrototype;
import org.asciicerebrum.mydndgame.domain.rules.WeaponTypes;

/**
 *
 * @author species8472
 */
public class Weapon extends InventoryItem {

    @Override
    protected final WeaponPrototype getInventoryItemPrototype() {
        return (WeaponPrototype) super.getInventoryItemPrototype();
    }

    public final boolean isOfWeaponPrototype(
            final WeaponPrototype weaponPrototype) {
        return this.getInventoryItemPrototype().equals(weaponPrototype);
    }

    public final WeaponCategories getBaseCategories() {
        return this.getInventoryItemPrototype().getDefaultCategories();
    }

    public final DamageType getDefaultDamageType() {
        // the first from all the or-connected alternatives.
        return this.getInventoryItemPrototype().getDefaultDamgeTypes()
                .getFirst();
    }

    public final Proficiency getBaseProficiency() {
        return this.getInventoryItemPrototype().getProficiency();
    }

    public final Encumbrance getBaseEncumbrance() {
        return this.getInventoryItemPrototype().getEncumbrance();
    }

    public final WeaponTypes getBaseWeaponTypes() {
        return this.getInventoryItemPrototype().getWeaponTypes();
    }

    public final CriticalMinimumLevel getBaseCriticalMinimumLevel() {
        return this.getInventoryItemPrototype().getCriticalMinimumLevel();
    }

    public final CriticalFactor getBaseCriticalFactor() {
        return this.getInventoryItemPrototype().getCriticalFactor();
    }

    public final DiceAction getBaseDamage() {
        return this.getInventoryItemPrototype().getBaseDamage();
    }

}
