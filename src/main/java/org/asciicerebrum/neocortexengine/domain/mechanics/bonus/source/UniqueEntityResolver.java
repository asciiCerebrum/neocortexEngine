package org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public interface UniqueEntityResolver {

    /**
     * Retrieves the unique entity instance for its unique id.
     *
     * @param uid the id of the entity.
     * @return the entity with this id.
     */
    UniqueEntity resolve(UniqueId uid);
}
