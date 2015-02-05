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

    private Campaign campaign;

    @Override
    public PersonalizedBodySlot newEntity(final EntitySetup setup,
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

    public void reAssign(final EntitySetup setup,
            final PersonalizedBodySlot entity) {

        this.assignItem(setup, entity);
    }

    public boolean assignItem(final EntitySetup setup,
            final PersonalizedBodySlot entity) {
        String itemId = setup.getProperty(SetupProperty.BODY_SLOT_ITEM);
        boolean itemUnresolved = false;
        if (StringUtils.isNotBlank(itemId)) {
            final UniqueEntity item = this.getCampaign().getEntityById(
                    new UniqueId(itemId));
            itemUnresolved = item == null;
            entity.setItem(item);
        }
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
