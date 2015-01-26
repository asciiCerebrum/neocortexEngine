package org.asciicerebrum.mydndgame.domain.gameentities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
public class Conditions implements BonusSource, ObserverSource {

    private final List<Condition> elements = new ArrayList<Condition>();

    public Conditions() {

    }

    public Conditions(final Condition... conditionsInput) {
        this.elements.addAll(Arrays.asList(conditionsInput));
    }

    /**
     * @return the conditions
     */
    public final List<Condition> getConditions() {
        return elements;
    }

    public final void add(final Condition condition) {
        this.elements.add(condition);
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        for (final Condition condition : this.elements) {
            bonusSources.add(condition);
        }

        return bonusSources;
    }

    @Override
    public final Observers getObservers() {
        return Observers.EMPTY_OBSERVERS;
    }

    @Override
    public final ObserverSources getObserverSources() {
        final ObserverSources observerSources = new ObserverSources();

        for (final Condition condition : this.elements) {
            observerSources.add(condition);
        }

        return observerSources;
    }

    public final Iterator<Condition> iterator() {
        return this.elements.iterator();
    }

}
