package org.asciicerebrum.mydndgame.domain.rules.composition;

import org.asciicerebrum.mydndgame.domain.rules.composition.Condition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;

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

    public final Iterator<Condition> iterator() {
        return this.elements.iterator();
    }

}
