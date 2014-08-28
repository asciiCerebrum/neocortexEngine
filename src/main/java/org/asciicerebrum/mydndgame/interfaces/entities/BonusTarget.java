package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface BonusTarget extends Identifiable {

    /**
     * @return the associatedAttackMode
     */
    IWeaponCategory getAssociatedAttackMode();

    /**
     * @param associatedAttackMode the associatedAttackMode to set
     */
    void setAssociatedAttackMode(IWeaponCategory associatedAttackMode);

}
