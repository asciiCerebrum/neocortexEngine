package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public interface BonusValueProvider {

    /**
     *
     * @param dndCharacter the context of the bonus value calculation in form of
     * a dndCharacter.
     * @return the result of the dynamic bonus value calculation.
     */
    Long getDynamicValue(DndCharacter dndCharacter);
}
