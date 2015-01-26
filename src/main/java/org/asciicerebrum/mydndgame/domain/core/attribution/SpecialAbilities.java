package org.asciicerebrum.mydndgame.domain.core.attribution;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public class SpecialAbilities implements BonusSource, ObserverSource {

    /**
     * The list of special abilities.
     */
    private List<SpecialAbility> specialAbilities
            = new ArrayList<SpecialAbility>();

    /**
     * @return the specialAbilities
     */
    public final List<SpecialAbility> getSpecialAbilities() {
        return specialAbilities;
    }

    /**
     * @param specialAbilitiesInput the specialAbilities to set
     */
    public final void setSpecialAbilities(
            final List<SpecialAbility> specialAbilitiesInput) {
        this.specialAbilities = specialAbilitiesInput;
    }

    public final void add(final SpecialAbility specialAbilityInput) {
        this.specialAbilities.add(specialAbilityInput);
    }

    public final void add(final SpecialAbilities specialAbilitiesInput) {
        this.specialAbilities.addAll(specialAbilitiesInput
                .getSpecialAbilities());
    }

    @Override
    public final ObserverSources getObserverSources() {
        final ObserverSources observerSources = new ObserverSources();

        for (SpecialAbility specialAbility : this.specialAbilities) {
            observerSources.add(specialAbility);
        }
        return observerSources;
    }

    @Override
    public Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        for (final SpecialAbility ability : this.specialAbilities) {
            bonusSources.add(ability);
        }

        return bonusSources;
    }

    @Override
    public final Observers getObservers() {
        return Observers.EMPTY_OBSERVERS;
    }

}
