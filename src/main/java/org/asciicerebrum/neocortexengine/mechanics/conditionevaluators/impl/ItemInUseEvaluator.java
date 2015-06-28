package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;

/**
 * This evaluator is meant for all the boni/observers that only apply when the
 * specific weapon they originate from is really used actively. E.g. you cannot
 * get your +2 attack bonus from your magical sword that you are holding in your
 * OTHER hand that is NOT attacking the two-headed dread-ogre of doom.
 *
 * @author species8472
 */
public class ItemInUseEvaluator implements ConditionEvaluator {

    /**
     * Getting settings from the character.
     */
    private SituationContextService ctxService;

    /**
     * The entity pool service.
     */
    private EntityPoolService entityPoolService;

    /**
     * {@inheritDoc} Strategy: gather all boni/observers from the current weapon
     * in the situation context. Then check if this bonus/observer is part of
     * the list.
     */
    @Override
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextItem) {
        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        final UniqueEntity item = this.getEntityPoolService().getEntityById(
                this.getCtxService().getActiveItemId(dndCharacter));

        if (item == null) {
            return contextItem == null;
        }
        return item.equals(contextItem);
    }

    /**
     * @param ctxServiceInput the ctxService to set
     */
    public final void setCtxService(
            final SituationContextService ctxServiceInput) {
        this.ctxService = ctxServiceInput;
    }

    /**
     * @return the ctxService
     */
    public final SituationContextService getCtxService() {
        return ctxService;
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
