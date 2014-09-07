package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author species8472
 */
public interface IConditionType extends Identifiable {

    /**
     * @return the observers
     */
    List<IObserver> getObservers();

    /**
     * @param observers the observers to set
     */
    void setObservers(List<IObserver> observers);

}
