package org.asciicerebrum.neocortexengine.services.statistics;

import org.asciicerebrum.neocortexengine.domain.core.particles.AbilityScore;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusTargets;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHooks;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;
import org.asciicerebrum.neocortexengine.services.core.BonusCalculationService;

/**
 *
 * @author species8472
 */
public class DefaultAbilityCalculationService
        implements AbilityCalculationService {

    /**
     * These are constants for calculating the ability mod from the ability
     * score. This whole class is d20-specific. So if you ever want to implement
     * another rule system, this class has to be replaced anyway. So these
     * values were not put into the spring xml.
     */
    private static final double ABILITY_BONUS_OFFSET = 10.0;
    /**
     * Constant for calculating the ability mod from the ability score.
     */
    private static final double ABILITY_BONUS_FRACTION = 2.0;

    /**
     * The bonus calculation service needed for dynamic bonus value calculation.
     */
    private BonusCalculationService bonusService;

    @Override
    public final AbilityScore calcAbilityScore(final DndCharacter dndCharacter,
            final Ability ability) {
        final AbilityScore baseValue
                = dndCharacter.getBaseAbilities().getValueForAbility(ability);

        // adding the advancements in that special ability.
        baseValue.add(dndCharacter.getLevelAdvancements()
                .countAbility(ability));
        return baseValue;
    }

    @Override
    public final AbilityScore calcCurrentAbilityScore(
            final DndCharacter dndCharacter, final Ability ability) {

        final AbilityScore baseValue = this.calcAbilityScore(
                dndCharacter, ability);

        final BonusValueTuple abilityTuple
                = this.getBonusService().calculateBonusValues(
                        new BonusSources(dndCharacter),
                        new BonusTargets(ability),
                        dndCharacter,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.ABILITY,
                                ability.getAssociatedHook()),
                        dndCharacter);

        // adding all dynamic boni to the ability score.
        baseValue.add(abilityTuple.getBonusValueByRank(BonusRank.RANK_0));

        return baseValue;
    }

    @Override
    public final BonusValue calcAbilityMod(final DndCharacter dndCharacter,
            final Ability ability) {
        final AbilityScore score = this.calcAbilityScore(
                dndCharacter, ability);

        return this.calcAbilityMod(score);
    }

    @Override
    public final BonusValue calcCurrentAbilityMod(
            final DndCharacter dndCharacter, final Ability ability) {

        final AbilityScore score = this.calcCurrentAbilityScore(
                dndCharacter, ability);

        return this.calcAbilityMod(score);
    }

    /**
     * Basic calculation method from the ability score to the mod.
     *
     * @param score the ability score.
     * @return the corresponding ability mod.
     */
    final BonusValue calcAbilityMod(final AbilityScore score) {
        long mod = Math.round(Math.floor((score.getValue()
                - ABILITY_BONUS_OFFSET) / ABILITY_BONUS_FRACTION));

        return new BonusValue(mod);
    }

    /**
     * @param bonusServiceInput the bonusService to set
     */
    public final void setBonusService(
            final BonusCalculationService bonusServiceInput) {
        this.bonusService = bonusServiceInput;
    }

    /**
     * @return the bonusService
     */
    public final BonusCalculationService getBonusService() {
        return bonusService;
    }

}
