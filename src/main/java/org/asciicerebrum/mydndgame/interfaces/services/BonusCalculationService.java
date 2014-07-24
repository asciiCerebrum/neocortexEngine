package org.asciicerebrum.mydndgame.interfaces.services;

import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;

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
    List<IBonus> filterBonusListByTarget(List<IBonus> boni, BonusTarget target);

    /**
     *
     * @param source the bonus source as the starting point for the tree
     * taversal.
     * @param target the bonus target of interest.
     * @return the collection list of all boni found through the object graph.
     */
    List<IBonus> traverseBoniByTarget(Object source, BonusTarget target);

    /**
     * Calculation of the effective bonus as a summation over all granted boni.
     * This method is a sequence of
     * {@link #traverseBoniByTarget(Object, BonusTarget)} and
     * {@link #accumulateBonusValue(ICharacter, jList)}.
     *
     * @param iCharacter the context as a dndCharacter.
     * @param source the source to start collecting boni from (and then going
     * down the tree).
     * @param target the bonus target of interest.
     * @return the total bonus value calculated from all boni given by the
     * source object in the context of the dndCharacter.
     */
    Long retrieveEffectiveBonusValueByTarget(ICharacter iCharacter,
            Object source, BonusTarget target);

    /**
     * Accumulates the list of found boni into a single effective bonus value.
     *
     * @param iCharacter the context as a dndCharacter.
     * @param foundBoni the list of boni to accumulate the result over.
     * @return the accumulated bonus value.
     */
    Long accumulateBonusValue(ICharacter iCharacter,
            List<IBonus> foundBoni);
}
