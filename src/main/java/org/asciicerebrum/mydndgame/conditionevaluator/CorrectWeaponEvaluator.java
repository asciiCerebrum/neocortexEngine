package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;

/**
 *
 * @author species8472
 */
public class CorrectWeaponEvaluator implements ConditionEvaluator {

    /**
     * The weapon to check.
     */
    private IWeapon weapon;

    /**
     * {@inheritDoc} Checks if the given weapon corresponds to the one from the
     * situation context.
     *
     * @return if weapons resemble each other. True if weapon is not set.
     */
    @Override
    public final Boolean evaluate(final ISituationContext situationContext) {

        if (this.weapon == null) {
            return Boolean.TRUE;
        }

        final IBodySlotType bsType = situationContext.getBodySlotType();

        final IInventoryItem item = situationContext.getCharacter()
                .getBodySlotByType(bsType).getItem();

        if (item == null) {
            return Boolean.FALSE;
        }

        final IWeapon checkWeapon = (IWeapon) item;

        return this.weapon.resembles(checkWeapon);
    }

    /**
     * @return the weapon
     */
    public final IWeapon getWeapon() {
        return weapon;
    }

    /**
     * @param weaponInput the weapon to set
     */
    public final void setWeapon(final IWeapon weaponInput) {
        this.weapon = weaponInput;
    }

}
