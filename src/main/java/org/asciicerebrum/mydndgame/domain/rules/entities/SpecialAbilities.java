package org.asciicerebrum.mydndgame.domain.rules.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.ObserverSource;

/**
 *
 * @author species8472
 */
public class SpecialAbilities implements BonusSource, ObserverSource {

    /**
     * The list of special abilities.
     */
    private List<SpecialAbility> elements
            = new ArrayList<SpecialAbility>();

    /**
     * @param specialAbilitiesInput the specialAbilities to set
     */
    public final void setSpecialAbilities(
            final List<SpecialAbility> specialAbilitiesInput) {
        this.elements = specialAbilitiesInput;
    }

    public final void add(final SpecialAbility specialAbilityInput) {
        this.elements.add(specialAbilityInput);
    }

    public final void add(final SpecialAbilities specialAbilitiesInput) {
        this.elements.addAll(specialAbilitiesInput.elements);
    }

    public final Iterator<SpecialAbility> iterator() {
        return this.elements.iterator();
    }

    @Override
    public Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        for (final SpecialAbility ability : this.elements) {
            bonusSources.add(ability);
        }

        return bonusSources;
    }

}
