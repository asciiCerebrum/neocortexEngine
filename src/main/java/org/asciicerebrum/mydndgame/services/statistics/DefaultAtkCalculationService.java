package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;

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
        //TODO test this thouroughly!! also with multiple weapons in the slots!
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
