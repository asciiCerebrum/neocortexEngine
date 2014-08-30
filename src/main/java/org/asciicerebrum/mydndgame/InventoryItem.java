package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusSource;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.ISizeCategory;
import org.asciicerebrum.mydndgame.interfaces.entities.ISpecialAbility;
import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;
import org.asciicerebrum.mydndgame.interfaces.observing.ObservableDelegate;

/**
 * Abstract prototype class for everything a DND Character can carry.
 *
 * @author species8472
 */
public abstract class InventoryItem implements IInventoryItem, BonusSource {

    /**
     * Unique id String. This is not set in the prototype spring bean
     * definition. So this value has to be determined on creation time.
     */
    private String id;

    /**
     * Name of this prototypicl object. E.g. longsword.
     */
    private String name;

    /**
     * Weight of the object.
     */
    private Long weight;

    /**
     * Base price without modifications (like mwk, magic, etc.) always in gp of
     * the object.
     */
    private Long baseCost;

    /**
     * Size category of the object.
     */
    private ISizeCategory size;

    /**
     * Collection of special abilities of this item. E.g. mwk, magical, etc.
     */
    @BonusGranter
    private List<ISpecialAbility> specialAbilities
            = new ArrayList<ISpecialAbility>();

    /**
     * Service for the handling of observable calls and registering into the
     * hooks.
     */
    private ObservableDelegate observableDelegate;

    /**
     * Holder for the observer hooks and their corresponding list of observers.
     */
    private Map<ObserverHook, List<IObserver>> observerMap
            = new EnumMap<ObserverHook, List<IObserver>>(ObserverHook.class);

    /**
     * Boni directly granted from the inventory item.
     */
    private List<IBonus> boni = new ArrayList<IBonus>();

    /**
     * The observers of this item. They are designed to be registered in the
     * targeted item to modify certain values of all kinds of attributes.
     */
    private List<IObserver> observers = new ArrayList<IObserver>();

    /**
     * @return the id
     */
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
    public final String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setName(final String nameInput) {
        this.name = nameInput;
    }

    /**
     * @return the weight
     */
    public final Long getWeight() {
        return weight;
    }

    /**
     * @param weightInput the weight to set
     */
    public final void setWeight(final Long weightInput) {
        this.weight = weightInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getBaseCost() {
        return this.baseCost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setBaseCost(final Long baseCostInput) {
        this.baseCost = baseCostInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ISizeCategory getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setSize(final ISizeCategory sizeInput) {
        this.size = sizeInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ObservableDelegate getObservableDelegate() {
        return observableDelegate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setObservableDelegate(
            final ObservableDelegate observableDelegateInput) {
        this.observableDelegate = observableDelegateInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<ObserverHook, List<IObserver>> getObserverMap() {
        return observerMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setObserverMap(
            final Map<ObserverHook, List<IObserver>> observerMapInput) {
        this.observerMap = observerMapInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getCost() {
        return (Long) this.getObservableDelegate()
                .triggerObservers(
                        ObserverHook.PRICE, this.baseCost,
                        this.getObserverMap(), null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ISpecialAbility> getSpecialAbilities() {
        return specialAbilities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setSpecialAbilities(
            final List<ISpecialAbility> specialAbilitiesInput) {
        this.specialAbilities = specialAbilitiesInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Boolean resembles(final IInventoryItem item) {
        if (item == null) {
            return Boolean.FALSE;
        }
        return item.getName().equals(this.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<IBonus> getBoni() {
        return boni;
    }

    /**
     * @param boniInput the boni to set
     */
    public final void setBoni(final List<IBonus> boniInput) {
        this.boni = boniInput;
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
    public final void adaptToSize(final ISizeCategory newSize) {
        //TODO implement this
        // damage
        // weight
        // cost
        // range increment
        // shift in encumbrance regarding a medium sized creature

        this.setSize(newSize);
    }
}
