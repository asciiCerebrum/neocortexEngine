package org.asciicerebrum.mydndgame.services.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntities;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueIds;

/**
 *
 * @author species8472
 */
public class MapBasedEntityPoolService implements EntityPoolService {

    /**
     * Just a dummy class for unique entities.
     */
    private static class DefaultUniqueEntities extends UniqueEntities {
        // nothing goes here as everything is provided by the parent class.
    }

    /**
     * The map of entities registered. The instances are mapped to their unique
     * ids.
     */
    private final Map<UniqueId, UniqueEntity> entities
            = new HashMap<UniqueId, UniqueEntity>();

    @Override
    public final void registerUniqueEntity(final UniqueEntity uniqueEntity) {
        this.entities.put(uniqueEntity.getUniqueId(), uniqueEntity);
    }

    @Override
    public final UniqueEntity getEntityById(final UniqueId uniqueId) {
        return this.entities.get(uniqueId);
    }

    @Override
    public final UniqueEntities getEntitiesByIds(final UniqueIds uniqueIds) {
        final UniqueEntities uniqueEntities = new DefaultUniqueEntities();

        final Iterator<UniqueId> idIterator = uniqueIds.iterator();
        while (idIterator.hasNext()) {
            final UniqueId uid = idIterator.next();

            uniqueEntities.add(this.getEntityById(uid));
        }
        return uniqueEntities;
    }

    @Override
    public final UniqueEntity resolve(final UniqueId uid) {
        return this.getEntityById(uid);
    }

}
