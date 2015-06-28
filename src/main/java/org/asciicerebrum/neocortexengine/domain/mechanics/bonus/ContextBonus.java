package org.asciicerebrum.neocortexengine.domain.mechanics.bonus;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;

/**
 * Keeps track of the origin of the bonus by binding the bonus together with the
 * bonus granting instance into one object instance.
 *
 * @author species8472
 */
public class ContextBonus {

    /**
     * The bonus granted.
     */
    private final Bonus bonus;

    /**
     * The granting instance of the bonus.
     */
    private final UniqueEntity context;

    /**
     * Constructing the compound object.
     *
     * @param bonusInput the bonus granted.
     * @param contextInput the granting bonus source.
     */
    public ContextBonus(final Bonus bonusInput,
            final UniqueEntity contextInput) {
        this.bonus = bonusInput;
        this.context = contextInput;
    }

    /**
     * @return the bonus
     */
    public final Bonus getBonus() {
        return bonus;
    }

    /**
     * @return the context
     */
    public final UniqueEntity getContext() {
        return context;
    }

}
