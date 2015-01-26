package org.asciicerebrum.mydndgame.valueproviders;

import org.asciicerebrum.mydndgame.domain.core.attribution.ArmorCategory;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;

/**
 *
 * @author species8472
 */
public class ArmorCheckPenaltyProvider implements DynamicValueProvider {

    /**
     * The armor category in question.
     */
    private ArmorCategory armorCategory;

    /**
     * {@inheritDoc} Collects the armor of the given category and returns the
     * minimum check penalty. The lowest value counts!
     */
    @Override
    public final BonusValue getDynamicValue(final DndCharacter dndCharacter) {

        if (this.armorCategory == null) {
            return new BonusValue();
        }

        return dndCharacter.getArmorWorn()
                .filterByArmorCateogry(this.armorCategory)
                .getMinimumArmorCheckPenalty(dndCharacter);
    }

    /**
     * @param armorCategoryInput the armorCategory to set
     */
    public final void setArmorCategory(
            final ArmorCategory armorCategoryInput) {
        this.armorCategory = armorCategoryInput;
    }

}
