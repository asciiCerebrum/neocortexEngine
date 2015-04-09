package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;

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
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        ContextBoni ctxBoni = this.getFeatureBoni(context);

        if (this.subAbilities != null) {
            ctxBoni.add(this.subAbilities.getBoni(context, resolver));
        }

        return ctxBoni;
    }

}
