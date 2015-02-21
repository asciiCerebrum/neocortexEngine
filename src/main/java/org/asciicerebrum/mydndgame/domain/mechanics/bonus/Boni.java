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

    /**
     * List filtering predicate for when the bonus target is the same as the one
     * given in the constructor.
     */
    private static class SameTargetPredicate implements Predicate {

        /**
         * The target to compare the others with.
         */
        private final BonusTarget target;

        /**
         * Constructing the predicate with a given target.
         *
         * @param targetInput the target to rule them all.
         */
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

    /**
     * List filtering predicate for when the bonus scope is the same as the one
     * given in the constructor.
     */
    private static class SameScopePredicate implements Predicate {

        /**
         * The bonusscope to compare the others with.
         */
        private final BonusScope scope;

        /**
         * Constructing the predicate with a given scope.
         *
         * @param scopeInput the scope to rule them all.
         */
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

    /**
     * List filtering predicate for when the bonus target is part of the list
     * given in the constructor.
     */
    private static class InTargetsPredicate implements Predicate {

        /**
         * The list of targets to compare the others with.
         */
        private final BonusTargets targets;

        /**
         * Constructing the predicate with a given collection of targets.
         *
         * @param targetsInput the targets to rule them all.
         */
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

    /**
     * Ready to use empty collection of boni.
     */
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

    /**
     * Adds a further collection of boni to the instance.
     *
     * @param boniInput the boni to add.
     */
    public final void addBoni(final Boni boniInput) {
        if (boniInput == null) {
            return;
        }
        this.elements.addAll(boniInput.elements);
    }

    /**
     * Adds a further single bonus to the instance.
     *
     * @param bonusInput the bonus to add.
     */
    public final void addBonus(final Bonus bonusInput) {
        this.elements.add(bonusInput);
    }

    /**
     * Returns only those boni of this collection that correspond to the given
     * target.
     *
     * @param target the target needed.
     * @return the boni of that target.
     */
    public final Boni filterByTarget(final BonusTarget target) {
        Boni filteredBoni = new Boni();

        filteredBoni.addBoni(CollectionUtils.select(this.elements,
                new SameTargetPredicate(target)));

        return filteredBoni;
    }

    /**
     * Returns only those boni of this collection that correspond to the given
     * scope.
     *
     * @param scope the scope needed.
     * @return the boni of that scope.
     */
    public final Boni filterByScope(final BonusScope scope) {
        Boni filteredBoni = new Boni();

        filteredBoni.addBoni(CollectionUtils.select(this.elements,
                new SameScopePredicate(scope)));

        return filteredBoni;
    }

    /**
     * Returns only those boni of this collection that correspond to the given
     * list of target.
     *
     * @param targets the targets needed (or-connected).
     * @return the boni of that targets.
     */
    public final Boni filterByTargets(final BonusTargets targets) {
        Boni filteredBoni = new Boni();

        filteredBoni.addBoni(CollectionUtils.select(this.elements,
                new InTargetsPredicate(targets)));

        return filteredBoni;
    }

    /**
     * Retrieves an iterator over the collection of boni.
     *
     * @return the iterator.
     */
    public final Iterator<Bonus> iterator() {
        return this.elements.iterator();
    }

    /**
     * Tests if the given bonus is part of the list of boni.
     *
     * @param bonus the bonus to test.
     * @return true if bonus was found, false otherwise.
     */
    public final boolean contains(final Bonus bonus) {
        return this.elements.contains(bonus);
    }

}
