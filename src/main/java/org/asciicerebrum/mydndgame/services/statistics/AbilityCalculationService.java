package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.rules.Ability;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.AbilityScore;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.dndcharacter.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;

/**
 *
 * @author species8472
 */
public class AbilityCalculationService {

    /**
     * These are constants for calculating the ability mod from the ability
     * score. This whole class is d20-specific. So if you ever want to implement
     * another rule system, this class has to be replaced anyway. So these
     * values were not put into the spring xml.
     */
    private static final double ABILITY_BONUS_OFFSET = 10.0;
    private static final double ABILITY_BONUS_FRACTION = 2.0;

    /**
     * The one ability concerning all the other abilities.
     */
    private Ability generalAbility;
    /**
     * The bonus calculation service needed for dynamic bonus value calculation.
     */
    private BonusCalculationService bonusService;

    public final AbilityScore calcAbilityScore(final DndCharacter dndCharacter,
            final Ability ability) {
        final AbilityScore baseValue
                = dndCharacter.getBaseAbilities().getValueForAbility(ability);

        // adding the advancements in that special ability.
        baseValue.add(dndCharacter.getLevelAdvancements()
                .countAbility(ability));
        return baseValue;
    }

    public final AbilityScore calcCurrentAbilityScore(
            final DndCharacter dndCharacter, final Ability ability) {

        final AbilityScore baseValue = this.calcAbilityScore(
                dndCharacter, ability);

        final BonusValueTuple abilityTuple
                = this.bonusService.calculateBonusValues(
                        new BonusSources(dndCharacter),
                        new BonusTargets(this.generalAbility, ability),
                        dndCharacter,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.ABILITY,
                                ability.getAssociatedHook()),
                        dndCharacter);

        // adding all dynamic boni to the ability score.
        baseValue.add(abilityTuple.getBonusValueByRank(BonusRank.RANK_0));
        //TODO is it necessary to let the observers also change the result of
        // the ability value? A new hook would be needed.

        return baseValue;
    }

    public final BonusValue calcAbilityMod(final DndCharacter dndCharacter,
            final Ability ability) {
        final AbilityScore score = this.calcAbilityScore(
                dndCharacter, ability);

        return this.calcAbilityMod(score);
    }

    public final BonusValue calcCurrentAbilityMod(
            final DndCharacter dndCharacter, final Ability ability) {

        //TODO to this point it is not known if Boni/Observers influence the
        // ability mod. It should be the case that they only influence the
        // ability score directly. So please investigate and change this
        // behaviour here accordingly if this should ever be the case.
        final AbilityScore score = this.calcCurrentAbilityScore(
                dndCharacter, ability);

        return this.calcAbilityMod(score);
    }

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
     * @param generalAbilityInput the generalAbility to set
     */
    public final void setGeneralAbility(final Ability generalAbilityInput) {
        this.generalAbility = generalAbilityInput;
    }

}
