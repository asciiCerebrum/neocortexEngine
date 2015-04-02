package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;

/**
 *
 * @author species8472
 */
public class SpecialAbilities implements BonusSource, ObserverSource {

    /**
     * The list of special abilities.
     */
    private final List<SpecialAbility> elements
            = new ArrayList<SpecialAbility>();

    /**
     * The collection is cleared first.
     *
     * @param specialAbilitiesInput the specialAbilities to set
     */
    public final void setSpecialAbilities(
            final List<SpecialAbility> specialAbilitiesInput) {
        this.elements.clear();
        this.elements.addAll(specialAbilitiesInput);
    }

    /**
     * Adds a further special ability to the collection.
     *
     * @param specialAbilityInput the special ability to add.
     */
    public final void add(final SpecialAbility specialAbilityInput) {
        this.elements.add(specialAbilityInput);
    }

    /**
     * Adds a further collection of special abilities to the collection.
     *
     * @param specialAbilitiesInput the collection to add.
     */
    public final void add(final SpecialAbilities specialAbilitiesInput) {
        this.elements.addAll(specialAbilitiesInput.elements);
    }

    /**
     * Iterator over the collection of special abilities.
     *
     * @return the iterator.
     */
    public final Iterator<SpecialAbility> iterator() {
        return this.elements.iterator();
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources(
            final UniqueEntityResolver resolver) {
        final BonusSources bonusSources = new BonusSources();

        for (final SpecialAbility ability : this.elements) {
            bonusSources.add(ability);
        }

        return bonusSources;
    }

}
