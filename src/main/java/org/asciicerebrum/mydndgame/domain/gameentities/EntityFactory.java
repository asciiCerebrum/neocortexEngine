package org.asciicerebrum.mydndgame.domain.gameentities;

import org.asciicerebrum.mydndgame.domain.gameentities.setup.EntitySetup;

/**
 *
 * @author species8472
 * @param <T> the type of the entity to build.
 */
public interface EntityFactory<T> {

    T newEntity(EntitySetup<T> setup, Reassignments reassignments);

    /**
     * Only set specific attributes that were impossible to set in the first run
     * because of possible cyclic dependencies. But now that everything is now
     * set up, this cycle should be resolvable.
     *
     * @param setup the base setup to get the properties from.
     * @param entity the entity to reassign the yet unresolved values to.
     */
    void reAssign(EntitySetup<T> setup, T entity);

}
