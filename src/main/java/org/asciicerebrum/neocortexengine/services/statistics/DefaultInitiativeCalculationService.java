package org.asciicerebrum.neocortexengine.services.statistics;

import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.services.core.BonusCalculationService;

/**
 *
 * @author species8472
 */
public class DefaultInitiativeCalculationService implements
        InitiativeCalculationService {

    /**
     * The bonus calculation service needed for dynamic bonus value calculation.
     */
    private BonusCalculationService bonusService;

    /**
     * Dice action associated with initiative.
     */
    private DiceAction initAction;

    @Override
    public final BonusValue calcInitBonus(final DndCharacter dndCharacter) {

        final BonusValueTuple initValues
                = this.getBonusService().calculateBonusValues(
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

    /**
     * @return the bonusService
     */
    public final BonusCalculationService getBonusService() {
        return bonusService;
    }

}
