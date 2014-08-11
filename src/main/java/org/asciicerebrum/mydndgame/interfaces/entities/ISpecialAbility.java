package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author species8472
 */
public interface ISpecialAbility extends Identifiable {

    /**
     * @return the boni
     */
    List<IBonus> getBoni();

    /**
     * @param boni the boni to set
     */
    void setBoni(List<IBonus> boni);

    /**
     * @return the observers
     */
    List<IObserver> getObservers();

    /**
     * @param observers the observers to set
     */
    void setObservers(List<IObserver> observers);

    /**
     * @return the subAbilities
     */
    List<ISpecialAbility> getSubAbilities();

    /**
     * @param subAbilities the subAbilities to set
     */
    void setSubAbilities(List<ISpecialAbility> subAbilities);

}
