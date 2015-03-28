package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotType;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlotFactory
        implements EntityFactory<PersonalizedBodySlot> {

    @Override
    public final PersonalizedBodySlot newEntity(final EntitySetup setup,
            final Campaign campaign) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the body slot "
                    + " is not complete.");
        }

        return this.build(setup, campaign);
    }

    /**
     * Central builder for the personalized body slot. It is used by multiple
     * public methods of this class.
     *
     * @param setup the setup of the specific object to create.
     * @param campaign the campaign as the central entity map.
     * @return the created instance.
     */
    private PersonalizedBodySlot build(final EntitySetup setup,
            final Campaign campaign) {

        // retrieve holder from campaign
        final DndCharacter holder
                = (DndCharacter) this.retrieveHolder(setup, campaign);
        if (holder == null) {
            campaign.addReassignmentEntry(this, setup, null);
            return null;
        }

        // generating the blue print body slot from the given setup
        final PersonalizedBodySlot personalizedBodySlot
                = new PersonalizedBodySlot();
        final BodySlot bluePrintSlot = new BodySlot();

        bluePrintSlot.setBodySlotType(ApplicationContextProvider
                .getApplicationContext().getBean(
                        setup.getProperty(SetupProperty.BODY_SLOT_TYPE),
                        BodySlotType.class));

        bluePrintSlot.setIsPrimaryAttackSlot(
                new AttackAbility(setup.getProperty(
                                SetupProperty.BODY_SLOT_PRIMARY_ATTACK)));

        personalizedBodySlot.setBodySlot(bluePrintSlot);

        // retrieving a resembling personalized body slot from the character
        // Here this personalized body slot is retrieved by comparing the
        // attributes.
        final PersonalizedBodySlot realBodySlot
                = holder.getPersonalizedBodySlots()
                .findFirstSimilar(personalizedBodySlot,
                        PersonalizedBodySlot.Facet.BODY_SLOT_TYPE,
                        PersonalizedBodySlot.Facet.PRIMARY_ATTACK_SLOT,
                        PersonalizedBodySlot.Facet.ITEM);

        // Retrieving the item for the slot
        final UniqueEntity item = this.retrieveItem(setup, campaign);
        if (item == null) {
            campaign.addReassignmentEntry(this, setup, null);
            return null;
        }
        realBodySlot.setItem(item);
        return realBodySlot;
    }

    /**
     * Retrieves a holder (dndCharacter) from the campaign.
     *
     * @param setup the setup of the personalized body slot.
     * @param campaign the campaign as the central entity map.
     * @return the holder as a unique entity.
     */
    final UniqueEntity retrieveHolder(final EntitySetup setup,
            final Campaign campaign) {
        String holderId = setup.getProperty(SetupProperty.BODY_SLOT_HOLDER);
        // holderId cannot be null as it is mandatory!
        return campaign.getEntityById(new UniqueId(holderId));
    }

    /**
     * Retrieves an item from the campaign .
     *
     * @param setup the setup of the personalized body slot.
     * @param campaign the campaign as the central entity map.
     * @return the item as a unique entity.
     */
    final UniqueEntity retrieveItem(final EntitySetup setup,
            final Campaign campaign) {
        String itemId = setup.getProperty(SetupProperty.BODY_SLOT_ITEM);
        // itemId cannot be null as it is mandatory!
        return campaign.getEntityById(new UniqueId(itemId));
    }

    @Override
    public final void reAssign(final EntitySetup setup,
            final PersonalizedBodySlot entity,
            final Campaign campaign) {
        this.build(setup, campaign);
    }

}
