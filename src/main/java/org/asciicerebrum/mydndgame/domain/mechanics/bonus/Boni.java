package org.asciicerebrum.mydndgame.domain.mechanics.bonus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus.BonusScope;

/**
 *
 * @author species8472
 */
public class Boni {

    private static class SameTargetPredicate implements Predicate {

        private final BonusTarget target;

        public SameTargetPredicate(final BonusTarget targetInput) {
            this.target = targetInput;
        }

        @Override
        public final boolean evaluate(final Object o) {
            if (!(o instanceof Bonus)) {
                return false;
            }
            Bonus oBonus = (Bonus) o;
            return target.equals(oBonus.getTarget());
        }
    }

    private static class SameScopePredicate implements Predicate {

        private final BonusScope scope;

        public SameScopePredicate(final BonusScope scopeInput) {
            this.scope = scopeInput;
        }

        @Override
        public final boolean evaluate(final Object o) {
            if (!(o instanceof Bonus)) {
                return false;
            }
            Bonus oBonus = (Bonus) o;
            return this.scope.equals(oBonus.getScope());
        }
    }

    private static class InTargetsPredicate implements Predicate {

        private final BonusTargets targets;

        public InTargetsPredicate(final BonusTargets targetsInput) {
            this.targets = targetsInput;
        }

        @Override
        public final boolean evaluate(final Object o) {
            if (!(o instanceof Bonus)) {
                return false;
            }
            Bonus oBonus = (Bonus) o;
            return targets.contains(oBonus.getTarget());
        }
    }

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

        filteredBoni.addBoni(CollectionUtils.select(this.elements,
                new SameTargetPredicate(target)));

        return filteredBoni;
    }

    public final Boni filterByScope(final BonusScope scope) {
        Boni filteredBoni = new Boni();

        filteredBoni.addBoni(CollectionUtils.select(this.elements,
                new SameScopePredicate(scope)));

        return filteredBoni;
    }

    public final Boni filterByTargets(final BonusTargets targets) {
        Boni filteredBoni = new Boni();

        filteredBoni.addBoni(CollectionUtils.select(this.elements,
                new InTargetsPredicate(targets)));

        return filteredBoni;
    }

    public final Iterator<Bonus> iterator() {
        return this.elements.iterator();
    }

    public final boolean contains(final Bonus bonus) {
        return this.elements.contains(bonus);
    }

}
