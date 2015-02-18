package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;

/**
 *
 * @author species8472
 */
public class WeaponCategory extends Feature {

    /**
     * Either melee or ranged dice action can be associated with this category.
     */
    private BonusTarget associatedAttackDiceAction;

    /**
     * Either melee or ranged observer hook associated with this category.
     */
    private ObserverHook associatedDamageHook;

    /**
     * @return the associated attack DiceAction
     */
    public final BonusTarget getAssociatedAttackDiceAction() {
        return associatedAttackDiceAction;
    }

    /**
     * @param associatedAttackDiceActionInput the associated attack dice action.
     */
    public final void setAssociatedAttackDiceAction(
            final BonusTarget associatedAttackDiceActionInput) {
        this.associatedAttackDiceAction = associatedAttackDiceActionInput;
    }

    /**
     * @return the associatedDamageHook
     */
    public final ObserverHook getAssociatedDamageHook() {
        return associatedDamageHook;
    }

    /**
     * @param associatedDamageHookInput the associatedDamageHook to set
     */
    public final void setAssociatedDamageHook(
            final ObserverHook associatedDamageHookInput) {
        this.associatedDamageHook = associatedDamageHookInput;
    }

    @Override
    public final BonusSources getBonusSources() {
        return BonusSources.EMPTY_BONUSSOURCES;
    }

}
