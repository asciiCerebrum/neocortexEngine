package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public final class CombatRoundNumber extends LongParticle
        implements Comparable {

    public CombatRoundNumber(final String stringInput) {
        this.setValue(stringInput);
    }

    public CombatRoundNumber(final long longInput) {
        this.setValue(longInput);
    }

    public void setValue(final String stringInput) {
        super.setValue(Long.valueOf(stringInput));
    }

    @Override
    public int compareTo(Object object) {
        final CombatRoundNumber crn = (CombatRoundNumber) object;
        return Long.valueOf(this.getValue())
                .compareTo(crn.getValue());
    }

}
