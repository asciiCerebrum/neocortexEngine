package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IFeat;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.IParameterSetter;

/**
 *
 * @author species8472
 */
public class Feat implements IFeat {

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
     * Some feats are restricted with a parameter. E.g. exotic weapon
     * proficiency is valid only for a certain type of weapon.
     */
    private IParameterSetter parameterSetter;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setParameter(final Object parameter) {
        this.getParameterSetter().setFeatParameter(this, parameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IParameterSetter getParameterSetter() {
        return parameterSetter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setParameterSetter(
            final IParameterSetter parameterSetterInput) {
        this.parameterSetter = parameterSetterInput;
    }

}
