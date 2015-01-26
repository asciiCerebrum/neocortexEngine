package org.asciicerebrum.mydndgame.domain.core.attribution;

import java.util.HashMap;
import java.util.Map;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.observers.Observers;
import org.asciicerebrum.mydndgame.domain.core.particles.AbilityScore;

/**
 *
 * @author species8472
 */
public class BaseAbilities implements BonusSource, ObserverSource {

    private final Map<Ability, AbilityScore> baseAbilities
            = new HashMap<Ability, AbilityScore>();

    public final void addBaseAbilityEntry(final BaseAbilityEntry entry) {
        this.baseAbilities.put(entry.getAbility(), entry.getAbilityValue());
    }

    public final AbilityScore getValueForAbility(final Ability ability) {
        return this.baseAbilities.get(ability);
    }

    @Override
    public Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public BonusSources getBonusSources() {
        BonusSources bonusSources = new BonusSources();

        for (Ability ability : this.baseAbilities.keySet()) {
            bonusSources.add(ability);
        }

        return bonusSources;
    }

    @Override
    public Observers getObservers() {
        return Observers.EMPTY_OBSERVERS;
    }

    @Override
    public ObserverSources getObserverSources() {
        ObserverSources observerSources = new ObserverSources();

        for (Ability ability : this.baseAbilities.keySet()) {
            observerSources.add(ability);
        }

        return observerSources;
    }

}
