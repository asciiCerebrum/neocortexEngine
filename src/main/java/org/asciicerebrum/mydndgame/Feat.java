package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;

/**
 *
 * @author species8472
 */
public class Feat {

    /**
     * Unique id of the feat.
     */
    private String id;

    /**
     * The observers of this feat. They are designed to be registered in the
     * targeted character to modify certain values of all kinds of attributes.
     */
    private List<IObserver> observers = new ArrayList<IObserver>();

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * @return the observers
     */
    public final List<IObserver> getObservers() {
        return observers;
    }

    /**
     * @param observersInput the observers to set
     */
    public final void setObservers(final List<IObserver> observersInput) {
        this.observers = observersInput;
    }

}
