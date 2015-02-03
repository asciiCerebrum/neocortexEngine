package org.asciicerebrum.mydndgame.mechanics.observertriggers;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus.ResemblanceFacet;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.interfaces.ObserverTriggerStrategy;

/**
 *
 * @author species8472
 */
public class RemoveBonusObserverTrigger implements ObserverTriggerStrategy {

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
    public final Object trigger(final Object object,
            final DndCharacter dndCharacter, final UniqueEntity contextItem) {

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
