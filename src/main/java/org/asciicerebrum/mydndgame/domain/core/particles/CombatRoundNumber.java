package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public final class CombatRoundNumber extends LongParticle {

    public CombatRoundNumber(final String stringInput) {
        this.setValue(stringInput);
    }

    public CombatRoundNumber(final long longInput) {
        this.setValue(longInput);
    }

    public void setValue(final String stringInput) {
        super.setValue(Long.valueOf(stringInput));
    }

    public int compareTo(CombatRoundNumber crn) {
        return Long.valueOf(this.getValue())
                .compareTo(crn.getValue());
    }

}
