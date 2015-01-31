package org.asciicerebrum.mydndgame.domain.rules.composition;

import org.asciicerebrum.mydndgame.domain.rules.composition.BodySlotType;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;
import org.asciicerebrum.mydndgame.domain.game.entities.UniqueEntity;

/**
 *
 * @author species8472
 */
public class BodySlot implements BonusSource, ObserverSource {

    public enum Facet {

        BODY_SLOT_TYPE {

                    @Override
                    final boolean isSimilar(final BodySlot bluePrint,
                            final BodySlot candidate) {
                        return bluePrint.getBodySlotType()
                        .equals(candidate.getBodySlotType());
                    }
                },
        ITEM {
                    @Override
                    final boolean isSimilar(final BodySlot bluePrint,
                            final BodySlot candidate) {
                        // both null or both equal
                        return bluePrint.getItem() == null
                        && candidate.getItem() == null
                        || bluePrint.getItem().equals(candidate.getItem());
                    }
                },
        PRIMARY_ATTACK_SLOT {

                    @Override
                    final boolean isSimilar(final BodySlot bluePrint,
                            final BodySlot candidate) {
                        return bluePrint.getIsPrimaryAttackSlot()
                        .equals(candidate.getIsPrimaryAttackSlot());
                    }
                };

        abstract boolean isSimilar(BodySlot bluePrint, BodySlot candidate);
    }

    /**
     * The type of the body slot.
     */
    private BodySlotType bodySlotType;
    /**
     * What item is in the body slot.
     */
    private UniqueEntity item;
    /**
     * The character this slot is associated with.
     */
    private UniqueEntity holder;
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
     *
     * @return the item stored in this slot.
     */
    public final UniqueEntity getItem() {
        return item;
    }

    /**
     * Store the item in the slot.
     *
     * @param itemInput the inventory item in question.
     */
    public final void setItem(final UniqueEntity itemInput) {
        this.item = itemInput;
    }

    /**
     * @return the holder
     */
    public final UniqueEntity getHolder() {
        return holder;
    }

    /**
     * @param holderInput the holder to set
     */
    public final void setHolder(final UniqueEntity holderInput) {
        this.holder = holderInput;
    }

    public final boolean isOfType(final BodySlotType bodySlotTypeInput) {
        return this.bodySlotType == bodySlotTypeInput;
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        BonusSources bonusSources = new BonusSources();

        bonusSources.add(this.item);

        return bonusSources;
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

    public final BodySlot cloneSlot() {
        final BodySlot clonedSlot = new BodySlot();

        clonedSlot.setBodySlotType(this.bodySlotType);
        clonedSlot.setIsPrimaryAttackSlot(this.isPrimaryAttackSlot.getClone());

        return clonedSlot;
    }

    public final boolean isSimilar(final BodySlot bluePrint,
            final BodySlot.Facet... facets) {
        for (BodySlot.Facet facet : facets) {
            if (!facet.isSimilar(bluePrint, this)) {
                return false;
            }
        }
        return true;
    }

    public final boolean containsItem(final UniqueEntity itemInput) {
        if (this.item == null) {
            return itemInput == null;
        }
        return this.item.equals(itemInput);
    }

}
