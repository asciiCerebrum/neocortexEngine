package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author Tabea Raab
 */
public interface IAbility extends Identifiable {

    /**
     * The ability hook associated with this ability.
     *
     * @return the hook.
     */
    ObserverHook getAssociatedHook();

    /**
     * Setter for the associated hook.
     *
     * @param associatedHook the hook to be set.
     */
    void setAssociatedHook(ObserverHook associatedHook);

}
