package org.asciicerebrum.mydndgame.domain.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.CombatRound;
import java.util.HashMap;
import java.util.Map;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public class Campaign {

    private final Map<UniqueId, UniqueEntity> entities
            = new HashMap<UniqueId, UniqueEntity>();

    private CombatRound combatRound;

    public void registerUniqueEntity(final UniqueEntity uniqueEntity) {
        this.entities.put(uniqueEntity.getUniqueId(), uniqueEntity);
    }

    public UniqueEntity getEntityById(final UniqueId uniqueId) {
        return this.entities.get(uniqueId);
    }

    /**
     * @return the combatRound
     */
    public CombatRound getCombatRound() {
        return combatRound;
    }

    /**
     * @param combatRound the combatRound to set
     */
    public void setCombatRound(CombatRound combatRound) {
        this.combatRound = combatRound;
    }

}
