package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;

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
    public final BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        bonusSources.add(this.subAbilities);

        return bonusSources;
    }

}
