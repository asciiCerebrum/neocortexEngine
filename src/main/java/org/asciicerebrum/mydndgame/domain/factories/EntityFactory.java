package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;

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
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     * @return the created instance.
     */
    T newEntity(EntitySetup setup, Reassignments reassignments);

    /**
     * Only set specific attributes that were impossible to set in the first run
     * because of possible cyclic dependencies. But now that everything is now
     * set up, this cycle should be resolvable.
     *
     * @param setup the base setup to get the properties from.
     * @param entity the entity to reassign the yet unresolved values to.
     * @param reassignments Something could still go wrong here. But unfound
     * objects could still be retrieved later.
     */
    void reAssign(EntitySetup setup, T entity, Reassignments reassignments);

}
