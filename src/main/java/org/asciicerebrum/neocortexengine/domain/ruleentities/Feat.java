package org.asciicerebrum.neocortexengine.domain.ruleentities;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public class Feat extends Feature {

    /**
     * The feat itself.
     */
    private FeatType featType;

    /**
     * The object this feat is bound to. Only needed for special feats which are
     * restricted to be bound to one type of object only. E.g. Weapon Focus,
     * which is bound to one type of weapon.
     */
    private FeatBinding featBinding;

    /**
     * @return the featType
     */
    public final FeatType getFeatType() {
        return featType;
    }

    /**
     * @param featTypeInput the featType to set
     */
    public final void setFeatType(final FeatType featTypeInput) {
        this.featType = featTypeInput;
    }

    /**
     * @return the featBinding
     */
    public final FeatBinding getFeatBinding() {
        return featBinding;
    }

    /**
     * @param featBindingInput the featBinding to set
     */
    public final void setFeatBinding(final FeatBinding featBindingInput) {
        this.featBinding = featBindingInput;
    }

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        return this.getFeatureBoni(context);
    }

}
