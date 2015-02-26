package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.ruleentities.Proficiency;

/**
 *
 * @author species8472
 */
public class CorrectArmorProficiencyEvaluator implements ConditionEvaluator {

    /**
     * The proficiency in question.
     */
    private Proficiency proficiency;

    /**
     * {@inheritDoc} Checks if the worn armor is of the given proficiency.
     */
    @Override
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextEntity) {
        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        if (this.getProficiency() == null) {
            return false;
        }

        return dndCharacter.getArmorWorn()
                .containsProficiency(this.getProficiency());
    }

    /**
     * @return the proficiency
     */
    public final Proficiency getProficiency() {
        return proficiency;
    }

    /**
     * @param proficiencyInput the proficiency to set
     */
    public final void setProficiency(final Proficiency proficiencyInput) {
        this.proficiency = proficiencyInput;
    }

}
