package org.asciicerebrum.mydndgame.interfaces.observing;

import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import java.util.List;
import java.util.Map;

/**
 *
 * @author species8472
 */
public interface Observable {

    /**
     *
     * @return the observable delegate service.
     */
    ObservableDelegate getObservableDelegate();

    /**
     * Sets the observable delegate service.
     *
     * @param observableDelegate the observable delegate to set.
     */
    void setObservableDelegate(ObservableDelegate observableDelegate);

    /**
     *
     * @return the map of registered observers with their hooks.
     */
    Map<ObserverHook, List<IObserver>> getObserverMap();

    /**
     *
     * @param observerMap the map of registered observers with their hooks.
     */
    void setObserverMap(Map<ObserverHook, List<IObserver>> observerMap);
}
