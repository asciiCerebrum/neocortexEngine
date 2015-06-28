package org.asciicerebrum.neocortexengine.mechanics.observertriggers;

import java.util.Iterator;
import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.Bonus.ResemblanceFacet;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBonus;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.ObserverTriggerStrategy;

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
            final ICharacter dndCharacter, final UniqueEntity contextItem) {

        final ContextBoni ctxBoni = (ContextBoni) object;
        final Iterator<ContextBonus> boniIterator = ctxBoni.iterator();
        while (boniIterator.hasNext()) {
            final ContextBonus ctxBonus = boniIterator.next();
            if (this.getRemoveBonus().resembles(ctxBonus.getBonus(),
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

    /**
     * @return the removeBonus
     */
    public final Bonus getRemoveBonus() {
        return removeBonus;
    }

}
