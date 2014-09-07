package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IConditionType;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;

/**
 *
 * @author species8472
 */
public class ConditionType implements IConditionType {

    /**
     * The unique id of this condition type.
     */
    private String id;
    /**
     * The observers of this condition. They are designed to be registered in
     * the targeted character to modify certain values of all kinds of
     * attributes.
     */
    private List<IObserver> observers = new ArrayList<IObserver>();

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<IObserver> getObservers() {
        return observers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setObservers(final List<IObserver> observersInput) {
        this.observers = observersInput;
    }

}
