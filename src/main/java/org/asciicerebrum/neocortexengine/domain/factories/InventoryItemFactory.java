package org.asciicerebrum.neocortexengine.domain.factories;

import java.util.List;
import org.asciicerebrum.neocortexengine.domain.ruleentities.SizeCategory;
import org.asciicerebrum.neocortexengine.domain.ruleentities.SpecialAbilities;
import org.asciicerebrum.neocortexengine.domain.ruleentities.SpecialAbility;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.InventoryItem;
import org.asciicerebrum.neocortexengine.domain.ruleentities.InventoryItemPrototype;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.Condition;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.Conditions;
import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.domain.setup.SetupProperty;
import org.asciicerebrum.neocortexengine.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public abstract class InventoryItemFactory
        implements EntityFactory<InventoryItem> {

    /**
     * The factory for the conditions.
     */
    private EntityFactory<Condition> conditionFactory;

    @Override
    public final InventoryItem newEntity(final EntitySetup setup) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the inventory "
                    + "item is not complete.");
        }

        InventoryItem concreteItem = this.getConcreteInventoryItem();
        concreteItem.setUniqueId(new UniqueId(
                setup.getProperty(SetupProperty.UNIQUEID)));

        InventoryItemPrototype iiProto = ApplicationContextProvider
                .getApplicationContext().getBean(
                        setup.getProperty(SetupProperty.NAME),
                        InventoryItemPrototype.class);
        concreteItem.setInventoryItemPrototype(iiProto);
        SizeCategory sizeCategory
                = ApplicationContextProvider
                .getApplicationContext().getBean(
                        setup.getProperty(SetupProperty.SIZE_CATEGORY),
                        SizeCategory.class);
        concreteItem.setSizeCategory(sizeCategory);
        final SpecialAbilities specialAbilities
                = concreteItem.getSpecialAbilities();

        // adding special abilities
        if (setup.getProperties(SetupProperty.SPECIAL_ABILITIES) != null) {
            for (String specialAbilityKey
                    : setup.getProperties(SetupProperty.SPECIAL_ABILITIES)) {
                SpecialAbility specAb = ApplicationContextProvider
                        .getApplicationContext()
                        .getBean(specialAbilityKey, SpecialAbility.class);
                specialAbilities.add(specAb);
            }
        }

        // conditions
        this.fillConditions(setup, concreteItem);

        this.finalizeCreation(concreteItem);

        return concreteItem;
    }

    /**
     * Setting up the conditions the item is in. Conditions are not mandatory.
     *
     * @param setup the inventory item setup.
     * @param inventoryItem the inventory item to set up.
     */
    final void fillConditions(final EntitySetup setup,
            final InventoryItem inventoryItem) {
        final Conditions conditions = inventoryItem.getConditions();
        final List<EntitySetup> conditionSetups
                = setup.getPropertySetups(SetupProperty.CONDITIONS);
        if (conditionSetups != null) {
            for (EntitySetup conditionSetup : conditionSetups) {
                conditions.add(this.getConditionFactory()
                        .newEntity(conditionSetup));
            }
        }
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
     * @return the conditionFactory
     */
    public final EntityFactory<Condition> getConditionFactory() {
        return conditionFactory;
    }

    /**
     * @param conditionFactoryInput the conditionFactory to set
     */
    public final void setConditionFactory(
            final EntityFactory<Condition> conditionFactoryInput) {
        this.conditionFactory = conditionFactoryInput;
    }

}
