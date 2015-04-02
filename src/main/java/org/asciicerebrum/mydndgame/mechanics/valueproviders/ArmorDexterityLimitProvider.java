package org.asciicerebrum.mydndgame.mechanics.valueproviders;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.DynamicValueProvider;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.facades.game.ArmorServiceFacade;
import org.asciicerebrum.mydndgame.facades.game.CharacterServiceFacade;

/**
 *
 * @author species8472
 */
public class ArmorDexterityLimitProvider implements DynamicValueProvider {

    /**
     * The facade for getting armor specifica.
     */
    private ArmorServiceFacade armorFacade;

    /**
     * The service facade for the dnd character.
     */
    private CharacterServiceFacade characterServiceFacade;

    /**
     * {@inheritDoc} Calculate the minimum of the Maximum Dex Bonus through all
     * worn armor.
     */
    @Override
    public final BonusValue getDynamicValue(final ICharacter dndCharacter,
            final UniqueEntity contextItem) {

        return this.getArmorFacade().getMinimumMaxDexBonus(
                this.getCharacterServiceFacade().getArmorWorn(
                        (DndCharacter) dndCharacter),
                (DndCharacter) dndCharacter);
    }

    /**
     * @param armorFacadeInput the armorFacade to set
     */
    public final void setArmorFacade(
            final ArmorServiceFacade armorFacadeInput) {
        this.armorFacade = armorFacadeInput;
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
