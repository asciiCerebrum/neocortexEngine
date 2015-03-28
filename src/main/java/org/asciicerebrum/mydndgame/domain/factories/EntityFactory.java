package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.game.Campaign;
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
     * @param campaign the campaign as the base entity map.
     * @return the created instance.
     */
    T newEntity(EntitySetup setup, Campaign campaign);

    /**
     * Only set specific attributes that were impossible to set in the first run
     * because of possible cyclic dependencies. But now that everything is now
     * set up, this cycle should be resolvable.
     *
     * @param setup the base setup to get the properties from.
     * @param entity the entity to reassign the yet unresolved values to.
     * @param campaign the campaign as the base entity map.
     */
    void reAssign(EntitySetup setup, T entity, Campaign campaign);

}
