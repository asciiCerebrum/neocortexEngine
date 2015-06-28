package org.asciicerebrum.neocortexengine.domain.mechanics.bonus;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.LongParticle;

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
