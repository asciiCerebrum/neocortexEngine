package org.asciicerebrum.mydndgame.domain.mechanics.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observer.ObserverScope;

/**
 *
 * @author species8472
 */
public class Observers {

    /**
     * Filtering list predicate for when the hook of an observer is the same as
     * the one given in the constructor.
     */
    private static class SameHookPredicate implements Predicate {

        /**
         * The observer hook to compare the others with.
         */
        private final ObserverHook observerHook;

        /**
         * Constructing the predicate with a given hook.
         *
         * @param observerHookInput the hook to rule them all.
         */
        public SameHookPredicate(final ObserverHook observerHookInput) {
            this.observerHook = observerHookInput;
        }

        @Override
        public final boolean evaluate(final Object object) {
            Observer potentialObserver = (Observer) object;
            return this.observerHook.equals(potentialObserver.getHook());
        }
    }

    /**
     * Filtering list predicate for when the hook of an observer is part of the
     * list of hooks given in the constructor.
     */
    private static class InHooksPredicate implements Predicate {

        /**
         * The list of observer hooks to compare the others with.
         */
        private final ObserverHooks observerHooks;

        /**
         * Constructing the predicate with a given list of hooks.
         *
         * @param observerHooksInput the list of hooks.
         */
        public InHooksPredicate(final ObserverHooks observerHooksInput) {
            this.observerHooks = observerHooksInput;
        }

        @Override
        public final boolean evaluate(final Object object) {
            Observer potentialObserver = (Observer) object;
            return this.observerHooks.contains(
                    potentialObserver.getHook());
        }
    }

    /**
     * Filtering list predicate for when the scope of an observer is the same as
     * the one given in the constructor.
     */
    private static class SameScopePredicate implements Predicate {

        /**
         * The observer scope to compare the others with.
         */
        private final ObserverScope scope;

        /**
         * Constructing the predicate with a given scope.
         *
         * @param scopeInput the scope to rule them all.
         */
        public SameScopePredicate(final ObserverScope scopeInput) {
            this.scope = scopeInput;
        }

        @Override
        public final boolean evaluate(final Object object) {
            Observer potentialObserver = (Observer) object;
            return this.scope.equals(potentialObserver.getScope());
        }
    }

    /**
     * Ready to use empty collection of observers.
     */
    public static final Observers EMPTY_OBSERVERS = new Observers();

    /**
     * The list of observers.
     */
    private final List<Observer> elements = new ArrayList<Observer>();

    /**
     * @param observersInput the observers to set
     */
    public final void setObservers(final List<Observer> observersInput) {
        this.elements.addAll(observersInput);
    }

    /**
     * Return only those observers that correspond to a given hook.
     *
     * @param observerHook the hook all observers should have.
     * @return the observers having this kind of hook.
     */
    public final Observers filterByHook(final ObserverHook observerHook) {

        List<Observer> filteredList = new ArrayList<Observer>();

        CollectionUtils.select(this.elements,
                new SameHookPredicate(observerHook), filteredList);

        Observers filteredObservers = new Observers();
        filteredObservers.setObservers(filteredList);
        return filteredObservers;
    }

    /**
     * The hooks are OR-connected.
     *
     * @param observerHooks the hooks that are needed.
     * @return the observers having a hook being part of the list.
     */
    public final Observers filterByHooks(final ObserverHooks observerHooks) {
        List<Observer> filteredList = new ArrayList<Observer>();

        CollectionUtils.select(this.elements,
                new InHooksPredicate(observerHooks), filteredList);

        Observers filteredObservers = new Observers();
        filteredObservers.setObservers(filteredList);
        return filteredObservers;
    }

    /**
     * Return only those observers that correspond to a given scope.
     *
     * @param scope the scope all obervers should have.
     * @return the observers having this kind of scope.
     */
    public final Observers filterByScope(final ObserverScope scope) {
        List<Observer> filteredList = new ArrayList<Observer>();

        CollectionUtils.select(this.elements,
                new SameScopePredicate(scope), filteredList);

        Observers filteredObservers = new Observers();
        filteredObservers.setObservers(filteredList);
        return filteredObservers;
    }

    /**
     * Adding an additional collection of observers to this instance.
     *
     * @param observersInput the observers to add.
     */
    public final void add(final Observers observersInput) {
        this.elements.addAll(observersInput.elements);
    }

    /**
     * Adding a single additional observer to this collection.
     *
     * @param observerInput the observer to add.
     */
    public final void add(final Observer observerInput) {
        this.elements.add(observerInput);
    }

    /**
     * Tests if a given observer is part of the collection.
     *
     * @param observer the observer to test.
     * @return true if part of the list, false otherwise.
     */
    public final boolean contains(final Observer observer) {
        return this.elements.contains(observer);
    }

    /**
     * Retrieves an iterator over the collection of observers.
     *
     * @return the iterator.
     */
    public final Iterator<Observer> iterator() {
        return this.elements.iterator();
    }

}
