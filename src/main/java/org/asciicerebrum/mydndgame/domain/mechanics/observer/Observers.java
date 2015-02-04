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

    private static class SameHookPredicate implements Predicate {

        private final ObserverHook observerHook;

        public SameHookPredicate(final ObserverHook observerHookInput) {
            this.observerHook = observerHookInput;
        }

        @Override
        public final boolean evaluate(final Object object) {
            Observer potentialObserver = (Observer) object;
            return this.observerHook.equals(potentialObserver.getHook());
        }
    }

    private static class InHooksPredicate implements Predicate {

        private final ObserverHooks observerHooks;

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

    private static class SameScopePredicate implements Predicate {

        private final ObserverScope scope;

        public SameScopePredicate(final ObserverScope scopeInput) {
            this.scope = scopeInput;
        }

        @Override
        public final boolean evaluate(final Object object) {
            Observer potentialObserver = (Observer) object;
            return this.scope.equals(potentialObserver.getScope());
        }
    }

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

    public Observers filterByHook(final ObserverHook observerHook) {

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
     * @param observerHooks
     * @return
     */
    public Observers filterByHooks(final ObserverHooks observerHooks) {
        List<Observer> filteredList = new ArrayList<Observer>();

        CollectionUtils.select(this.elements,
                new InHooksPredicate(observerHooks), filteredList);

        Observers filteredObservers = new Observers();
        filteredObservers.setObservers(filteredList);
        return filteredObservers;
    }

    public Observers filterByScope(final ObserverScope scope) {
        List<Observer> filteredList = new ArrayList<Observer>();

        CollectionUtils.select(this.elements,
                new SameScopePredicate(scope), filteredList);

        Observers filteredObservers = new Observers();
        filteredObservers.setObservers(filteredList);
        return filteredObservers;
    }

    public void add(final Observers observersInput) {
        this.elements.addAll(observersInput.elements);
    }

    public final boolean contains(final Observer observer) {
        return this.elements.contains(observer);
    }

    public final Iterator<Observer> iterator() {
        return this.elements.iterator();
    }

}
