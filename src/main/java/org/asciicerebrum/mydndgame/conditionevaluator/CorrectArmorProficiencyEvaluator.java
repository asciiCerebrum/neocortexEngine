package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IProficiency;

/**
 *
 * @author species8472
 */
public class CorrectArmorProficiencyEvaluator implements ConditionEvaluator {

    /**
     * The proficiency in question.
     */
    private IProficiency proficiency;

    /**
     * {@inheritDoc} Checks if the worn armor is of the given proficiency.
     */
    @Override
    public final Boolean evaluate(final ICharacter character) {
        if (this.getProficiency() == null) {
            return Boolean.FALSE;
        }

        for (IArmor armor : character.getWieldedArmor()) {
            if (armor.getProficiency() != null
                    && armor.getProficiency().equals(this.getProficiency())) {
                return Boolean.TRUE;
            }
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
