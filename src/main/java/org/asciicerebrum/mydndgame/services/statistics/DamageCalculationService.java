package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.Weapon;
import org.asciicerebrum.mydndgame.domain.gameentities.prototypes.DiceAction;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class DamageCalculationService {

    /**
     * The bonus calculation service needed for dynamic bonus value calculation.
     */
    private BonusCalculationService bonusService;

    /**
     * The diceAction with id attackAction.
     */
    private DiceAction damageAction;

    /**
     * Getting settings from the character.
     */
    private SituationContextService situationContextService;

    //TODO test this thouroughly!! also with multiple weapons in the slots!
    public BonusValue calcDamageBonus(final Weapon weapon,
            final DndCharacter dndCharacter) {

        final BonusValueTuple damageValues
                = this.bonusService.calculateBonusValues(
                        new BonusSources(dndCharacter),
                        new BonusTargets(this.damageAction,
                                this.situationContextService
                                .getWeaponAttackMode(weapon, dndCharacter)
                                .getAssociatedAttackDiceAction()),
                        weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.DAMAGE,
                                this.situationContextService
                                .getWeaponAttackMode(weapon, dndCharacter)
                                .getAssociatedDamageHook()),
                        dndCharacter
                );

        return damageValues.getBonusValueByRank(BonusRank.RANK_0);
    }

    /**
     * @param bonusServiceInput the bonusService to set
     */
    public final void setBonusService(
            final BonusCalculationService bonusServiceInput) {
        this.bonusService = bonusServiceInput;
    }

    /**
     * @param damageActionInput the damageAction to set
     */
    public final void setDamageAction(final DiceAction damageActionInput) {
        this.damageAction = damageActionInput;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

}