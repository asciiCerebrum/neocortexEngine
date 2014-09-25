package org.asciicerebrum.mydndgame.valueproviders;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusValueProvider;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;

/**
 *
 * @author species8472
 */
public class ArmorDexterityLimitProvider implements BonusValueProvider {

    /**
     * {@inheritDoc} Calculate the minimum of the Maximum Dex Bonus through all
     * worn armor.
     */
    @Override
    public final Long getDynamicValue(final ICharacter character) {

        Long minMaxDexBonus = null;
        for (IArmor armor : character.getWieldedArmor()) {
            if (armor.getMaxDexBonus() != null
                    && (minMaxDexBonus == null
                    || armor.getMaxDexBonus() < minMaxDexBonus)) {
                minMaxDexBonus = armor.getMaxDexBonus();
            }
        }
        return minMaxDexBonus;
    }

}
