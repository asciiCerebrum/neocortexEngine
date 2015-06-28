package org.asciicerebrum.neocortexengine.services.statistics;

import org.asciicerebrum.neocortexengine.domain.core.particles.AdvancementNumber;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.core.particles.HitPoints;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;
import org.asciicerebrum.neocortexengine.services.core.BonusCalculationService;

/**
 *
 * @author species8472
 */
public class DefaultHpCalculationService implements HpCalculationService {

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

    @Override
    public final HitPoints calcMaxHp(final DndCharacter dndCharacter) {
        final HitPoints maxHp = dndCharacter.getBaseMaxHp();
        final AdvancementNumber maxLvl = dndCharacter.getTotalClassLevel();
        final BonusValue maxCon = this.getAbilityService()
                .calcAbilityMod(dndCharacter, this.getConAbility());

        // Multiplication with standard constitution modifier.
        maxHp.add(maxCon.multiply(maxLvl));

        return maxHp;
    }

    @Override
    public final HitPoints calcCurrentMaxHp(final DndCharacter dndCharacter) {
        final HitPoints maxHp = this.calcMaxHp(dndCharacter);

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

    @Override
    public final HitPoints calcCurrentHp(final DndCharacter dndCharacter) {
        return dndCharacter.getCurrentStaticHp();
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
