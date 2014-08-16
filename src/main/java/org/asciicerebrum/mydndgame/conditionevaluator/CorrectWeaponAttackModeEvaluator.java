package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;

/**
 *
 * @author species8472
 */
public class CorrectWeaponAttackModeEvaluator implements ConditionEvaluator {

    /**
     * {@inheritDoc} Checks if the given weapon is used in its destined way.
     *
     * @return
     */
    @Override
    public final Boolean evaluate(final ISituationContext situationContext) {

        final IWeaponCategory refAttackMode = situationContext.getAttackMode();

        final IInventoryItem item = situationContext.getCharacter()
                .getBodySlotByType(situationContext.getBodySlotType())
                .getItem();

        if (item instanceof IWeapon) {
            return ((IWeapon) item).getDefaultCategories()
                    .contains(refAttackMode);
        } else {
            return Boolean.FALSE;
        }
    }

}
