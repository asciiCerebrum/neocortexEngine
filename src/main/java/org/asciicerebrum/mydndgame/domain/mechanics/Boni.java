package org.asciicerebrum.mydndgame.domain.mechanics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.asciicerebrum.mydndgame.domain.mechanics.Bonus.BonusScope;

/**
 *
 * @author species8472
 */
public class Boni {

    public static final Boni EMPTY_BONI = new Boni();

    /**
     * The list of boni.
     */
    private final List<Bonus> elements = new ArrayList<Bonus>();

    /**
     * @param boniInput the boni to set
     */
    final void addBoni(final Collection<Bonus> boniInput) {
        this.elements.addAll(boniInput);
    }

    public final void addBoni(final Boni boniInput) {
        this.elements.addAll(boniInput.elements);
    }

    public final void addBonus(final Bonus bonusInput) {
        this.elements.add(bonusInput);
    }

    public final Boni filterByTarget(final BonusTarget target) {
        Boni filteredBoni = new Boni();

        filteredBoni.addBoni(CollectionUtils.select(this.elements, new Predicate() {

            public boolean evaluate(Object o) {
                if (!(o instanceof Bonus)) {
                    return false;
                }
                Bonus oBonus = (Bonus) o;
                return target.equals(oBonus.getTarget());
            }
        }));

        return filteredBoni;
    }

    public final Boni filterByScope(final BonusScope scope) {
        Boni filteredBoni = new Boni();

        filteredBoni.addBoni(CollectionUtils.select(this.elements, new Predicate() {

            public boolean evaluate(Object o) {
                if (!(o instanceof Bonus)) {
                    return false;
                }
                Bonus oBonus = (Bonus) o;
                return scope.equals(oBonus.getScope());
            }
        }));

        return filteredBoni;
    }

    public final Boni filterByTargets(final BonusTargets targets) {
        Boni filteredBoni = new Boni();

        filteredBoni.addBoni(CollectionUtils.select(this.elements, new Predicate() {

            public boolean evaluate(Object o) {
                if (!(o instanceof Bonus)) {
                    return false;
                }
                Bonus oBonus = (Bonus) o;
                return targets.contains(oBonus.getTarget());
            }
        }));

        return filteredBoni;
    }

    public final Iterator<Bonus> iterator() {
        return this.elements.iterator();
    }

    public final boolean contains(final Bonus bonus) {
        return this.elements.contains(bonus);
    }

}
