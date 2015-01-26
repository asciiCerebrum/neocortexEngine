package org.asciicerebrum.mydndgame.domain.core.mechanics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus.BonusScope;

/**
 *
 * @author species8472
 */
public class Boni {

    public final static Boni EMPTY_BONI = new Boni();

    /**
     * The list of boni.
     */
    private final List<Bonus> boni = new ArrayList<Bonus>();

    /**
     * @param boniInput the boni to set
     */
    final void addBoni(final Collection<Bonus> boniInput) {
        this.boni.addAll(boniInput);
    }

    public final void addBoni(final Boni boniInput) {
        this.boni.addAll(boniInput.boni);
    }

    public final void addBonus(final Bonus bonusInput) {
        this.boni.add(bonusInput);
    }

    public final Boni filterByTarget(final BonusTarget target) {
        Boni filteredBoni = new Boni();

        filteredBoni.addBoni(CollectionUtils.select(this.boni, new Predicate() {

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

        filteredBoni.addBoni(CollectionUtils.select(this.boni, new Predicate() {

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

        filteredBoni.addBoni(CollectionUtils.select(this.boni, new Predicate() {

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
        return this.boni.iterator();
    }

    public final boolean contains(final Bonus bonus) {
        return this.boni.contains(bonus);
    }

}
