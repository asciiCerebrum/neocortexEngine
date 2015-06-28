package org.asciicerebrum.neocortexengine.services.statistics;

import org.asciicerebrum.neocortexengine.domain.core.particles.AbilityScore;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;

/**
 *
 * @author species8472
 */
public interface AbilityCalculationService {

    /**
     * Calculates the unmodified default ability mod for a given ability.
     *
     * @param dndCharacter the character holding the ability.
     * @param ability the ability to calculate the mod from.
     * @return the ability mod.
     */
    BonusValue calcAbilityMod(DndCharacter dndCharacter, Ability ability);

    /**
     * Calculates the unmodified default ability score from an ability in the
     * context of a dnd character.
     *
     * @param dndCharacter the character holding the ability.
     * @param ability the ability to calculate the score from.
     * @return the ability score.
     */
    AbilityScore calcAbilityScore(DndCharacter dndCharacter, Ability ability);

    /**
     * Calculates the modified currently active ability mod for a given ability.
     *
     * @param dndCharacter the character holding the ability.
     * @param ability the ability to calculate the mod from.
     * @return the ability mod.
     */
    BonusValue calcCurrentAbilityMod(DndCharacter dndCharacter,
            Ability ability);

    /**
     * Calculates the modified currently active ability score from an ability in
     * the context of a given dnd character.
     *
     * @param dndCharacter the character holding the ability.
     * @param ability the ability to calculate the score from.
     * @return the ability score.
     */
    AbilityScore calcCurrentAbilityScore(DndCharacter dndCharacter,
            Ability ability);
}
