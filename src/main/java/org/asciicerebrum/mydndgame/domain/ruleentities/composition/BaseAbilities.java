package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.AbilityScore;

/**
 *
 * @author species8472
 */
public class BaseAbilities implements BonusSource, ObserverSource {

    /**
     * Central map of base abilities.
     */
    private final Map<Ability, AbilityScore> elements
            = new HashMap<Ability, AbilityScore>();

    /**
     * Add a further base ability entry to the map.
     *
     * @param entry the entry to add.
     */
    public final void addBaseAbilityEntry(final BaseAbilityEntry entry) {
        this.elements.put(entry.getAbility(), entry.getAbilityValue());
    }

    /**
     * Retrieves the ability score for the corresponding ability.
     *
     * @param ability the ability the score is needed for.
     * @return the ability score.
     */
    public final AbilityScore getValueForAbility(final Ability ability) {
        return this.elements.get(ability);
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        BonusSources bonusSources = new BonusSources();

        for (Ability ability : this.elements.keySet()) {
            bonusSources.add(ability);
        }

        return bonusSources;
    }

    /**
     * Iterator over the abilities of the map.
     *
     * @return the iterator.
     */
    public final Iterator<Ability> abilityIterator() {
        return this.elements.keySet().iterator();
    }

}
