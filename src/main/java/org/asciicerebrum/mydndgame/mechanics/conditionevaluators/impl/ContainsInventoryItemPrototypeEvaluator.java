package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.ruleentities.InventoryItemPrototype;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.ConditionEvaluator;

/**
 * Checks if the context entity is part of a given list.
 *
 * @author species8472
 */
public class ContainsInventoryItemPrototypeEvaluator
        implements ConditionEvaluator {

    /**
     * The reference list to check the existence of the context entity.
     */
    private List<InventoryItemPrototype> checkList;

    @Override
    public final boolean evaluate(final ICharacter dndCharacter,
            final UniqueEntity contextEntity) {

        if (!(contextEntity instanceof InventoryItem)) {
            return false;
        }

        final InventoryItem item = (InventoryItem) contextEntity;

        return this.getCheckList().contains(item.getInventoryItemPrototype());
    }

    /**
     * @return the checkList
     */
    public final List<InventoryItemPrototype> getCheckList() {
        return checkList;
    }

    /**
     * @param checkListInput the checkList to set
     */
    public final void setCheckList(
            final List<InventoryItemPrototype> checkListInput) {
        this.checkList = checkListInput;
    }

}
