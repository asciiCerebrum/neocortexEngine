package org.asciicerebrum.mydndgame.mechanics.valueproviders;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.DynamicValueProvider;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
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
     * {@inheritDoc}
     */
    @Override
    public final BonusValue getDynamicValue(final ICharacter dndCharacter,
            final UniqueEntity contextItem) {

        if (!(contextItem instanceof Weapon)) {
            return null;
        }

        final Weapon weapon = (Weapon) contextItem;

        final BonusValueTuple atkBonusTuple
                = atkCalcService.calcAtkBoni(weapon, (DndCharacter) dndCharacter);

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

}
