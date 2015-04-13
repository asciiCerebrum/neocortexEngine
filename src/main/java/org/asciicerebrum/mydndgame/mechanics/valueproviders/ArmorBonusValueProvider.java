package org.asciicerebrum.mydndgame.mechanics.valueproviders;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.LongParticle;
import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.DynamicValueProvider;

/**
 *
 * @author species8472
 */
public class ArmorBonusValueProvider implements DynamicValueProvider {

    /**
     * {@inheritDoc } Retrieves the armor bonus from the underlying prototype.
     */
    @Override
    public final LongParticle getDynamicValue(final ICharacter dndCharacter,
            final UniqueEntity contextItem) {

        if (!(contextItem instanceof Armor)) {
            return null;
        }

        final Armor armor = (Armor) contextItem;

        return armor.getArmorPrototype().getArmorBonus();
    }

}
