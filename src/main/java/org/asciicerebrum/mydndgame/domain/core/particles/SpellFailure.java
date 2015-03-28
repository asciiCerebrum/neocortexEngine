package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class SpellFailure extends Percentage {

    /**
     * Basic constructor for the spell failure.
     *
     * @param spellFailureInput the numerical value of the spell failure.
     */
    public SpellFailure(final long spellFailureInput) {
        this.setValue(spellFailureInput);
    }

}
