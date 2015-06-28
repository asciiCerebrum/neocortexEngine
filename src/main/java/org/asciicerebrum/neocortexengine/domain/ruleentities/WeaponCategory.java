package org.asciicerebrum.neocortexengine.domain.ruleentities;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusTarget;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public class WeaponCategory extends Feature {

    /**
     * Either melee or ranged attack dice action can be associated with this
     * category.
     */
    private BonusTarget associatedAttackDiceAction;

    /**
     * Either melee or ranged damage dice action can be associated with this
     * category.
     */
    private BonusTarget associatedDamageDiceAction;

    /**
     * Either melee or ranged observer damage hook associated with this
     * category.
     */
    private ObserverHook associatedDamageHook;

    /**
     * Either melee or ranged observer attack hook associated with this
     * category.
     */
    private ObserverHook associatedAttackHook;

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
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        return this.getFeatureBoni(context);
    }

    /**
     * @return the associatedAttackHook
     */
    public final ObserverHook getAssociatedAttackHook() {
        return associatedAttackHook;
    }

    /**
     * @param associatedAttackHookInput the associatedAttackHook to set
     */
    public final void setAssociatedAttackHook(
            final ObserverHook associatedAttackHookInput) {
        this.associatedAttackHook = associatedAttackHookInput;
    }

    /**
     * @return the associatedDamageDiceAction
     */
    public final BonusTarget getAssociatedDamageDiceAction() {
        return associatedDamageDiceAction;
    }

    /**
     * @param associatedDamageDiceActionInput the associatedDamageDiceAction to
     * set
     */
    public final void setAssociatedDamageDiceAction(
            final BonusTarget associatedDamageDiceActionInput) {
        this.associatedDamageDiceAction = associatedDamageDiceActionInput;
    }

}
