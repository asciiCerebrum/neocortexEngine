package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.IProficiency;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;

/**
 *
 * @author species8472
 */
public class CorrectProficiencyEvaluator implements ConditionEvaluator {

    /**
     * The proficiency to compare with.
     */
    private IProficiency proficiency;

    /**
     * {@inheritDoc} Checks if the given weapon's (weapon in the active slot)
     * proficiency corresponds to the one from the situation context. The
     * proficiency can be compared with equals as it is a singleton (equality by
     * reference).
     *
     * @return
     */
    @Override
    public final Boolean evaluate(final ICharacter character) {

        if (this.getProficiency() == null) {
            return Boolean.FALSE;
        }

        final IInventoryItem item = character
                .getBodySlotByType(character.getSituationContext()
                        .getBodySlotType()).getItem();

        if (item != null && item instanceof IWeapon) {
            return ((IWeapon) item).getProficiency()
                    .equals(this.getProficiency());
        }

        return Boolean.FALSE;
    }

    /**
     * @return the proficiency
     */
    public final IProficiency getProficiency() {
        return proficiency;
    }

    /**
     * @param proficiencyInput the proficiency to set
     */
    public final void setProficiency(final IProficiency proficiencyInput) {
        this.proficiency = proficiencyInput;
    }

}
