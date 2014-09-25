package org.asciicerebrum.mydndgame.observers;

import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus.ResemblanceFacet;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;

/**
 *
 * @author species8472
 */
public class RemoveBonusObserver extends AbstractObserver {

    /**
     * The bonus to resemble the bonus which must be removed.
     */
    private IBonus removeBonus;

    /**
     * Defines the aspects which create resemblance between two boni.
     */
    private List<ResemblanceFacet> resemblanceFacets;

    /**
     * {@inheritDoc}
     */
    @Override
    protected final Object triggerCallback(final Object object,
            final ICharacter character) {

        List<IBonus> boni = (List<IBonus>) object;
        Iterator<IBonus> boniIterator = boni.iterator();
        while (boniIterator.hasNext()) {
            IBonus bonus = boniIterator.next();
            if (this.getRemoveBonus().resembles(bonus,
                    this.getResemblanceFacets())) {
                boniIterator.remove();
            }
        }
        return object;
    }

    /**
     * @return the removeBonus
     */
    public final IBonus getRemoveBonus() {
        return removeBonus;
    }

    /**
     * @param removeBonusInput the removeBonus to set
     */
    public final void setRemoveBonus(final IBonus removeBonusInput) {
        this.removeBonus = removeBonusInput;
    }

    /**
     * @return the resemblanceFacets
     */
    public final List<ResemblanceFacet> getResemblanceFacets() {
        return resemblanceFacets;
    }

    /**
     * @param resemblanceFacetsInput the resemblanceFacets to set
     */
    public final void setResemblanceFacets(
            final List<ResemblanceFacet> resemblanceFacetsInput) {
        this.resemblanceFacets = resemblanceFacetsInput;
    }
}
