package org.asciicerebrum.mydndgame.domain.gameentities.setup;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author species8472
 * @param <T> Type of the setup to create.
 */
public abstract class AbstractEntitySetup<T> implements EntitySetup<T> {

    protected final Map<SetupProperty, String> singleProperties
            = new EnumMap<SetupProperty, String>(SetupProperty.class);

    protected final Map<SetupProperty, List<String>> listProperties
            = new EnumMap<SetupProperty, List<String>>(SetupProperty.class);

    protected final Map<SetupProperty, EntitySetup> singleSetup
            = new EnumMap<SetupProperty, EntitySetup>(SetupProperty.class);

    protected final Map<SetupProperty, List<EntitySetup>> listSetup
            = new EnumMap<SetupProperty, List<EntitySetup>>(
                    SetupProperty.class);

    @Override
    public final String getProperty(final SetupProperty setupProperty) {
        return this.singleProperties.get(setupProperty);
    }

    @Override
    public final List<String> getProperties(final SetupProperty setupProperty) {
        return this.listProperties.get(setupProperty);
    }

    @Override
    public final EntitySetup getPropertySetup(
            final SetupProperty setupProperty) {
        return this.singleSetup.get(setupProperty);
    }

    @Override
    public final List<EntitySetup> getPropertySetups(
            final SetupProperty setupProperty) {
        return this.listSetup.get(setupProperty);
    }

    @Override
    public void setProperty(final SetupProperty setupProperty,
            final String value) {
        this.singleProperties.put(setupProperty, value);
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final String idInput) {
        this.singleProperties.put(SetupProperty.UNIQUEID, idInput);
    }

    protected boolean checkRequiredSingleProperties(
            final SetupProperty[] requiredProps) {
        for (SetupProperty requiredProperty : requiredProps) {
            if (StringUtils.isBlank(this.getProperty(requiredProperty))) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkRequiredListProperties(
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

    protected boolean checkRequiredSingleSetup(
            final SetupProperty[] requiredProps) {
        for (SetupProperty requiredProperty : requiredProps) {
            EntitySetup setupProp = this.getPropertySetup(requiredProperty);
            if (setupProp == null || !setupProp.isSetupComplete()) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkRequiredListSetup(
            final SetupProperty[] requiredProps) {
        for (SetupProperty requiredProperty : requiredProps) {
            List<EntitySetup> listSetupProp
                    = this.getPropertySetups(requiredProperty);
            if (listSetupProp == null) {
                return false;
            }
            for (EntitySetup setupProp : listSetupProp) {
                if (!setupProp.isSetupComplete()) {
                    return false;
                }
            }
        }
        return true;
    }

}
