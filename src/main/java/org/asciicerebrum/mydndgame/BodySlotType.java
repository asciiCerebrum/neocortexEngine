package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;

/**
 *
 * @author species8472
 */
public class BodySlotType implements IBodySlotType {

    /**
     * The unique id of this body slot type. E.g. primaryHand.
     */
    private String id;

    /**
     * The opposite slot for easy navigation between both. E.g. primary and
     * secondary hand.
     */
    private IBodySlotType counterSlot;

    /**
     * Defines if this slot is the character type's primary slot of attack.
     */
    private Boolean isPrimaryAttackSlot;

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
    public final IBodySlotType getCounterSlot() {
        return counterSlot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setCounterSlot(final IBodySlotType counterSlotInput) {
        this.counterSlot = counterSlotInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Boolean getIsPrimaryAttackSlot() {
        return isPrimaryAttackSlot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setIsPrimaryAttackSlot(
            final Boolean isPrimaryAttackSlotInput) {
        this.isPrimaryAttackSlot = isPrimaryAttackSlotInput;
    }

}
