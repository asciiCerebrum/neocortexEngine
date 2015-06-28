package org.asciicerebrum.neocortexengine.domain.ruleentities.composition;

import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.core.particles.AbilityScore;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.UniqueEntityResolver;

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
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni contextBoni = new ContextBoni();
        for (Ability ability : this.elements.keySet()) {
            contextBoni.add(ability.getBoni(context, resolver));
        }
        return contextBoni;
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
