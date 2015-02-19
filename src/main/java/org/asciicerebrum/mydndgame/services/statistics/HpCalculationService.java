package org.asciicerebrum.mydndgame.services.statistics;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.core.particles.AdvancementNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;

/**
 *
 * @author species8472
 */
public class HpCalculationService {

    /**
     * The diceAction with id touchAttackAction.
     */
    private DiceAction hpAction;

    /**
     * Ability relevant to hp calculation - normally CON.
     */
    private Ability conAbility;
    /**
     * The bonus calculation service needed for dynamic bonus value calculation.
     */
    private BonusCalculationService bonusService;

    /**
     * The ability mod service needed for dynamic ability value calculation.
     */
    private AbilityCalculationService abilityService;

    /**
     * Calculates the unmodified base hit points of a given dnd character.
     *
     * @param dndCharacter the character the hit points are needed for.
     * @return the base hit points.
     */
    final HitPoints calcBaseHp(final DndCharacter dndCharacter) {
        final HitPoints baseHp = new HitPoints();

        // Max number of hit dice on first level.
        baseHp.add(dndCharacter.getLevelAdvancements()
                .getLevelAdvancementByNumber(AdvancementNumber.ADV_NO_0)
                .getClassLevel()
                .getCharacterClass()
                .getHitDice().getSides());

        // All following levels according to dice throw.
        final Iterator<LevelAdvancement> lvlAdvIterator
                = dndCharacter.getLevelAdvancements().iterator();
        while (lvlAdvIterator.hasNext()) {
            final LevelAdvancement lvlAdv = lvlAdvIterator.next();
            if (AdvancementNumber.ADV_NO_0.equals(lvlAdv.getAdvNumber())) {
                continue;
            }
            baseHp.add(lvlAdv.getHpAdvancement());
        }

        return baseHp;
    }

    /**
     * The maximum hit points the character normally has. Without any special
     * effects in action.
     *
     * @param dndCharacter the character the max hit points are needed for.
     * @return the max hit points.
     */
    public final HitPoints calcMaxHp(final DndCharacter dndCharacter) {
        final HitPoints maxHp = this.calcBaseHp(dndCharacter);

        // Multiplication with standard constitution modifier.
        maxHp.multiply(this.getAbilityService().calcAbilityMod(dndCharacter,
                this.getConAbility()));

        return maxHp;
    }

    /**
     * The maximum hit points the character can currently have. E.g. when
     * magical effects or other stuff is applied (false life, rage, effects on
     * abilities or on hp directly, etc.) which also affects the maximum hp.
     *
     * @param dndCharacter the character the max hp are needed for.
     * @return the max hit points.
     */
    public final HitPoints calcCurrentMaxHp(final DndCharacter dndCharacter) {
        final HitPoints maxHp = this.calcBaseHp(dndCharacter);

        // Multiplication with current constitution modifier.
        maxHp.multiply(this.getAbilityService().calcCurrentAbilityMod(
                dndCharacter, this.getConAbility()));

        // boni and observers apply after the multiplication!
        final BonusValueTuple hpBoni
                = this.getBonusService().calculateBonusValues(dndCharacter,
                        this.getHpAction(),
                        dndCharacter,
                        dndCharacter,
                        ObserverHook.HIT_POINTS,
                        dndCharacter);
        maxHp.add(hpBoni.getBonusValueByRank(BonusRank.RANK_0));

        return maxHp;
    }

    /**
     * Calculates the current active hitpoints of a given dnd character.
     *
     * @param dndCharacter the character the hp are needed for.
     * @return the hit points.
     */
    public final HitPoints calcCurrentHp(final DndCharacter dndCharacter) {
        final HitPoints currentHp = dndCharacter.getCurrentStaticHp();

        final BonusValueTuple hpBoni
                = this.getBonusService().calculateBonusValues(dndCharacter,
                        this.getHpAction(),
                        dndCharacter,
                        dndCharacter,
                        ObserverHook.HIT_POINTS,
                        dndCharacter);
        currentHp.add(hpBoni.getBonusValueByRank(BonusRank.RANK_0));

        return currentHp;
    }

    /**
     * @param hpActionInput the hpAction to set
     */
    public final void setHpAction(final DiceAction hpActionInput) {
        this.hpAction = hpActionInput;
    }

    /**
     * @param bonusServiceInput the bonusService to set
     */
    public final void setBonusService(
            final BonusCalculationService bonusServiceInput) {
        this.bonusService = bonusServiceInput;
    }

    /**
     * @param conAbilityInput the conAbility to set
     */
    public final void setConAbility(final Ability conAbilityInput) {
        this.conAbility = conAbilityInput;
    }

    /**
     * @param abilityServiceInput the abilityService to set
     */
    public final void setAbilityService(
            final AbilityCalculationService abilityServiceInput) {
        this.abilityService = abilityServiceInput;
    }

    /**
     * @return the hpAction
     */
    public final DiceAction getHpAction() {
        return hpAction;
    }

    /**
     * @return the conAbility
     */
    public final Ability getConAbility() {
        return conAbility;
    }

    /**
     * @return the bonusService
     */
    public final BonusCalculationService getBonusService() {
        return bonusService;
    }

    /**
     * @return the abilityService
     */
    public final AbilityCalculationService getAbilityService() {
        return abilityService;
    }

}
