package org.asciicerebrum.mydndgame.valueproviders;

import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.services.statistics.AbilityCalculationService;

/**
 *
 * @author species8472
 */
public class AbilityBonusValueProvider implements DynamicValueProvider {

    /**
     * The associated ability for this value provider.
     */
    private Ability ability;

    /**
     * Service for the actual calculation of the ability bonus.
     */
    private AbilityCalculationService abilityCalcService;

    /**
     *
     * @param dndCharacter The character as the context for calculating the
     * bonus of the given ability.
     * @return the dynamically calculated bonus value of the given ability -
     * depending on the character.
     */
    @Override
    public final BonusValue getDynamicValue(final DndCharacter dndCharacter) {

        return this.abilityCalcService.calcCurrentAbilityMod(
                dndCharacter, this.ability);
    }

    /**
     * @param abilityInput the ability to set
     */
    public final void setAbility(final Ability abilityInput) {
        this.ability = abilityInput;
    }

    /**
     * @param abilityCalcServiceInput the abilityCalcService to set
     */
    public final void setAbilityCalcService(
            final AbilityCalculationService abilityCalcServiceInput) {
        this.abilityCalcService = abilityCalcServiceInput;
    }

}
