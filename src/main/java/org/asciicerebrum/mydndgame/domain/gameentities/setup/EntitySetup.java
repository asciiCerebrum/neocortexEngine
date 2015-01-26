package org.asciicerebrum.mydndgame.domain.gameentities.setup;

import java.util.List;

/**
 *
 * @author species8472
 * @param <T> Type of the setup to create.
 */
public interface EntitySetup<T> {

    String getProperty(SetupProperty setupProperty);

    List<String> getProperties(SetupProperty setupProperty);

    EntitySetup getPropertySetup(SetupProperty setupProperty);

    List<EntitySetup> getPropertySetups(SetupProperty setupProperty);

    void setProperty(SetupProperty setupProperty, String value);

    boolean isSetupComplete();
}
