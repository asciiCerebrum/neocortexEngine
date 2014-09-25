package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
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
    public final Boolean evaluate(final ICharacter character) {

        final IWeaponCategory refAttackMode = character.getSituationContext()
                .getAttackMode();

        if (refAttackMode == null) {
            return Boolean.FALSE;
        }

        final IInventoryItem item = character
                .getBodySlotByType(character.getSituationContext()
                        .getBodySlotType()).getItem();

        if (item != null && item instanceof IWeapon) {
            return ((IWeapon) item).isAttackModeCompatible(refAttackMode);
        }

        return Boolean.FALSE;
    }

}
