package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
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
                                if (bluePrint.getItemId() == null) {
                                    return candidate.getItemId() == null;
                                }
                                return bluePrint.getItemId()
                                .equals(candidate.getItemId());
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
    private UniqueId itemId;

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources(
            final UniqueEntityResolver resolver) {
        BonusSources bonusSources = new BonusSources();

        if (resolver.resolve(this.itemId) instanceof BonusSource) {
            bonusSources.add((BonusSource) resolver.resolve(this.itemId));
        }

        return bonusSources;
    }

    /**
     * Tests if the slot contains the given unique entity item.
     *
     * @param itemIdInput the item to test.
     * @return true if item was found, false otherwise.
     */
    public final boolean containsItem(final UniqueId itemIdInput) {
        if (this.itemId == null) {
            return itemIdInput == null;
        }
        return this.itemId.equals(itemIdInput);
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
    public final UniqueId getItemId() {
        return itemId;
    }

    /**
     * Store the item in the slot.
     *
     * @param itemIdInput the inventory item in question.
     */
    public final void setItemId(final UniqueId itemIdInput) {
        this.itemId = itemIdInput;
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
