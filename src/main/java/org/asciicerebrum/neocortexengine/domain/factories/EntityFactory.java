package org.asciicerebrum.neocortexengine.domain.factories;

import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;

/**
 *
 * @author species8472
 * @param <T> the type of the entity to build.
 */
public interface EntityFactory<T> {

    /**
     * The actual building method. Creates the domain instance based on the
     * setup.
     *
     * @param setup the setup of the specific object to create.
     * @return the created instance.
     */
    T newEntity(EntitySetup setup);

}
