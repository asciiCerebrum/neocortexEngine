package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface IWeaponCategory extends Identifiable {

    /**
     * @return the associated attack DiceAction
     */
    BonusTarget getAssociatedAttackDiceAction();

    /**
     * @param associatedAttackDiceAction the associated attack DiceAction to set
     */
    void setAssociatedAttackDiceAction(BonusTarget associatedAttackDiceAction);

    /**
     * @return the associatedDamageHook
     */
    ObserverHook getAssociatedDamageHook();

    /**
     * @param associatedDamageHook the associatedDamageHook to set
     */
    void setAssociatedDamageHook(ObserverHook associatedDamageHook);

}
