package org.asciicerebrum.mydndgame.domain.rules.composition;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;
import org.asciicerebrum.mydndgame.domain.rules.BodySlot;
import org.asciicerebrum.mydndgame.domain.rules.BodySlotType;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlot implements BonusSource, ObserverSource {

    public enum Facet {

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
        ITEM {
                    @Override
                    final boolean isSimilar(
                            final PersonalizedBodySlot bluePrint,
                            final PersonalizedBodySlot candidate) {
                                // both null or both equal
                                return bluePrint.getItem() == null
                                && candidate.getItem() == null
                                || bluePrint.getItem()
                                .equals(candidate.getItem());
                            }
                },
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

        abstract boolean isSimilar(PersonalizedBodySlot bluePrint,
                PersonalizedBodySlot candidate);
    }

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

    public final boolean containsItem(final UniqueEntity itemInput) {
        if (this.item == null) {
            return itemInput == null;
        }
        return this.item.equals(itemInput);
    }

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

    public final BodySlotType getBodySlotType() {
        return this.bodySlot.getBodySlotType();
    }

    public final AttackAbility getIsPrimaryAttackSlot() {
        return this.bodySlot.getIsPrimaryAttackSlot();
    }

    public final BodySlot getCounterBodySlot() {
        return this.bodySlot.getCounterSlot();
    }
}
