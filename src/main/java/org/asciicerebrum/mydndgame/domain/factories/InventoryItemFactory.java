package org.asciicerebrum.mydndgame.domain.factories;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.ruleentities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.SpecialAbilities;
import org.asciicerebrum.mydndgame.domain.ruleentities.SpecialAbility;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.ruleentities.InventoryItemPrototype;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Condition;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Conditions;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 * @param <T> the type of the inventory item to build.
 */
public abstract class InventoryItemFactory<T extends InventoryItem>
        implements EntityFactory<InventoryItem> {

    /**
     * The factory for the conditions.
     */
    private EntityFactory<Condition> conditionFactory;

    @Override
    public final InventoryItem newEntity(
            final EntitySetup setup, final Campaign campaign,
            final Reassignments reassignments) {

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
        SpecialAbilities specialAbilities = new SpecialAbilities();
        concreteItem.setSpecialAbilities(specialAbilities);

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
        this.fillConditions(setup, concreteItem, campaign, reassignments);

        this.finalizeCreation(concreteItem);

        return concreteItem;
    }

    @Override
    public void reAssign(final EntitySetup setup,
            final InventoryItem entity, final Campaign campaign,
            final Reassignments reassignments) {
        // nothing to do here.
    }

    /**
     * Setting up the conditions the item is in. Conditions are not mandatory.
     *
     * @param setup the inventory item setup.
     * @param inventoryItem the inventory item to set up.
     * @param campaign the campaign as the central entity map.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillConditions(final EntitySetup setup,
            final InventoryItem inventoryItem,
            final Campaign campaign, final Reassignments reassignments) {
        final Conditions conditions = new Conditions();
        final List<EntitySetup> conditionSetups
                = setup.getPropertySetups(SetupProperty.CONDITIONS);
        if (conditionSetups != null) {
            for (EntitySetup conditionSetup : conditionSetups) {
                conditions.add(this.getConditionFactory()
                        .newEntity(conditionSetup, campaign, reassignments));
            }
            inventoryItem.setConditions(conditions);
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
