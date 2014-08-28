package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponType;

/**
 *
 * @author species8472
 */
public class CorrectWeaponTypeEvaluator implements ConditionEvaluator {

    /**
     * The weapon type to compare with.
     */
    private IWeaponType weaponType;

    /**
     * {@inheritDoc} Checks if the given weapontype is included in the current
     * weapon's list of types.
     *
     * @return
     */
    @Override
    public final Boolean evaluate(final ISituationContext situationContext) {

        if (this.getWeaponType() == null) {
            return Boolean.FALSE;
        }

        final IInventoryItem item = situationContext.getCharacter()
                .getBodySlotByType(situationContext.getBodySlotType())
                .getItem();

        if (item != null && item instanceof IWeapon) {
            return ((IWeapon) item).getWeaponTypes()
                    .contains(this.getWeaponType());
        }

        return Boolean.FALSE;
    }

    /**
     * @return the weaponType
     */
    public final IWeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * @param weaponTypeInput the weaponType to set
     */
    public final void setWeaponType(final IWeaponType weaponTypeInput) {
        this.weaponType = weaponTypeInput;
    }

}
