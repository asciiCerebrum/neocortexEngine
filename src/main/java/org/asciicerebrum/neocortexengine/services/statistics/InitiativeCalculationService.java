package org.asciicerebrum.neocortexengine.services.statistics;

import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;

/**
 *
 * @author t.raab
 */
public interface InitiativeCalculationService {

    /**
     * Calculates the initiative bonus of the given character and takes all
     * applied modifications into account.
     *
     * @param dndCharacter the character to calculate the init bonus for.
     * @return the initiative as a bonus value.
     */
    BonusValue calcInitBonus(DndCharacter dndCharacter);

}
