package org.asciicerebrum.mydndgame.domain.mechanics.bonus.source;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

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
