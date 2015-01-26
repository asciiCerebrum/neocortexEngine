package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class ExperiencePoints extends LongParticle {

    public ExperiencePoints(final String xpString) {
        this.setValue(xpString);
    }

    public final void setValue(final String xpString) {
        super.setValue(Long.parseLong(xpString));
    }

}
