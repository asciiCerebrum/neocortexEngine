package org.asciicerebrum.mydndgame.domain.setup;

import java.util.List;

/**
 *
 * @author species8472
 */
public interface EntitySetup {

    /**
     * Generic getter of the given property.
     *
     * @param setupProperty the kind of property to get.
     * @return the property as a string representation.
     */
    String getProperty(SetupProperty setupProperty);

    /**
     * Generic getter of a list-like given property.
     *
     * @param setupProperty the kind of property to get.
     * @return the property as a string-list representation.
     */
    List<String> getProperties(SetupProperty setupProperty);

    /**
     * Generic getter of a setup-like given property.
     *
     * @param setupProperty the kind of property to get.
     * @return the property in form of an entity setup.
     */
    EntitySetup getPropertySetup(SetupProperty setupProperty);

    /**
     * Generic getter of a setup-list-like given property.
     *
     * @param setupProperty the kind of property to get.
     * @return the property in form of an entity setup list.
     */
    List<EntitySetup> getPropertySetups(SetupProperty setupProperty);

    /**
     * Generic setter for a given property.
     *
     * @param setupProperty the property to set.
     * @param value the value of that property.
     */
    void setProperty(SetupProperty setupProperty, String value);

    /**
     * Tests if setup is complete.
     *
     * @return true if all required properties were set, false otherwise.
     */
    boolean isSetupComplete();
}
