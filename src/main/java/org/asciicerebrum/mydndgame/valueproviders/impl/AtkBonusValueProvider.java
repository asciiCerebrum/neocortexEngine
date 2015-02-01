package org.asciicerebrum.mydndgame.valueproviders.impl;

import org.asciicerebrum.mydndgame.valueproviders.DynamicValueProvider;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.entities.Weapon;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
import org.asciicerebrum.mydndgame.services.statistics.AtkCalculationService;

/**
 *
 * @author Tabea Raab
 */
public class AtkBonusValueProvider implements DynamicValueProvider {

    /**
     * The rank of the atk bonus in question.
     */
    private BonusRank rank;

    /**
     * Calculates attack boni.
     */
    private AtkCalculationService atkCalcService;

    /**
     * The situation context as a service.
     */
    private SituationContextService situationContextService;

    /**
     * {@inheritDoc}
     */
    @Override
    public final BonusValue getDynamicValue(final DndCharacter dndCharacter) {

        final InventoryItem item = this.situationContextService
                .getActiveItem(dndCharacter);

        if (!(item instanceof Weapon)) {
            return null;
        }

        final Weapon weapon = (Weapon) item;

        final BonusValueTuple atkBonusTuple
                = atkCalcService.calcAtkBoni(weapon, dndCharacter);

        return atkBonusTuple.getBonusValueByRank(this.rank);
    }

    /**
     * @param rankInput the rank to set
     */
    public final void setRank(final BonusRank rankInput) {
        this.rank = rankInput;
    }

    /**
     * @param atkCalcServiceInput the atkCalcService to set
     */
    public final void setAtkCalcService(
            final AtkCalculationService atkCalcServiceInput) {
        this.atkCalcService = atkCalcServiceInput;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }
}
