package org.asciicerebrum.mydndgame.observers.impl;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus.ResemblanceFacet;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;

/**
 *
 * @author species8472
 */
public class RemoveBonusObserver extends AbstractObserver {

    /**
     * The bonus to resemble the bonus which must be removed.
     */
    private Bonus removeBonus;

    /**
     * Defines the aspects which create resemblance between two boni.
     */
    private ResemblanceFacet[] resemblanceFacets;

    /**
     * {@inheritDoc}
     */
    @Override
    protected final Object triggerCallback(final Object object,
            final DndCharacter dndCharacter) {

        final Boni boni = (Boni) object;
        final Iterator<Bonus> boniIterator = boni.iterator();
        while (boniIterator.hasNext()) {
            final Bonus bonus = boniIterator.next();
            if (this.removeBonus.resembles(bonus,
                    this.resemblanceFacets)) {
                boniIterator.remove();
            }
        }
        return object;
    }

    /**
     * @param removeBonusInput the removeBonus to set
     */
    public final void setRemoveBonus(final Bonus removeBonusInput) {
        this.removeBonus = removeBonusInput;
    }

    /**
     * @param resemblanceFacetsInput the resemblanceFacets to set
     */
    public final void setResemblanceFacets(
            final ResemblanceFacet... resemblanceFacetsInput) {
        this.resemblanceFacets = resemblanceFacetsInput;
    }
}
