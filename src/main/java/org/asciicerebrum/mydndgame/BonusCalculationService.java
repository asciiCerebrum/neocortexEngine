package org.asciicerebrum.mydndgame;

import java.util.List;

/**
 *
 * @author species8472
 */
public interface BonusCalculationService {

    /**
     *
     * @param boni the list of boni to be filtered.
     * @param target the filter bonus target.
     * @return the list of boni filtered by the target.
     */
    List<Bonus> filterBonusListByTarget(List<Bonus> boni, BonusTarget target);

    /**
     *
     * @param source the bonus source as the starting point for the tree
     * taversal.
     * @param target the bonus target of interest.
     * @return the collection list of all boni found through the object graph.
     */
    List<Bonus> traverseBoniByTarget(Object source, BonusTarget target);

    /**
     * Calculation of the effective bonus as a summation over all granted boni.
     *
     * @param dndCharacter the context as a dndCharacter.
     * @param source the source to start collecting boni from (and then going
     * down the tree).
     * @param target the bonus target of interest.
     * @return the total bonus value calculated from all boni given by the
     * source object in the context of the dndCharacter.
     */
    Long retrieveEffectiveBonusValueByTarget(DndCharacter dndCharacter,
            Object source, BonusTarget target);
}
