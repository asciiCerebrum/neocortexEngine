package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.ruleentities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.SpecialAbilities;
import org.asciicerebrum.mydndgame.domain.ruleentities.SpecialAbility;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.ruleentities.InventoryItemPrototype;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author species8472
 * @param <T> the type of the inventory item to build.
 */
public abstract class InventoryItemFactory<T extends InventoryItem>
        implements EntityFactory<InventoryItem> {

    /**
     * Spring context to get the beans from.
     */
    private ApplicationContext context;

    /**
     * Central method on building the item.
     *
     * @param setup the setup for the item to create.
     * @param reassignments object to setup in case of unresolved dependencies.
     * @return the created unique item.
     */
    @Override
    public final InventoryItem newEntity(
            final EntitySetup setup,
            final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the inventory "
                    + "item is not complete.");
        }

        InventoryItem concreteItem = this.getConcreteInventoryItem();
        concreteItem.setUniqueId(new UniqueId(
                setup.getProperty(SetupProperty.UNIQUEID)));

        InventoryItemPrototype iiProto = this.getContext().getBean(
                setup.getProperty(SetupProperty.NAME),
                InventoryItemPrototype.class);
        concreteItem.setInventoryItemPrototype(iiProto);
        SizeCategory sizeCategory
                = this.getContext().getBean(
                        setup.getProperty(SetupProperty.SIZE_CATEGORY),
                        SizeCategory.class);
        concreteItem.setSizeCategory(sizeCategory);
        SpecialAbilities specialAbilities = new SpecialAbilities();
        concreteItem.setSpecialAbilities(specialAbilities);

        // adding special abilities
        if (setup.getProperties(SetupProperty.SPECIAL_ABILITIES) != null) {
            for (String specialAbilityKey
                    : setup.getProperties(SetupProperty.SPECIAL_ABILITIES)) {
                SpecialAbility specAb = this.getContext()
                        .getBean(specialAbilityKey, SpecialAbility.class);
                specialAbilities.add(specAb);
            }
        }

        this.finalizeCreation(concreteItem);

        // register it as a spring bean for further retrieval
        ConfigurableListableBeanFactory beanFactory
                = ((ConfigurableApplicationContext) this.getContext())
                .getBeanFactory();
        beanFactory.registerSingleton(setup.getProperty(SetupProperty.UNIQUEID),
                concreteItem);

        return concreteItem;
    }

    @Override
    public void reAssign(final EntitySetup setup,
            final InventoryItem entity) {
        // nothing to do here.
    }

    /**
     * Letting the concrete subclassed factories decide which item to return.
     *
     * @return the concrete item.
     */
    protected abstract InventoryItem getConcreteInventoryItem();

    /**
     * Letting the concrete subclass decide how to finalize the item creation
     * based on its specialities.
     *
     * @param inventoryItem the item to finalize in creation.
     */
    protected abstract void finalizeCreation(InventoryItem inventoryItem);

    /**
     * @return the context
     */
    protected final ApplicationContext getContext() {
        return context;
    }

    /**
     * @param contextInput the context to set
     */
    public final void setContext(final ApplicationContext contextInput) {
        this.context = contextInput;
    }

}
