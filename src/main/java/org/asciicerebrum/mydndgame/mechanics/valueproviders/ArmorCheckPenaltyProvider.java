package org.asciicerebrum.mydndgame.mechanics.valueproviders;

import org.asciicerebrum.mydndgame.domain.core.DynamicValueProvider;
import org.asciicerebrum.mydndgame.domain.rules.ArmorCategory;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.facades.game.ArmorServiceFacade;

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
     * The facade for getting armor specifica.
     */
    private ArmorServiceFacade armorFacade;

    /**
     * {@inheritDoc} Collects the armor of the given category and returns the
     * minimum check penalty. The lowest value counts!
     */
    @Override
    public final BonusValue getDynamicValue(final DndCharacter dndCharacter) {

        if (this.armorCategory == null) {
            return new BonusValue();
        }

        return this.armorFacade.getMinimumArmorCheckPenalty(
                dndCharacter.getArmorWorn()
                .filterByArmorCateogry(this.armorCategory), dndCharacter);
    }

    /**
     * @param armorCategoryInput the armorCategory to set
     */
    public final void setArmorCategory(
            final ArmorCategory armorCategoryInput) {
        this.armorCategory = armorCategoryInput;
    }

    /**
     * @param armorFacadeInput the armorFacade to set
     */
    public final void setArmorFacade(
            final ArmorServiceFacade armorFacadeInput) {
        this.armorFacade = armorFacadeInput;
    }

}
