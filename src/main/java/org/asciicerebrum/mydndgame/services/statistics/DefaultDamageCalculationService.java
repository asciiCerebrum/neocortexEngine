package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
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
public class DefaultDamageCalculationService
        implements DamageCalculationService {

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
    @Override
    public final BonusValue calcDamageBonus(final Weapon weapon,
            final DndCharacter dndCharacter) {

        WeaponCategory itemAttackMode
                = this.getSituationContextService()
                .getItemAttackMode(weapon.getUniqueId(), dndCharacter);
        if (itemAttackMode == null) {
            itemAttackMode = weapon.getDefaultAttackMode();
        }

        final BonusValueTuple damageValues
                = this.getBonusService().calculateBonusValues(
                        new BonusSources(dndCharacter),
                        new BonusTargets(this.damageAction,
                                itemAttackMode.getAssociatedDamageDiceAction()),
                        weapon,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.DAMAGE,
                                itemAttackMode.getAssociatedDamageHook()),
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
