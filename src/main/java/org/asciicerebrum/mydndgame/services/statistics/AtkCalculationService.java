package org.asciicerebrum.mydndgame.services.statistics;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.rules.entities.ClassLevel;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.Weapon;
import org.asciicerebrum.mydndgame.domain.rules.entities.DiceAction;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class AtkCalculationService {

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

    public final BonusValueTuple calcBaseAtkBoni(
            final DndCharacter dndCharacter) {

        final BonusValueTuple boniTuple = new BonusValueTuple();

        final Iterator<ClassLevel> clLvlIterator
                = dndCharacter.getLevelAdvancements().classLevelIterator();

        while (clLvlIterator.hasNext()) {
            final ClassLevel clLvl = clLvlIterator.next();
            boniTuple.add(clLvl.getBaseAtkBoniDelta());
        }

        return boniTuple;
    }

    /**
     * For statistical (and other) purposes it is necessary to calculate the
     * attack boni for a given weapon inside a given body slot with a given
     * attack mode (ranged or melee). E.g. I want to know the attack boni of my
     * magic dagger inside my backpack when thrown with my off-hand. So I can
     * print out this value on my character sheet.<br />
     * Another method can then determine the current situation - which weapon do
     * I currently have wielded, what attack mode have I chosen, etc. (by
     * querying the state registry of the character).
     *
     * @param weapon
     * @param dndCharacter
     * @return
     */
    public final BonusValueTuple calcAtkBoni(final Weapon weapon,
            final DndCharacter dndCharacter) {
        //TODO test this thouroughly!! also with multiple weapons in the slots!
        final BonusValueTuple atkValues
                = this.calcBaseAtkBoni(dndCharacter);
        final BonusValueTuple atkBonus = this.bonusService.calculateBonusValues(
                new BonusSources(dndCharacter),
                new BonusTargets(this.attackAction,
                        this.situationContextService
                        .getWeaponAttackMode(weapon, dndCharacter)
                        .getAssociatedAttackDiceAction()),
                weapon,
                new ObserverSources(dndCharacter),
                new ObserverHooks(ObserverHook.ATTACK,
                        this.situationContextService
                        .getWeaponAttackMode(weapon, dndCharacter)
                        .getAssociatedDamageHook()),
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

}
