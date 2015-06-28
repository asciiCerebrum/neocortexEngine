package org.asciicerebrum.neocortexengine.services.context;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntities;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueIds;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public interface EntityPoolService extends UniqueEntityResolver {

    /**
     * Puts a new unique entity into the campaign.
     *
     * @param uniqueEntity the unique entity in question.
     */
    void registerUniqueEntity(UniqueEntity uniqueEntity);

    /**
     * Retrieves the entity back from the map by its unique id.
     *
     * @param uniqueId the id to identify the object needed.
     * @return the object instance with that id.
     */
    UniqueEntity getEntityById(UniqueId uniqueId);

    /**
     * Retrieves a collection of entities back from the map by the collection of
     * unique ids.
     *
     * @param uniqueIds the ids to identify the object needed.
     * @return the object instance with that id.
     */
    UniqueEntities getEntitiesByIds(UniqueIds uniqueIds);

    /**
     * Deletes all entries from the internal map.
     */
    void empty();
}
