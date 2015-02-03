package org.asciicerebrum.mydndgame.mechanics.valueproviders;

import org.asciicerebrum.mydndgame.domain.mechanics.transfer.DynamicValueProvider;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.game.dndcharacter.DndCharacter;
import org.asciicerebrum.mydndgame.facades.game.ArmorServiceFacade;

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
     * {@inheritDoc} Calculate the minimum of the Maximum Dex Bonus through all
     * worn armor.
     */
    @Override
    public final BonusValue getDynamicValue(final DndCharacter dndCharacter) {

        return this.armorFacade.getMinimumMaxDexBonus(
                dndCharacter.getArmorWorn(), dndCharacter);
    }

    /**
     * @param armorFacadeInput the armorFacade to set
     */
    public final void setArmorFacade(
            final ArmorServiceFacade armorFacadeInput) {
        this.armorFacade = armorFacadeInput;
    }

}
