package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.ISpecialAbility;
import org.asciicerebrum.mydndgame.interfaces.observing.Observable;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public abstract class InventoryItemBuilder {

    /**
     * Setup information for the weapon.
     */
    private final InventoryItemSetup setup;
    /**
     * Spring context to get the beans from.
     */
    private final ApplicationContext context;

    /**
     * Constructor for creating an inventory item builder.
     *
     * @param setupInput the item setup object.
     * @param contextInput the spring application context.
     */
    public InventoryItemBuilder(final InventoryItemSetup setupInput,
            final ApplicationContext contextInput) {
        this.setup = setupInput;
        this.context = contextInput;
    }

    /**
     * Central method on building the item.
     *
     * @param item the item to complete the creation for.
     * @return the created unique item.
     */
    protected final IInventoryItem build(final IInventoryItem item) {

        item.setId(getSetup().getId());

        SizeCategory size = this.getContext().getBean(
                getSetup().getSizeCategory(), SizeCategory.class);

        item.adaptToSize(size);

        // registering item's own observers
        for (IObserver observer : item.getObservers()) {
            ((Observable) item).getObservableDelegate()
                    .registerListener(observer.getHook(), observer,
                            ((Observable) item).getObserverMap());
        }

        // adding special abilities
        for (String specialAbilityKey : getSetup().getSpecialAbilities()) {
            ISpecialAbility specAb = this.getSpecialAbilityFromContext(
                    specialAbilityKey);
            item.getSpecialAbilities().add(specAb);

            // registering special abillity hooks
            for (IObserver observer : specAb.getObservers()) {
                item.getObservableDelegate().registerListener(
                        observer.getHook(), observer,
                        item.getObserverMap());
            }
        }

        return item;
    }

    /**
     * Retrieves the special ability object from the context. The subclasses
     * must manage this because they have the knowledge of what type is needed
     * exactly.
     *
     * @param specialAbilityKey the id of the context bean.
     * @return the special ability object.
     */
    protected abstract ISpecialAbility getSpecialAbilityFromContext(
            final String specialAbilityKey);

    /**
     * @return the setup
     */
    protected final InventoryItemSetup getSetup() {
        return setup;
    }

    /**
     * @return the context
     */
    protected final ApplicationContext getContext() {
        return context;
    }

}
