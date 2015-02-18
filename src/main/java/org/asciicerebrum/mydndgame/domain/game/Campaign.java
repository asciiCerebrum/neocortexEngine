package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import java.util.HashMap;
import java.util.Map;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

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

}
