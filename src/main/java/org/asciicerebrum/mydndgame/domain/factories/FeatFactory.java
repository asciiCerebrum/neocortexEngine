package org.asciicerebrum.mydndgame.domain.factories;

import org.apache.commons.lang3.StringUtils;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatBinding;
import org.asciicerebrum.mydndgame.domain.ruleentities.Feat;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatType;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class FeatFactory implements EntityFactory<Feat> {

    @Override
    public final Feat newEntity(final EntitySetup setup) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the feat "
                    + " is not complete.");
        }

        final String featTypeId = setup.getProperty(
                SetupProperty.FEAT_TYPE);
        final String featBindingId = setup.getProperty(
                SetupProperty.FEAT_BINDING);

        // if the binding id is not given, then we can assume we want the whole
        // feat from the context.
        if (StringUtils.isBlank(featBindingId)) {
            return ApplicationContextProvider.getApplicationContext()
                    .getBean(featTypeId, Feat.class);
        }

        final Feat feat = new Feat();

        final FeatType featType = ApplicationContextProvider
                .getApplicationContext().getBean(featTypeId,
                        FeatType.class);
        feat.setFeatType(featType);

        final FeatBinding featBinding = ApplicationContextProvider
                .getApplicationContext().getBean(
                        featBindingId, FeatBinding.class);
        feat.setFeatBinding(featBinding);

        return feat;
    }

}
