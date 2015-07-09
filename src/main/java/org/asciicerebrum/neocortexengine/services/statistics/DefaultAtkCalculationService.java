package org.asciicerebrum.neocortexengine.services.statistics;

import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusTargets;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHooks;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;
import org.asciicerebrum.neocortexengine.services.core.BonusCalculationService;

/**
 *
 * @author species8472
 */
public class DefaultAtkCalculationService implements AtkCalculationService {

    /**
     * The bonus calculation service needed for dynamic bonus value calculation.
     */
    private BonusCalculationService bonusService;

    /**
     * Getting settings from the character.
     */
    private SituationContextService situationContextService;

    /**
     * The diceAction with id attackAction.
     */
    private DiceAction attackAction;

    @Override
    public final BonusValueTuple calcAtkBoni(final Weapon weapon,
            final DndCharacter dndCharacter) {
        final BonusValueTuple atkValues = dndCharacter.getBaseAtkBoni();
        WeaponCategory itemAttackMode
                = this.getSituationContextService()
                .getItemAttackMode(weapon.getUniqueId(), dndCharacter);
        if (itemAttackMode == null) {
            itemAttackMode = weapon.getDefaultAttackMode();
        }

        final BonusValueTuple atkBonus = this.getBonusService()
                .calculateBonusValues(
                        new BonusSources(dndCharacter),
                        new BonusTargets(this.attackAction,
                                itemAttackMode.getAssociatedAttackDiceAction()),
                        weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.ATTACK,
                                itemAttackMode.getAssociatedAttackHook()),
                        dndCharacter
                );
        atkValues.add(atkBonus.getBonusValueByRank(BonusRank.RANK_0));
        return atkValues;
    }

    /**
     * @param bonusServiceInput the bonusService to set
     */
    public final void setBonusService(
            final BonusCalculationService bonusServiceInput) {
        this.bonusService = bonusServiceInput;
    }

    /**
     * @param attackActionInput the attackAction to set
     */
    public final void setAttackAction(final DiceAction attackActionInput) {
        this.attackAction = attackActionInput;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

    /**
     * @return the bonusService
     */
    public final BonusCalculationService getBonusService() {
        return bonusService;
    }

    /**
     * @return the situationContextService
     */
    public final SituationContextService getSituationContextService() {
        return situationContextService;
    }

}
