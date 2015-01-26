package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.attribution.ConditionType;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.ArmorClass;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.prototypes.DiceAction;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;

/**
 *
 * @author species8472
 */
public class AcCalculationService {

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

    public final ArmorClass calcAc(
            final DndCharacter dndCharacter) {

        final BonusValueTuple acTuple = this.bonusService.calculateBonusValues(
                new BonusSources(dndCharacter),
                new BonusTargets(this.acBaseAction, this.acAction),
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

    final ArmorClass toArmorClass(final BonusValueTuple acTuple) {
        final ArmorClass armorClass = new ArmorClass();
        armorClass.add(acTuple.getBonusValueByRank(BonusRank.RANK_0))
                .add(this.acBaseAction.getConstValue());
        return armorClass;
    }

    /**
     * Keep the following use cases in mind.
     *
     * 1. Flat-footed: A character who has not yet acted during a combat is
     * flat-footed, not yet reacting normally to the situation. A flat-footed
     * character loses his Dexterity bonus to AC (if any) and cannot make
     * attacks of opportunity.<br />
     * 2. Touch attack<br />
     * 3. Wearing a shield and armor (both mwk)<br />
     * 3a. Armor that can also be used as a weapon<br />
     * 3b. Armor that is not worn/wielded does not contribute to the ac.<br />
     * 4. Feat Dodge: against a designated opponent --> this is only relevant
     * the real current AC method.<br />
     * 5. Armor proficiency.<br />
     * 6. Max Dexterity Bonus limit.
     *
     * This method is for statistical purpose only. What would be the ideal AC
     * of this character if nothing else (no special negative conditions, etc.)
     * would apply.
     *
     * @param dndCharacter the context.
     * @return the calculated standard armor class of this character.
     */
    //TODO implement all use cases mentioned above.
    public final ArmorClass calcAcStandard(
            final DndCharacter dndCharacter) {

        final BonusValueTuple acTuple = this.bonusService.calculateBonusValues(
                dndCharacter, this.acBaseAction,
                dndCharacter,
                dndCharacter, ObserverHook.AC_BASE,
                dndCharacter);

        return this.toArmorClass(acTuple);
    }

    /**
     * This method is for statistcial purpose only. What would be AC be if the
     * character was flat-footed and nothing else would apply. It is a
     * simulation of this condition.
     *
     * @param dndCharacter the context.
     * @return the armor class under the condition flat-footed.
     */
    public final ArmorClass calcAcFlatFooted(
            final DndCharacter dndCharacter) {
        final BonusValueTuple acTuple = this.bonusService.calculateBonusValues(
                new BonusSources(dndCharacter, this.flatFooted),
                new BonusTargets(this.acBaseAction),
                dndCharacter,
                new ObserverSources(dndCharacter, this.flatFooted),
                new ObserverHooks(ObserverHook.AC_BASE),
                dndCharacter);

        return this.toArmorClass(acTuple);
    }

    /**
     * This method is for statistical purpose only. What would the AC be if it
     * was a touch attack and nothing else would apply.
     *
     * @param dndCharacter the context.
     * @return the armor class when experiencing a touch attack.
     */
    public final ArmorClass calcAcTouch(
            final DndCharacter dndCharacter) {
        final BonusValueTuple acTuple = this.bonusService.calculateBonusValues(
                new BonusSources(dndCharacter, this.touchAttackAction),
                new BonusTargets(this.acBaseAction),
                dndCharacter,
                new ObserverSources(dndCharacter, this.touchAttackAction),
                new ObserverHooks(ObserverHook.AC_BASE),
                dndCharacter);

        return this.toArmorClass(acTuple);
    }

    /**
     * @param acBaseAction the acBaseAction to set
     */
    public final void setAcBaseAction(final DiceAction acBaseAction) {
        this.acBaseAction = acBaseAction;
    }

    /**
     * @param acAction the acAction to set
     */
    public final void setAcAction(final DiceAction acAction) {
        this.acAction = acAction;
    }

    /**
     * @param flatFooted the flatFooted to set
     */
    public final void setFlatFooted(final ConditionType flatFooted) {
        this.flatFooted = flatFooted;
    }

    /**
     * @param touchAttackAction the touchAttackAction to set
     */
    public final void setTouchAttackAction(
            final DiceAction touchAttackAction) {
        this.touchAttackAction = touchAttackAction;
    }

    /**
     * @param bonusServiceInput the bonusService to set
     */
    public final void setBonusService(
            final BonusCalculationService bonusServiceInput) {
        this.bonusService = bonusServiceInput;
    }

}
