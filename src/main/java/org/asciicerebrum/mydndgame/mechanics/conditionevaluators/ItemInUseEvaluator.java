package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;
import org.asciicerebrum.mydndgame.services.core.ObservableService;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

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
     * Accumulates observers.
     */
    private ObservableService observableService;

    /**
     * Getting settings from the character.
     */
    private SituationContextService ctxService;

    /**
     * {@inheritDoc} Strategy: gather all boni/observers from the current weapon
     * in the situation context. Then check if this bonus/observer is part of
     * the list.
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final UniqueEntity contextItem) {

        final InventoryItem item = this.ctxService.getActiveItem(dndCharacter);

        if (item == null && contextItem != null) {
            return false;
        } else if (item != null && contextItem == null) {
            return false;
        }

        return (contextItem == null && item == null)
                || item.equals(contextItem);
    }

    /**
     * @param observableServiceInput the observableService to set
     */
    public final void setObservableService(
            final ObservableService observableServiceInput) {
        this.observableService = observableServiceInput;
    }

    /**
     * @param ctxServiceInput the ctxService to set
     */
    public final void setCtxService(
            final SituationContextService ctxServiceInput) {
        this.ctxService = ctxServiceInput;
    }

}
