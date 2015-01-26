package org.asciicerebrum.mydndgame.domain.core.attribution;

import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;

/**
 *
 * @author species8472
 */
public class SpecialAbility extends Feature {

    /**
     * For example a magic weapon is always a masterwork. So the masterwork is
     * the subAbility of the magic feature.
     */
    private SpecialAbilities subAbilities;

    /**
     * @return the subAbilities
     */
    public final SpecialAbilities getSubAbilities() {
        return subAbilities;
    }

    /**
     * @param subAbilitiesInput the subAbilities to set
     */
    public final void setSubAbilities(
            final SpecialAbilities subAbilitiesInput) {
        this.subAbilities = subAbilitiesInput;
    }

    @Override
    public final ObserverSources getObserverSources() {
        final ObserverSources observerSources = super.getObserverSources();

        observerSources.add(this.subAbilities);

        return observerSources;
    }

    @Override
    public BonusSources getBonusSources() {
        final BonusSources bonusSources = super.getBonusSources();

        bonusSources.add(this.subAbilities);

        return bonusSources;
    }

}
