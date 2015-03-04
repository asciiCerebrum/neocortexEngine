package org.asciicerebrum.mydndgame.domain.factories;

import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotType;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlotFactory
        implements EntityFactory<PersonalizedBodySlot> {

    /**
     * Spring context to get the beans from.
     */
    private ApplicationContext context;

    /**
     * The campaign holding the basic data of encounters and characters.
     */
    private Campaign campaign;

    @Override
    public final PersonalizedBodySlot newEntity(final EntitySetup setup,
            final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the body slot "
                    + " is not complete.");
        }

        final PersonalizedBodySlot personalizedBodySlot
                = new PersonalizedBodySlot();
        final BodySlot bluePrintSlot = new BodySlot();

        bluePrintSlot.setBodySlotType(getContext().getBean(
                setup.getProperty(SetupProperty.BODY_SLOT_TYPE),
                BodySlotType.class));

        final String isPrimaryAttack
                = setup.getProperty(SetupProperty.BODY_SLOT_PRIMARY_ATTACK);
        if (StringUtils.isNotBlank(isPrimaryAttack)) {
            bluePrintSlot.setIsPrimaryAttackSlot(
                    new AttackAbility(isPrimaryAttack));
        }
        personalizedBodySlot.setBodySlot(bluePrintSlot);

        return personalizedBodySlot;
    }

    @Override
    public final void reAssign(final EntitySetup setup,
            final PersonalizedBodySlot entity) {

        this.assignItem(setup, entity);
    }

    /**
     * Assigns an item by retrieving it from the context or campaign and putting
     * it into the slot.
     *
     * @param setup the setup of the personalized body slot.
     * @param entity the body slot to be filled with an item.
     * @return true, if the item could NOT be resolved (NOT found in the
     * campaign), false otherwise.
     */
    public final boolean assignItem(final EntitySetup setup,
            final PersonalizedBodySlot entity) {
        String itemId = setup.getProperty(SetupProperty.BODY_SLOT_ITEM);
        // itemId cannot be null as it is mandatory!
        final UniqueEntity item = this.getCampaign().getEntityById(
                new UniqueId(itemId));
        boolean itemUnresolved = item == null;
        entity.setItem(item);

        return itemUnresolved;
    }

    /**
     * @param contextInput the context to set
     */
    public final void setContext(final ApplicationContext contextInput) {
        this.context = contextInput;
    }

    /**
     * @param campaignInput the campaign to set
     */
    public final void setCampaign(final Campaign campaignInput) {
        this.campaign = campaignInput;
    }

    /**
     * @return the context
     */
    public final ApplicationContext getContext() {
        return context;
    }

    /**
     * @return the campaign
     */
    public final Campaign getCampaign() {
        return campaign;
    }

}
