package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author species8472
 */
public interface IFeat extends Identifiable {

    /**
     * @return the observers
     */
    List<IObserver> getObservers();

    /**
     * @param observers the observers to set
     */
    void setObservers(List<IObserver> observers);

    /**
     * Sets the parameter for the feat. Normally it is delegated to the
     * parameter setter bean.
     *
     * @param parameter the parameter object to set.
     */
    void setParameter(Object parameter);

    /**
     * @return the parameterSetter
     */
    IParameterSetter getParameterSetter();

    /**
     * @param parameterSetter the parameterSetter to set
     */
    void setParameterSetter(IParameterSetter parameterSetter);
}
