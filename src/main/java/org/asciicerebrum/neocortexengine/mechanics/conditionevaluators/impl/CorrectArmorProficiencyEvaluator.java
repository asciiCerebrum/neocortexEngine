package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Proficiency;
import org.asciicerebrum.neocortexengine.facades.game.CharacterServiceFacade;

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
     * The service facade for the dnd character.
     */
    private CharacterServiceFacade characterServiceFacade;

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

        return this.getCharacterServiceFacade().getArmorWorn(dndCharacter)
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

    /**
     * @return the characterServiceFacade
     */
    public final CharacterServiceFacade getCharacterServiceFacade() {
        return characterServiceFacade;
    }

    /**
     * @param characterServiceFacadeInput the characterServiceFacade to set
     */
    public final void setCharacterServiceFacade(
            final CharacterServiceFacade characterServiceFacadeInput) {
        this.characterServiceFacade = characterServiceFacadeInput;
    }

}
