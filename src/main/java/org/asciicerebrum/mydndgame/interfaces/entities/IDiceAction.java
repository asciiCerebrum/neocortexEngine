package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author Tabea Raab
 */
public interface IDiceAction {

    /**
     * @return the observers
     */
    List<IObserver> getTargetObservers();

    /**
     * @param observers the observers to set
     */
    void setTargetObservers(List<IObserver> observers);

    /**
     * @return the diceType
     */
    IDice getDiceType();

    /**
     * @param diceType the diceType to set
     */
    void setDiceType(IDice diceType);

    /**
     * @return the diceNumber
     */
    Long getDiceNumber();

    /**
     * @param diceNumber the diceNumber to set
     */
    void setDiceNumber(Long diceNumber);

}
