package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;

/**
 *
 * @author species8472
 */
public class BodySlot {

    /**
     * The type of the body slot. E.g. primary hand, secondary hand, torso,
     * claw, tail, etc.
     */
    private BodySlotType bodySlotType;
    /**
     * The opposite slot for easy navigation between both. E.g. primary and
     * secondary hand. This is set in the blue print of the race and nothing the
     * player can change.
     */
    private BodySlot counterSlot;
    /**
     * Defines if this slot is the character type's primary slot of attack. This
     * is set in the blue print of the race and nothing the player can change.
     */
    private AttackAbility isPrimaryAttackSlot = new AttackAbility(false);

    /**
     *
     * @return the type of this (body) slot.
     */
    public final BodySlotType getBodySlotType() {
        return bodySlotType;
    }

    /**
     * @param bodySlotTypeInput the bodySlotType to set
     */
    public final void setBodySlotType(final BodySlotType bodySlotTypeInput) {
        this.bodySlotType = bodySlotTypeInput;
    }

    /**
     * Tests if the type of this instance is the same as the one given.
     *
     * @param bodySlotTypeInput the body slot type in question.
     * @return true if both are equal, false otherwise.
     */
    public final boolean isOfType(final BodySlotType bodySlotTypeInput) {
        return this.bodySlotType == bodySlotTypeInput;
    }

    /**
     * @return the counterSlot
     */
    public final BodySlot getCounterSlot() {
        return counterSlot;
    }

    /**
     * @param counterSlotInput the counterSlot to set
     */
    public final void setCounterSlot(final BodySlot counterSlotInput) {
        this.counterSlot = counterSlotInput;
    }

    /**
     * @return the isPrimaryAttackSlot
     */
    public final AttackAbility getIsPrimaryAttackSlot() {
        return isPrimaryAttackSlot;
    }

    /**
     * @param isPrimaryAttackSlotInput the isPrimaryAttackSlot to set
     */
    public final void setIsPrimaryAttackSlot(
            final AttackAbility isPrimaryAttackSlotInput) {
        this.isPrimaryAttackSlot = isPrimaryAttackSlotInput;
    }

    /**
     * Returns a cloned body slot of this instance.
     *
     * @return the clone.
     */
    public final BodySlot cloneSlot() {
        final BodySlot clonedSlot = new BodySlot();

        clonedSlot.setBodySlotType(this.bodySlotType);
        clonedSlot.setIsPrimaryAttackSlot(this.isPrimaryAttackSlot.getClone());

        return clonedSlot;
    }

}
