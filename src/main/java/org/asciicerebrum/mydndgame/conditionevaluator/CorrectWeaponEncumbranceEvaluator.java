package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IEncumbrance;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;

/**
 *
 * @author species8472
 */
public class CorrectWeaponEncumbranceEvaluator implements ConditionEvaluator {

    /**
     * The encumbrance to compare with.
     */
    private IEncumbrance encumbrance;

    /**
     * {@inheritDoc} Checks if the given weapon's encumbrance corresponds to the
     * one from the situation context.
     *
     * @return
     */
    @Override
    public final Boolean evaluate(final ISituationContext situationContext) {

        if (this.getEncumbrance() == null) {
            return Boolean.FALSE;
        }

        final IInventoryItem item = situationContext.getCharacter()
                .getBodySlotByType(situationContext.getBodySlotType())
                .getItem();

        // keep in mind: ranged weapons might not have an encumbrance!
        if (item != null
                && item instanceof IWeapon
                && ((IWeapon) item).getEncumbrance() != null) {
            return ((IWeapon) item).getEncumbrance()
                    .equals(this.getEncumbrance());
        }

        return Boolean.FALSE;
    }

    /**
     * @return the proficiency
     */
    public final IEncumbrance getEncumbrance() {
        return this.encumbrance;
    }

    /**
     * @param encumbranceInput the encumbrance to set
     */
    public final void setEncumbrance(final IEncumbrance encumbranceInput) {
        this.encumbrance = encumbranceInput;
    }

}
