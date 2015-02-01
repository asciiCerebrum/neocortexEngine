package org.asciicerebrum.mydndgame.conditionevaluator.impl;

import org.asciicerebrum.mydndgame.conditionevaluator.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.rules.entities.ArmorCategory;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.observers.IObserver;

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
            final IObserver referenceObserver) {

        if (this.getArmorCategory() == null) {
            return false;
        }

        return dndCharacter.getArmorWorn()
                .containsArmorCategory(this.armorCategory);
    }

    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        return this.evaluate(dndCharacter, (IObserver) null);
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
