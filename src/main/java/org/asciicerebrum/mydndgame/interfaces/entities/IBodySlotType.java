package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author Tabea Raab
 */
public interface IBodySlotType extends Identifiable {

    /**
     * @return the counterSlot
     */
    IBodySlotType getCounterSlot();

    /**
     * @param counterSlot the counterSlot to set
     */
    void setCounterSlot(IBodySlotType counterSlot);

    /**
     * @return the isPrimaryAttackSlot
     */
    Boolean getIsPrimaryAttackSlot();

    /**
     * @param isPrimaryAttackSlot the isPrimaryAttackSlot to set
     */
    void setIsPrimaryAttackSlot(Boolean isPrimaryAttackSlot);

}
