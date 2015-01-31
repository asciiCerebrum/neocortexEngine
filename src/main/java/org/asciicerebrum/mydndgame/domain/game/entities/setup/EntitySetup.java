package org.asciicerebrum.mydndgame.domain.game.entities.setup;

import java.util.List;

/**
 *
 * @author species8472
 */
public interface EntitySetup {

    String getProperty(SetupProperty setupProperty);

    List<String> getProperties(SetupProperty setupProperty);

    EntitySetup getPropertySetup(SetupProperty setupProperty);

    List<EntitySetup> getPropertySetups(SetupProperty setupProperty);

    void setProperty(SetupProperty setupProperty, String value);

    boolean isSetupComplete();
}
