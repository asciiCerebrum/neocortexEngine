package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;
import org.asciicerebrum.mydndgame.interfaces.observing.ObservableDelegate;

/**
 *
 * @author species8472
 */
public class DefaultObservableDelegate implements ObservableDelegate {

    /**
     * {@inheritDoc}
     */
    @Override
    public final void registerListener(final ObserverHook hook,
            final IObserver observer,
            final Map<ObserverHook, List<IObserver>> observerMap) {

        List<IObserver> hookList = observerMap.get(hook);
        if (hookList == null) {
            hookList = new ArrayList<IObserver>();
            observerMap.put(hook, hookList);
        }
        hookList.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void unregisterListener(final ObserverHook hook,
            final IObserver observer,
            final Map<ObserverHook, List<IObserver>> observerMap) {

        List<IObserver> hookList = observerMap.get(hook);
        if (hookList != null) {
            hookList.remove(observer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object triggerObservers(final ObserverHook hook,
            final Object object,
            final Map<ObserverHook, List<IObserver>> observerMap,
            final ICharacter character) {
        List<IObserver> hookList = observerMap.get(hook);
        if (hookList == null) {
            return object;
        }
        Object modifiableObject = object;
        for (IObserver observer : hookList) {
            modifiableObject = observer.trigger(modifiableObject, character);
        }
        return modifiableObject;
    }
}
