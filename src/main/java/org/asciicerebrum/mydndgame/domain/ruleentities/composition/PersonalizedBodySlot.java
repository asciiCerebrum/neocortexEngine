package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotType;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlot implements BonusSource, ObserverSource {

    /**
     * Facets are characteristics of personalized body slots that are used to
     * compare similarity between two slots in that aspect.
     */
    public enum Facet {

        /**
         * The aspect of the same body slot type.
         */
        BODY_SLOT_TYPE {

                    @Override
                    final boolean isSimilar(
                            final PersonalizedBodySlot bluePrint,
                            final PersonalizedBodySlot candidate) {
                                return bluePrint.getBodySlot().getBodySlotType()
                                .equals(candidate.getBodySlot()
                                        .getBodySlotType());
                            }
                },
        /**
         * The aspect of the same item that is contained (or no item at all).
         */
        ITEM {
                    @Override
                    final boolean isSimilar(
                            final PersonalizedBodySlot bluePrint,
                            final PersonalizedBodySlot candidate) {
                                // both null or both equal
                                if (bluePrint.getItem() == null) {
                                    return candidate.getItem() == null;
                                }
                                return bluePrint.getItem()
                                .equals(candidate.getItem());
                            }
                },
        /**
         * The aspect of being the primary attack slot.
         */
        PRIMARY_ATTACK_SLOT {

                    @Override
                    final boolean isSimilar(
                            final PersonalizedBodySlot bluePrint,
                            final PersonalizedBodySlot candidate) {
                                return bluePrint.getBodySlot()
                                .getIsPrimaryAttackSlot()
                                .equals(candidate.getBodySlot()
                                        .getIsPrimaryAttackSlot());
                            }
                };

        /**
         * Tests similarity between two slots in the aspect it is invoked upon.
         *
         * @param bluePrint the first personalized body slot.
         * @param candidate the second personalized body slot.
         * @return true if similar in that aspect, false otherwise.
         */
        abstract boolean isSimilar(PersonalizedBodySlot bluePrint,
                PersonalizedBodySlot candidate);
    }

    /**
     * The body slot this instance is based upon.
     */
    private BodySlot bodySlot;
    /**
     * What item is in the body slot.
     */
    private UniqueEntity item;
    /**
     * The character this slot is associated with.
     */
    private UniqueEntity holder;

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        BonusSources bonusSources = new BonusSources();

        if (this.item instanceof BonusSource) {
            bonusSources.add((BonusSource) this.item);
        }

        return bonusSources;
    }

    /**
     * Tests if the slot contains the given unique entity item.
     *
     * @param itemInput the item to test.
     * @return true if item was found, false otherwise.
     */
    public final boolean containsItem(final UniqueEntity itemInput) {
        if (this.item == null) {
            return itemInput == null;
        }
        return this.item.equals(itemInput);
    }

    /**
     * Tests if this instance is similar to the given one in respect of all the
     * facets given.
     *
     * @param bluePrint the slot to compare similarity with.
     * @param facets all those facets are AND connected.
     * @return true if all facets show similarity, false otherwise.
     */
    public final boolean isSimilar(final PersonalizedBodySlot bluePrint,
            final PersonalizedBodySlot.Facet... facets) {
        for (PersonalizedBodySlot.Facet facet : facets) {
            if (!facet.isSimilar(bluePrint, this)) {
                return false;
            }
        }
        return true;
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

    /**
     * @return the bodySlot
     */
    public final BodySlot getBodySlot() {
        return bodySlot;
    }

    /**
     * @param bodySlotInput the bodySlot to set
     */
    public final void setBodySlot(final BodySlot bodySlotInput) {
        this.bodySlot = bodySlotInput;
    }

    /**
     * @return the body slot type.
     */
    public final BodySlotType getBodySlotType() {
        if (this.bodySlot == null) {
            return null;
        }
        return this.bodySlot.getBodySlotType();
    }

    /**
     * @return the primary attack slot characteristic.
     */
    public final AttackAbility getIsPrimaryAttackSlot() {
        if (this.bodySlot == null) {
            return null;
        }
        return this.bodySlot.getIsPrimaryAttackSlot();
    }

    /**
     * @return the counter body slot.
     */
    public final BodySlot getCounterBodySlot() {
        if (this.bodySlot == null) {
            return null;
        }
        return this.bodySlot.getCounterSlot();
    }
}
