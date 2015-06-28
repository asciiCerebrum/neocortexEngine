package org.asciicerebrum.neocortexengine.mechanics.valueproviders;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.DynamicValueProvider;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorCategory;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.facades.game.ArmorServiceFacade;
import org.asciicerebrum.neocortexengine.facades.game.CharacterServiceFacade;

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
     * The service facade for the dnd character.
     */
    private CharacterServiceFacade characterServiceFacade;

    /**
     * {@inheritDoc} Collects the armor of the given category and returns the
     * minimum check penalty. The lowest value counts!
     */
    @Override
    public final BonusValue getDynamicValue(final ICharacter dndCharacter,
            final UniqueEntity contextItem) {

        if (this.getArmorCategory() == null) {
            return new BonusValue();
        }

        return this.getArmorFacade().getMinimumArmorCheckPenalty(
                this.getCharacterServiceFacade().getArmorWorn(
                        (DndCharacter) dndCharacter)
                .filterByArmorCateogry(this.getArmorCategory()),
                (DndCharacter) dndCharacter);
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

    /**
     * @return the armorCategory
     */
    public final ArmorCategory getArmorCategory() {
        return armorCategory;
    }

    /**
     * @return the armorFacade
     */
    public final ArmorServiceFacade getArmorFacade() {
        return armorFacade;
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
