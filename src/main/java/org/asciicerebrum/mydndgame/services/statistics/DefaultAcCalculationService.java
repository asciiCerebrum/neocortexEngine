package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.particles.ArmorClass;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.ruleentities.ConditionType;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;

/**
 *
 * @author species8472
 */
public class DefaultAcCalculationService implements AcCalculationService {

    /**
     * The diceAction with id acBaseAction.
     */
    private DiceAction acBaseAction;
    /**
     * The diceAction with id acAction.
     */
    private DiceAction acAction;
    /**
     * The condition of being flat footed.
     */
    private ConditionType flatFooted;
    /**
     * The diceAction with id touchAttackAction.
     */
    private DiceAction touchAttackAction;
    /**
     * The bonus calculation service needed for dynamic bonus value calculation.
     */
    private BonusCalculationService bonusService;

    @Override
    public final ArmorClass calcAc(
            final DndCharacter dndCharacter) {

        final BonusValueTuple acTuple = this.bonusService.calculateBonusValues(
                new BonusSources(dndCharacter),
                new BonusTargets(this.getAcBaseAction(), this.acAction),
                dndCharacter,
                new ObserverSources(dndCharacter),
                new ObserverHooks(ObserverHook.AC_BASE, ObserverHook.AC),
                dndCharacter);

        //TODO armor must have bonus condition - only applied when
        // wielded correctly!
        //TODO test for correct handling of touch attacks!
        //TODO test for correct handling of flatfootedness at the beginning of
        // a combat round.
        return this.toArmorClass(acTuple);
    }

    /**
     * Converts a bonus value tuple to an armor class value.
     *
     * @param acTuple the bonus value tuple given.
     * @return the corresponding armor class.
     */
    final ArmorClass toArmorClass(final BonusValueTuple acTuple) {
        final ArmorClass armorClass = new ArmorClass();
        armorClass.add(acTuple.getBonusValueByRank(BonusRank.RANK_0))
                .add(this.getAcBaseAction().getConstValue());
        return armorClass;
    }

    @Override
    public final ArmorClass calcAcStandard(
            final DndCharacter dndCharacter) {

        final BonusValueTuple acTuple = this.bonusService.calculateBonusValues(
                dndCharacter, this.getAcBaseAction(),
                dndCharacter,
                dndCharacter, ObserverHook.AC_BASE,
                dndCharacter);

        return this.toArmorClass(acTuple);
    }

    @Override
    public final ArmorClass calcAcFlatFooted(
            final DndCharacter dndCharacter) {
        final BonusValueTuple acTuple = this.bonusService.calculateBonusValues(
                new BonusSources(dndCharacter, this.flatFooted),
                new BonusTargets(this.getAcBaseAction()),
                dndCharacter,
                new ObserverSources(dndCharacter, this.flatFooted),
                new ObserverHooks(ObserverHook.AC_BASE),
                dndCharacter);

        return this.toArmorClass(acTuple);
    }

    @Override
    public final ArmorClass calcAcTouch(
            final DndCharacter dndCharacter) {
        final BonusValueTuple acTuple = this.bonusService.calculateBonusValues(
                new BonusSources(dndCharacter, this.touchAttackAction),
                new BonusTargets(this.getAcBaseAction()),
                dndCharacter,
                new ObserverSources(dndCharacter, this.touchAttackAction),
                new ObserverHooks(ObserverHook.AC_BASE),
                dndCharacter);

        return this.toArmorClass(acTuple);
    }

    /**
     * @param acBaseActionInput the acBaseAction to set
     */
    public final void setAcBaseAction(final DiceAction acBaseActionInput) {
        this.acBaseAction = acBaseActionInput;
    }

    /**
     * @param acActionInput the acAction to set
     */
    public final void setAcAction(final DiceAction acActionInput) {
        this.acAction = acActionInput;
    }

    /**
     * @param flatFootedInput the flatFooted to set
     */
    public final void setFlatFooted(final ConditionType flatFootedInput) {
        this.flatFooted = flatFootedInput;
    }

    /**
     * @param touchAttackActionInput the touchAttackAction to set
     */
    public final void setTouchAttackAction(
            final DiceAction touchAttackActionInput) {
        this.touchAttackAction = touchAttackActionInput;
    }

    /**
     * @param bonusServiceInput the bonusService to set
     */
    public final void setBonusService(
            final BonusCalculationService bonusServiceInput) {
        this.bonusService = bonusServiceInput;
    }

    /**
     * @return the acBaseAction
     */
    public final DiceAction getAcBaseAction() {
        return acBaseAction;
    }
}
