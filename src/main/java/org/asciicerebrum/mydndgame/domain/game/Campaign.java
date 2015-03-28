package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.factories.EntityFactory;
import org.asciicerebrum.mydndgame.domain.factories.Reassignments;
import org.asciicerebrum.mydndgame.domain.factories.Reassignments.ReassignmentEntry;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;

/**
 *
 * @author species8472
 */
public class Campaign {

    /**
     * The map of entities registered in this campagin. The instances are mapped
     * to their unique ids.
     */
    private final Map<UniqueId, UniqueEntity> entities
            = new HashMap<UniqueId, UniqueEntity>();

    /**
     * The reassignments to handle the multiple tries of object creation.
     */
    private final Reassignments reassignments = new Reassignments();

    /**
     * The most current combat round currently happening in the campaign.
     */
    private CombatRound combatRound;

    /**
     * Puts a new unique entity into the campaign.
     *
     * @param uniqueEntity the unique entity in question.
     */
    public final void registerUniqueEntity(final UniqueEntity uniqueEntity) {
        this.entities.put(uniqueEntity.getUniqueId(), uniqueEntity);
    }

    /**
     * Retrieves the entity back from the map by its unique id.
     *
     * @param uniqueId the id to identify the object needed.
     * @return the object instance with that id.
     */
    public final UniqueEntity getEntityById(final UniqueId uniqueId) {
        return this.entities.get(uniqueId);
    }

    /**
     * @return the combatRound
     */
    public final CombatRound getCombatRound() {
        return combatRound;
    }

    /**
     * @param combatRoundInput the combatRound to set
     */
    public final void setCombatRound(final CombatRound combatRoundInput) {
        this.combatRound = combatRoundInput;
    }

    /**
     * Adds an entry to the list of to-resolve-later items.
     *
     * @param factory the factory of the item to resolve.
     * @param setup the setup of the item to resolve.
     * @param entity the instance of the item that has to be resolved.
     */
    public final void addReassignmentEntry(final EntityFactory factory,
            final EntitySetup setup, final Object entity) {
        this.reassignments.addEntry(factory, setup, entity);
    }

    /**
     * Iterator over the list of unresolved entries.
     *
     * @return the iterator over the list.
     */
    public final Iterator<ReassignmentEntry> reassignmentIterator() {
        return this.reassignments.getIterator();
    }

}
