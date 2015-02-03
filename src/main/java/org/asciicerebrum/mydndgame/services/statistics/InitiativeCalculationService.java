package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.rules.DiceAction;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;

/**
 *
 * @author species8472
 */
public class InitiativeCalculationService {

    /**
     * The bonus calculation service needed for dynamic bonus value calculation.
     */
    private BonusCalculationService bonusService;

    /**
     * Dice action associated with initiative.
     */
    private DiceAction initAction;

    public final BonusValue calcInitBonus(final DndCharacter dndCharacter) {

        final BonusValueTuple initValues
                = this.bonusService.calculateBonusValues(
                        dndCharacter,
                        this.initAction,
                        dndCharacter,
                        dndCharacter,
                        ObserverHook.INITIATIVE,
                        dndCharacter
                );

        return initValues.getBonusValueByRank(BonusRank.RANK_0);
    }

    /**
     * @param bonusServiceInput the bonusService to set
     */
    public final void setBonusService(
            final BonusCalculationService bonusServiceInput) {
        this.bonusService = bonusServiceInput;
    }

    /**
     * @param initActionInput the initAction to set
     */
    public final void setInitAction(final DiceAction initActionInput) {
        this.initAction = initActionInput;
    }

}
