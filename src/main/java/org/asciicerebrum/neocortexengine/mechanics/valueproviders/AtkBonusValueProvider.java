package org.asciicerebrum.neocortexengine.mechanics.valueproviders;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.DynamicValueProvider;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.services.statistics.AtkCalculationService;

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
                = getAtkCalcService().calcAtkBoni(weapon,
                        (DndCharacter) dndCharacter);

        return atkBonusTuple.getBonusValueByRank(this.getRank());
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
     * @return the rank
     */
    public final BonusRank getRank() {
        return rank;
    }

    /**
     * @return the atkCalcService
     */
    public final AtkCalculationService getAtkCalcService() {
        return atkCalcService;
    }

}
