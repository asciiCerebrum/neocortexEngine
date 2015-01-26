package org.asciicerebrum.mydndgame.domain.gameentities;

import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.domain.core.attribution.BodySlotType;
import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupProperty;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class BodySlotFactory implements EntityFactory<BodySlot> {

    /**
     * Spring context to get the beans from.
     */
    private ApplicationContext context;

    private Campaign campaign;

    @Override
    public BodySlot newEntity(final EntitySetup<BodySlot> setup,
            final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the body slot "
                    + " is not complete.");
        }

        BodySlot bodySlot = new BodySlot();

        bodySlot.setBodySlotType(context.getBean(
                setup.getProperty(SetupProperty.BODY_SLOT_TYPE),
                BodySlotType.class));

        final String isPrimaryAttack
                = setup.getProperty(SetupProperty.BODY_SLOT_PRIMARY_ATTACK);
        if (StringUtils.isNotBlank(isPrimaryAttack)) {
            bodySlot.setIsPrimaryAttackSlot(new AttackAbility(isPrimaryAttack));
        }

        return bodySlot;
    }

    public void reAssign(final EntitySetup<BodySlot> setup,
            final BodySlot entity) {

        this.assignItem(setup, entity);
    }

    boolean assignItem(final EntitySetup<BodySlot> setup,
            final BodySlot entity) {
        String itemId = setup.getProperty(SetupProperty.BODY_SLOT_ITEM);
        boolean itemUnresolved = false;
        if (StringUtils.isNotBlank(itemId)) {
            final UniqueEntity item = this.campaign.getEntityById(new UniqueId(
                    itemId));
            itemUnresolved = item == null;
            entity.setItem(item);
        }
        return itemUnresolved;
    }

}
