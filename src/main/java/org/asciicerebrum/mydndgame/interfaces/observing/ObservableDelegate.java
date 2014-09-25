package org.asciicerebrum.mydndgame.interfaces.observing;

import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import java.util.List;
import java.util.Map;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;

/**
 *
 * @author species8472
 */
public interface ObservableDelegate {

    /**
     * Register an observer together with its designated hook enum.
     *
     * @param hook the enum hook for the given observer.
     * @param observer the observer object registered for this hook.
     * @param observerMap the map to register the listeners to.
     */
    void registerListener(ObserverHook hook, IObserver observer,
            Map<ObserverHook, List<IObserver>> observerMap);

    /**
     * Removes an observer from the hook's list.
     *
     * @param hook the enum hook for the given observer.
     * @param observer the observer object that is to be removed.
     * @param observerMap the map to unregister the listeners from.
     */
    void unregisterListener(ObserverHook hook, IObserver observer,
            Map<ObserverHook, List<IObserver>> observerMap);

    /**
     * Runs the list of overservers associated with the given hook.
     *
     * @param hook the hook to identify the correct list of observers.
     * @param object the object to modify and return again.
     * @param observerMap the map to get the registered listeners from.
     * @param character the contextual character needed to make the correct
     * modifications.
     * @return the modified object.
     */
    Object triggerObservers(ObserverHook hook, Object object,
            Map<ObserverHook, List<IObserver>> observerMap,
            ICharacter character);
}
