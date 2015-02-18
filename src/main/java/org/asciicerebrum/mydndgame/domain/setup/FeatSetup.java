package org.asciicerebrum.mydndgame.domain.setup;

/**
 *
 * @author species8472
 */
public class FeatSetup extends AbstractEntitySetup {

    /**
     * The required properties.
     */
    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.FEAT_TYPE};

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    /**
     * @param featTypeId the feat type.
     */
    public final void setFeatType(final String featTypeId) {
        this.getSingleProperties().put(SetupProperty.FEAT_TYPE, featTypeId);
    }

    /**
     * @param featBindingId the feat binding id.
     */
    public final void setFeatBinding(final String featBindingId) {
        this.getSingleProperties().put(SetupProperty.FEAT_BINDING,
                featBindingId);
    }

}
