package org.asciicerebrum.mydndgame.valueproviders;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusValueProvider;
import org.asciicerebrum.mydndgame.interfaces.entities.IAbility;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;

/**
 *
 * @author species8472
 */
public class AbilityBonusValueProvider implements BonusValueProvider {

    /**
     * The associated ability for this value provider.
     */
    private IAbility ability;

    /**
     *
     * @param character The character as the context for calculating the bonus
     * of the given ability.
     * @return the dynamically calculated bonus value of the given ability -
     * depending on the character.
     */
    public final Long getDynamicValue(final ICharacter character) {

        return character.getAbilityMod(this.getAbility());
    }

    /**
     * @return the ability
     */
    public final IAbility getAbility() {
        return ability;
    }

    /**
     * @param abilityInput the ability to set
     */
    public final void setAbility(final IAbility abilityInput) {
        this.ability = abilityInput;
    }

}
