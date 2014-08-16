package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.IProficiency;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
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
     * {@inheritDoc} Checks if the given weapon's proficiency corresponds to the
     * one from the situation context. The proficiency can be compared with
     * equals as it is a singleton (equality by reference).
     *
     * @return
     */
    @Override
    public final Boolean evaluate(final ISituationContext situationContext) {

        final IInventoryItem item = situationContext.getCharacter()
                .getBodySlotByType(situationContext.getBodySlotType())
                .getItem();

        if (item instanceof IWeapon) {
            return ((IWeapon) item).getProficiency().equals(this.proficiency);
        } else {
            return Boolean.FALSE;
        }
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
