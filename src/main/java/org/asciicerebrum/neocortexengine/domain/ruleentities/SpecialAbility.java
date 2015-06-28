package org.asciicerebrum.neocortexengine.domain.ruleentities;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public class SpecialAbility extends Feature {

    /**
     * For example a magic weapon is always a masterwork. So the masterwork is
     * the subAbility of the magic feature.
     */
    private SpecialAbilities subAbilities = new SpecialAbilities();

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

        ctxBoni.add(this.getSubAbilities().getBoni(context, resolver));

        return ctxBoni;
    }

}
