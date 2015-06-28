package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorCategory;
import org.asciicerebrum.neocortexengine.facades.game.CharacterServiceFacade;

/**
 *
 * @author species8472
 */
public class CorrectArmorCategoryWearingEvaluator
        implements ConditionEvaluator {

    /**
     * The armor category in question.
     */
    private ArmorCategory armorCategory;

    /**
     * The service facade for the dnd character.
     */
    private CharacterServiceFacade characterServiceFacade;

    /**
     * {@inheritDoc} Checks if the character wears armor of the given category.
     */
    @Override
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextEntity) {
        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        if (this.getArmorCategory() == null) {
            return false;
        }

        return this.getCharacterServiceFacade().getArmorWorn(dndCharacter)
                .containsArmorCategory(this.armorCategory);
    }

    /**
     * @return the armorCategory
     */
    public final ArmorCategory getArmorCategory() {
        return armorCategory;
    }

    /**
     * @param armorCategoryInput the armorCategory to set
     */
    public final void setArmorCategory(
            final ArmorCategory armorCategoryInput) {
        this.armorCategory = armorCategoryInput;
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
