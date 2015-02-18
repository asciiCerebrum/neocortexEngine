package org.asciicerebrum.mydndgame.services.core;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
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
     * Calculates the effective bonus value by considering all boni in the bonus
     * source tree as well as all effective observers in the observer source
     * tree.
     *
     * @param bonusSource the starting point of the calculation process.
     * @param bonusTarget the target the boni are restricted to.
     * @param targetEntity defines the entity for which the boni are collected.
     * No boni of equal type/hook for another entity are considered, as long as
     * their scope is SPECIFIC.
     * @param observerSource the starting point of the observer accumulation
     * process.
     * @param observerHook the hook the observers are restricted to.
     * @param dndCharacter the dnd character the calculation is based on.
     * @return the effective bonus value as a bonus value tuple.
     */
    BonusValueTuple calculateBonusValues(
            BonusSource bonusSource, BonusTarget bonusTarget,
            UniqueEntity targetEntity,
            ObserverSource observerSource,
            ObserverHook observerHook, DndCharacter dndCharacter);

    /**
     * Calculates the effective bonus value by considering all boni in the bonus
     * source tree as well as all effective observers in the observer source
     * tree.
     *
     * @param bonusSources the starting points of the calculation process.
     * @param bonusTargets the targets the boni are restricted to.
     * @param targetEntity the entity the calculation is made for.
     * @param observerSources the starting points of the observer accumulation
     * process.
     * @param observerHooks the hooks the observers are restricted to.
     * @param dndCharacter the dnd character the calculation is based on.
     * @return the effective bonus value as a bonus value tuple.
     */
    BonusValueTuple calculateBonusValues(
            BonusSources bonusSources, BonusTargets bonusTargets,
            UniqueEntity targetEntity,
            ObserverSources observerSources,
            ObserverHooks observerHooks, DndCharacter dndCharacter);

    /**
     * Accumulate all boni of a given bonus target through the whole bonus
     * source tree, beginning with the given bonus source.
     *
     * @param bonusSource the starting point of the accumulation process.
     * @param bonusTarget the target of the boni.
     * @param targetEntity the entity the boni are accumulated for.
     * @return the collection of boni.
     */
    Boni accumulateBoniByTarget(BonusSource bonusSource,
            BonusTarget bonusTarget, UniqueEntity targetEntity);

    /**
     * Accumulate all boni of a given bonus target through the whole bonus
     * source tree, begnning with the given bonus sources.
     *
     * @param bonusSources the starting points of the accumulation processes.
     * @param bonusTargets the targets of the boni.
     * @param targetEntity the entity the boni are accumulated for.
     * @return the collection of boni.
     */
    Boni accumulateBoniByTargets(BonusSources bonusSources,
            BonusTargets bonusTargets, UniqueEntity targetEntity);

    /**
     * Accumulate all boni through the whole bonus source tree, beginning with
     * the given bonus sources. The different trees are traversed independently.
     *
     * @param bonusSources the starting points of the accumulation processes.
     * @param targetEntity the entity the boni are accumulated for.
     * @return the collection of boni.
     */
    Boni accumulateBoni(BonusSources bonusSources, UniqueEntity targetEntity);

    /**
     * Accumulate all boni through the whole bonus source tree, beginning with
     * the given bonus source.
     *
     * @param bonusSource the starting point of the accumulation process.
     * @param targetEntity the entity the boni are accumulated for.
     * @return the collection of boni.
     */
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

    /**
     * Calculates the dynamic effective value of a bonus.
     *
     * @param bonus the bonus to calculate the value for.
     * @param targetEntity the entity the bonus is calculated for.
     * @param dndCharacter the dnd character which gives the context.
     * @return the effective value as a tuple.
     */
    BonusValueTuple getEffectiveValues(Bonus bonus, UniqueEntity targetEntity,
            DndCharacter dndCharacter);
}
