package org.asciicerebrum.mydndgame.observers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.observers.IObserver.ObserverScope;

/**
 *
 * @author species8472
 */
public class Observers {

    public static final Observers EMPTY_OBSERVERS = new Observers();

    /**
     * The list of observers.
     */
    private final List<IObserver> elements = new ArrayList<IObserver>();

    /**
     * @param observersInput the observers to set
     */
    public final void setObservers(final List<IObserver> observersInput) {
        this.elements.addAll(observersInput);
    }

    public Observers filterByHook(final ObserverHook observerHook) {

        List<IObserver> filteredList = new ArrayList<IObserver>();

        CollectionUtils.select(this.elements,
                new Predicate() {

                    public boolean evaluate(Object object) {
                        IObserver potentialObserver = (IObserver) object;
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
        List<IObserver> filteredList = new ArrayList<IObserver>();

        CollectionUtils.select(this.elements,
                new Predicate() {

                    public boolean evaluate(Object object) {
                        IObserver potentialObserver = (IObserver) object;

                        return observerHooks.contains(
                                potentialObserver.getHook());
                    }
                }, filteredList);

        Observers filteredObservers = new Observers();
        filteredObservers.setObservers(filteredList);
        return filteredObservers;
    }

    public Observers filterByScope(final ObserverScope scope) {
        List<IObserver> filteredList = new ArrayList<IObserver>();

        CollectionUtils.select(this.elements,
                new Predicate() {

                    public boolean evaluate(Object object) {
                        IObserver potentialObserver = (IObserver) object;

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

    public Object trigger(final Object object,
            final DndCharacter dndCharacter) {
        Object modificatedObject = object;
        for (final IObserver observer : this.elements) {
            modificatedObject = observer.trigger(modificatedObject,
                    dndCharacter);
        }
        return modificatedObject;
    }

    public final boolean contains(final IObserver observer) {
        return this.elements.contains(observer);
    }

    public final Iterator<IObserver> iterator() {
        return this.elements.iterator();
    }

}
