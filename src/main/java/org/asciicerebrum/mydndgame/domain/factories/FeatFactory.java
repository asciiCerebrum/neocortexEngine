package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.rules.FeatBinding;
import org.asciicerebrum.mydndgame.domain.rules.Feat;
import org.asciicerebrum.mydndgame.domain.rules.FeatType;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class FeatFactory implements EntityFactory<Feat> {

    /**
     * Spring context to get the beans from.
     */
    private ApplicationContext context;

    @Override
    public final Feat newEntity(final EntitySetup setup,
            final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the feat "
                    + " is not complete.");
        }

        final Feat feat = new Feat();

        final String featTypeId = setup.getProperty(
                SetupProperty.FEAT_TYPE);
        final String featBindingId = setup.getProperty(
                SetupProperty.FEAT_BINDING);

        final FeatType featType = this.context.getBean(featTypeId,
                FeatType.class);
        final FeatBinding featBinding = this.context.getBean(featBindingId,
                FeatBinding.class);

        feat.setFeatType(featType);
        feat.setFeatBinding(featBinding);

        return feat;
    }

    @Override
    public final void reAssign(final EntitySetup setup, final Feat entity) {
        // nothing to do here
    }

    /**
     * @param contextInput the context to set
     */
    public final void setContext(final ApplicationContext contextInput) {
        this.context = contextInput;
    }

}
