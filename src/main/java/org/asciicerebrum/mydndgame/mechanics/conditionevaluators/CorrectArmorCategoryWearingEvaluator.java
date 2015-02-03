package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.rules.ArmorCategory;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;

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
     * {@inheritDoc} Checks if the character wears armor of the given category.
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final UniqueEntity contextEntity) {

        if (this.getArmorCategory() == null) {
            return false;
        }

        return dndCharacter.getArmorWorn()
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

}
