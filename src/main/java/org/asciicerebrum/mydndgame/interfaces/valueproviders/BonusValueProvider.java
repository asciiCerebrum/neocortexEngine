package org.asciicerebrum.mydndgame.interfaces.valueproviders;

/**
 *
 * @author species8472
 */
public interface BonusValueProvider {

    /**
     *
     * @param context the context of the bonus value calculation in form of a
     * dndCharacter.
     * @return the result of the dynamic bonus value calculation.
     */
    Long getDynamicValue(BonusValueContext context);
}
