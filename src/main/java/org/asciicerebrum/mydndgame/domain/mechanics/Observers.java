package org.asciicerebrum.mydndgame.domain.mechanics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.asciicerebrum.mydndgame.domain.mechanics.Observer.ObserverScope;

/**
 *
 * @author species8472
 */
public class Observers {

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
                new Predicate() {

                    public boolean evaluate(Object object) {
                        Observer potentialObserver = (Observer) object;
                        return observerHook.equals(potentialObserver.getHook());
                    }
                }, filteredList);

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
                new Predicate() {

                    public boolean evaluate(Object object) {
                        Observer potentialObserver = (Observer) object;

                        return observerHooks.contains(
                                potentialObserver.getHook());
                    }
                }, filteredList);

        Observers filteredObservers = new Observers();
        filteredObservers.setObservers(filteredList);
        return filteredObservers;
    }

    public Observers filterByScope(final ObserverScope scope) {
        List<Observer> filteredList = new ArrayList<Observer>();

        CollectionUtils.select(this.elements,
                new Predicate() {

                    public boolean evaluate(Object object) {
                        Observer potentialObserver = (Observer) object;

                        return scope.equals(potentialObserver.getScope());
                    }
                }, filteredList);

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
