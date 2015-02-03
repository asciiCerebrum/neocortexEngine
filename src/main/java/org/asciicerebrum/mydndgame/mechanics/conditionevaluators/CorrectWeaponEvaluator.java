package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.interfaces.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.Weapon;

/**
 *
 * @author species8472
 */
public class CorrectWeaponEvaluator implements ConditionEvaluator {

    /**
     * The weapon to check.
     */
    private Weapon weapon;

    /**
     * {@inheritDoc} Checks if the given weapon corresponds to the one from the
     * situation context.
     *
     * @return if weapons resemble each other. True if weapon is not set.
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final UniqueEntity contextItem) {

        if (this.weapon == null) {
            return true;
        }

        if (contextItem == null || !(contextItem instanceof Weapon)) {
            return false;
        }

        return this.weapon.resembles((InventoryItem) contextItem);

    }

    /**
     * @param weaponInput the weapon to set
     */
    public final void setWeapon(final Weapon weaponInput) {
        this.weapon = weaponInput;
    }

}
