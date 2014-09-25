package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface BonusValueProvider {

    /**
     *
     * @param character the contextual Character.
     * @return the result of the dynamic bonus value calculation.
     */
    Long getDynamicValue(ICharacter character);
}
