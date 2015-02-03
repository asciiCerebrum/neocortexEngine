package org.asciicerebrum.mydndgame.services.core;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.dndcharacter.DndCharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;

/**
 *
 * @author species8472
 */
public interface BonusCalculationService {

    /**
     *
     * @param bonusSource
     * @param bonusTarget
     * @param targetEntity defines the entity for which the boni are collected.
     * No boni of equal type/hook for another entity are considered, as long as
     * their scope is SPECIFIC.
     * @param observerSource
     * @param observerHook
     * @param dndCharacter
     * @return
     */
    BonusValueTuple calculateBonusValues(
            BonusSource bonusSource, BonusTarget bonusTarget,
            UniqueEntity targetEntity,
            ObserverSource observerSource,
            ObserverHook observerHook, DndCharacter dndCharacter);

    BonusValueTuple calculateBonusValues(
            BonusSources bonusSources, BonusTargets bonusTargets,
            UniqueEntity targetEntity,
            ObserverSources observerSources,
            ObserverHooks observerHooks, DndCharacter dndCharacter);

    Boni accumulateBoniByTarget(BonusSource bonusSource,
            BonusTarget bonusTarget, UniqueEntity targetEntity);

    Boni accumulateBoniByTargets(BonusSources bonusSources,
            BonusTargets bonusTargets, UniqueEntity targetEntity);

    Boni accumulateBoni(BonusSources bonusSources, UniqueEntity targetEntity);

    Boni accumulateBoni(BonusSource bonusSource, UniqueEntity targetEntity);

    /**
     * Accumulates the list of found boni into a single effective bonus value
     * tuple.
     *
     * @param dndCharacter the context for the calculations.
     * @param targetEntity the context item.
     * @param foundBoni the list of boni to accumulate the result over.
     * @return the accumulated bonus value tuple.
     */
    BonusValueTuple accumulateBonusValues(
            DndCharacter dndCharacter, UniqueEntity targetEntity,
            Boni foundBoni);

    BonusValueTuple getEffectiveValues(Bonus bonus, UniqueEntity targetEntity,
            DndCharacter dndCharacter);
}
