package org.asciicerebrum.mydndgame.domain.mechanics.transfer;

import org.asciicerebrum.mydndgame.domain.core.particles.LongParticle;
import org.asciicerebrum.mydndgame.domain.game.dndcharacter.DndCharacter;

/**
 *
 * @author species8472
 */
public interface DynamicValueProvider {

    /**
     *
     * @param dndCharacter the context.
     * @return the result of the dynamic bonus value calculation.
     */
    LongParticle getDynamicValue(DndCharacter dndCharacter);
}
