package org.asciicerebrum.neocortexengine.domain.mechanics.bonus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusTarget;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusTargets;

/**
 *
 * @author species8472
 */
public class ContextBoni {

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
            final ContextBonus ctxBonus = (ContextBonus) o;
            return target.equals(ctxBonus.getBonus().getTarget());
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
            final ContextBonus ctxBonus = (ContextBonus) o;
            return targets.contains(ctxBonus.getBonus().getTarget());
        }
    }

    /**
     * List filtering predicate for the scope. The bonus is valid in case of
     * Scope ALL or (when Scope SPECIFIC) if its context equals the target
     * entity
     */
    private static class SameScopePredicate implements Predicate {

        /**
         * The bonusscope to compare the others with.
         */
        private final UniqueEntity targetEntity;

        /**
         * Constructing the predicate with a given scope.
         *
         * @param targetEntityInput the target entity defining the scope.
         */
        public SameScopePredicate(final UniqueEntity targetEntityInput) {
            this.targetEntity = targetEntityInput;
        }

        @Override
        public final boolean evaluate(final Object o) {
            final ContextBonus ctxBonus = (ContextBonus) o;
            return Bonus.BonusScope.ALL.equals(ctxBonus.getBonus().getScope())
                    || this.targetEntity.equals(ctxBonus.getContext());
        }
    }

    /**
     * Ready to use empty collection of contextual boni.
     */
    public static final ContextBoni EMPTY_CONTEXT_BONI = new ContextBoni();

    /**
     * Central collection of this instance.
     */
    private final List<ContextBonus> elements = new ArrayList<ContextBonus>();

    /**
     * Standard empty contructor for an empty collection.
     */
    public ContextBoni() {

    }

    /**
     * Build a collection of context boni by a given list of boni with their
     * context.
     *
     * @param boni the boni to start from.
     * @param context the context of these boni.
     */
    public ContextBoni(final Boni boni, final UniqueEntity context) {
        this.add(boni, context);
    }

    /**
     * Adds a further collection of context boni to the instance.
     *
     * @param contextBoni the contextual boni to add.
     */
    public final void add(final ContextBoni contextBoni) {
        this.elements.addAll(contextBoni.elements);
    }

    /**
     * Add boni with their context to the collection.
     *
     * @param boni the boni to start from.
     * @param context the context of these boni.
     */
    public final void add(final Boni boni, final UniqueEntity context) {
        if (boni == null) {
            return;
        }
        final Iterator<Bonus> bonusIterator = boni.iterator();
        while (bonusIterator.hasNext()) {
            final Bonus bonus = bonusIterator.next();
            final ContextBonus ctxBonus = new ContextBonus(bonus, context);

            this.elements.add(ctxBonus);
        }
    }

    /**
     * Add a bonus with its context to the collection.
     *
     * @param bonus the bonus to start from.
     * @param context the context of these boni.
     */
    public final void add(final Bonus bonus, final UniqueEntity context) {
        if (bonus == null) {
            return;
        }
        this.elements.add(new ContextBonus(bonus, context));
    }

    /**
     * Adds a further context bonus to the instance.
     *
     * @param contextBonus the contextual bonus to add.
     */
    public final void add(final ContextBonus contextBonus) {
        this.elements.add(contextBonus);
    }

    /**
     * @param contextBoniInput the boni to set
     */
    public final void add(final Collection<ContextBonus> contextBoniInput) {
        this.elements.addAll(contextBoniInput);
    }

    /**
     * @return the iterator over the collection.
     */
    public final Iterator<ContextBonus> iterator() {
        return this.elements.iterator();
    }

    /**
     * Returns only those context boni of this collection that correspond to the
     * given target.
     *
     * @param target the target needed.
     * @return the context boni of that target.
     */
    public final ContextBoni filterByTarget(final BonusTarget target) {
        final ContextBoni filteredBoni = new ContextBoni();

        filteredBoni.add(CollectionUtils.select(this.elements,
                new ContextBoni.SameTargetPredicate(target)));

        return filteredBoni;
    }

    /**
     * Returns only those context boni of this collection that correspond to the
     * given list of target.
     *
     * @param targets the targets needed (or-connected).
     * @return the context boni of that targets.
     */
    public final ContextBoni filterByTargets(final BonusTargets targets) {
        final ContextBoni filteredBoni = new ContextBoni();

        filteredBoni.add(CollectionUtils.select(this.elements,
                new ContextBoni.InTargetsPredicate(targets)));

        return filteredBoni;
    }

    /**
     * Returns only those context boni of this collection that correspond to the
     * given target entity as their scope.
     *
     * @param targetEntity the target entity the scope is valid for.
     * @return the context boni of that scope.
     */
    public final ContextBoni filterByScope(final UniqueEntity targetEntity) {
        final ContextBoni filteredBoni = new ContextBoni();

        filteredBoni.add(CollectionUtils.select(this.elements,
                new SameScopePredicate(targetEntity)));

        return filteredBoni;
    }

    /**
     * The size of the underlying list.
     *
     * @return the number of context boni.
     */
    public final int size() {
        return this.elements.size();
    }

}
