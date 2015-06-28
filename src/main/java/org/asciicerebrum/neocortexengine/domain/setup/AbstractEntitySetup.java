package org.asciicerebrum.neocortexengine.domain.setup;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author species8472
 */
public abstract class AbstractEntitySetup implements EntitySetup {

    /**
     * The map holding the specific properties.
     */
    private final Map<SetupProperty, String> singleProperties
            = new EnumMap<SetupProperty, String>(SetupProperty.class);

    /**
     * The map holding the list-like properties.
     */
    private final Map<SetupProperty, List<String>> listProperties
            = new EnumMap<SetupProperty, List<String>>(SetupProperty.class);

    /**
     * The map holding the specific setups.
     */
    private final Map<SetupProperty, EntitySetup> singleSetup
            = new EnumMap<SetupProperty, EntitySetup>(SetupProperty.class);

    /**
     * The map holding list-like setups.
     */
    private final Map<SetupProperty, List<EntitySetup>> listSetup
            = new EnumMap<SetupProperty, List<EntitySetup>>(
                    SetupProperty.class);

    @Override
    public final String getProperty(final SetupProperty setupProperty) {
        return this.getSingleProperties().get(setupProperty);
    }

    @Override
    public final List<String> getProperties(final SetupProperty setupProperty) {
        return this.getListProperties().get(setupProperty);
    }

    @Override
    public final EntitySetup getPropertySetup(
            final SetupProperty setupProperty) {
        return this.getSingleSetup().get(setupProperty);
    }

    @Override
    public final List<EntitySetup> getPropertySetups(
            final SetupProperty setupProperty) {
        return this.getListSetup().get(setupProperty);
    }

    @Override
    public final void setProperty(final SetupProperty setupProperty,
            final String value) {
        this.getSingleProperties().put(setupProperty, value);
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final String idInput) {
        this.getSingleProperties().put(SetupProperty.UNIQUEID, idInput);
    }

    /**
     * Tests if all required single properties were set.
     *
     * @param requiredProps the list of required properties.
     * @return true if the conditions are met, false otherwise.
     */
    protected final boolean checkRequiredSingleProperties(
            final SetupProperty[] requiredProps) {
        for (SetupProperty requiredProperty : requiredProps) {
            if (StringUtils.isBlank(this.getProperty(requiredProperty))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tests if all required list-like properties were set.
     *
     * @param requiredProps the list of required properties.
     * @return true if the conditions are met, false otherwise.
     */
    protected final boolean checkRequiredListProperties(
            final SetupProperty[] requiredProps) {
        for (SetupProperty requiredProperty : requiredProps) {
            List<String> listProp = this.getProperties(requiredProperty);
            if (listProp == null) {
                return false;
            }
            if (listProp.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tests if all required setups were set.
     *
     * @param requiredProps the list of required setup properties.
     * @return true if the conditions are met, false otherwise.
     */
    protected final boolean checkRequiredSingleSetup(
            final SetupProperty[] requiredProps) {
        for (SetupProperty requiredProperty : requiredProps) {
            EntitySetup setupProp = this.getPropertySetup(requiredProperty);
            if (setupProp == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tests if all required list-like setups were set.
     *
     * @param requiredProps the list of required setup properties.
     * @return true if the conditions are met, false otherwise.
     */
    protected final boolean checkRequiredListSetup(
            final SetupProperty[] requiredProps) {
        for (SetupProperty requiredProperty : requiredProps) {
            List<EntitySetup> listSetupProp
                    = this.getPropertySetups(requiredProperty);
            if (listSetupProp == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the singleProperties
     */
    protected final Map<SetupProperty, String> getSingleProperties() {
        return singleProperties;
    }

    /**
     * @return the listProperties
     */
    protected final Map<SetupProperty, List<String>> getListProperties() {
        return listProperties;
    }

    /**
     * @return the singleSetup
     */
    protected final Map<SetupProperty, EntitySetup> getSingleSetup() {
        return singleSetup;
    }

    /**
     * @return the listSetup
     */
    protected final Map<SetupProperty, List<EntitySetup>> getListSetup() {
        return listSetup;
    }

}
