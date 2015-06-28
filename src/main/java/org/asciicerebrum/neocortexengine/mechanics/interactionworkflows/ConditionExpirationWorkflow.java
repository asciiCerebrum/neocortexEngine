package org.asciicerebrum.neocortexengine.mechanics.interactionworkflows;

import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.IWorkflow;
import java.util.Iterator;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.game.CombatRound;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.neocortexengine.services.application.ConditionApplicationService;
import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;

/**
 *
 * @author species8472
 */
public class ConditionExpirationWorkflow implements IWorkflow {

    /**
     * The condition application service.
     */
    private ConditionApplicationService conditionService;

    /**
     * The entity pool service.
     */
    private EntityPoolService entityPoolService;

    /**
     * {@inheritDoc} It checks all conditions of all participants if they expire
     * in this new combat round position and unregisters them.
     */
    @Override
    public final void runWorkflow(final Interaction interaction,
            final Campaign campaign) {

        final CombatRound combatRound = campaign.getCombatRound();

        final Iterator<UniqueId> iterator
                = combatRound.participantsIterator();
        while (iterator.hasNext()) {
            final UniqueId participantId = iterator.next();

            this.getConditionService().removeExpiredConditions(
                    (DndCharacter) this.getEntityPoolService()
                    .getEntityById(participantId),
                    combatRound.getCurrentDate());
        }
    }

    /**
     * @param conditionServiceInput the conditionService to set
     */
    public final void setConditionService(
            final ConditionApplicationService conditionServiceInput) {
        this.conditionService = conditionServiceInput;
    }

    /**
     * @return the conditionService
     */
    public final ConditionApplicationService getConditionService() {
        return conditionService;
    }

    /**
     * @return the entityPoolService
     */
    public final EntityPoolService getEntityPoolService() {
        return entityPoolService;
    }

    /**
     * @param entityPoolServiceInput the entityPoolService to set
     */
    public final void setEntityPoolService(
            final EntityPoolService entityPoolServiceInput) {
        this.entityPoolService = entityPoolServiceInput;
    }

}
