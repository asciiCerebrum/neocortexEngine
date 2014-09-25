package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;
import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;

/**
 *
 * @author species8472
 */
public class WeaponCategory implements IWeaponCategory {

    /**
     * Unique id of the weapon category.
     */
    private String id;

    /**
     * Either melee or ranged dice action can be associated with this category.
     */
    private BonusTarget associatedAttackDiceAction;

    /**
     * Either melee or ranged observer hook associated with this category.
     */
    private ObserverHook associatedDamageHook;

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
    public final BonusTarget getAssociatedAttackDiceAction() {
        return associatedAttackDiceAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setAssociatedAttackDiceAction(
            final BonusTarget associatedAttackDiceActionInput) {
        this.associatedAttackDiceAction = associatedAttackDiceActionInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ObserverHook getAssociatedDamageHook() {
        return associatedDamageHook;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setAssociatedDamageHook(
            final ObserverHook associatedDamageHookInput) {
        this.associatedDamageHook = associatedDamageHookInput;
    }

}
