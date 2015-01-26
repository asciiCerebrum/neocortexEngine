package org.asciicerebrum.mydndgame.valueproviders;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;

/**
 *
 * @author species8472
 */
public class ArmorDexterityLimitProvider implements DynamicValueProvider {

    /**
     * {@inheritDoc} Calculate the minimum of the Maximum Dex Bonus through all
     * worn armor.
     */
    @Override
    public final BonusValue getDynamicValue(final DndCharacter dndCharacter) {

        return dndCharacter.getArmorWorn().getMinimumMaxDexBonus(dndCharacter);
    }

}
