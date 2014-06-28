package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public interface BonusValueProvider {
    
    Long getDynamicValue(DndCharacter dndCharacter);
}
