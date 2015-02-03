package org.asciicerebrum.mydndgame.domain.mechanics.bonus;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.LongParticle;

/**
 *
 * @author species8472
 */
public interface DynamicValueProvider {

    /**
     *
     * @param dndCharacter the context character.
     * @param contextItem the context item.
     * @return the result of the dynamic bonus value calculation.
     */
    LongParticle getDynamicValue(ICharacter dndCharacter,
            UniqueEntity contextItem);
}
