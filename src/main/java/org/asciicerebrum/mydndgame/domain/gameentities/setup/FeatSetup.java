package org.asciicerebrum.mydndgame.domain.gameentities.setup;

import org.asciicerebrum.mydndgame.domain.gameentities.Feat;

/**
 *
 * @author species8472
 */
public class FeatSetup extends AbstractEntitySetup<Feat> {

    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.FEAT_TYPE};

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    public final void setFeatType(final String featTypeId) {
        this.singleProperties.put(SetupProperty.FEAT_TYPE, featTypeId);
    }

    public final void setFeatBinding(final String featBindingId) {
        this.singleProperties.put(SetupProperty.FEAT_BINDING, featBindingId);
    }

}
