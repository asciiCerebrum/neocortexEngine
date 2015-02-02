package org.asciicerebrum.mydndgame.domain.rules.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.ObserverSource;
import org.asciicerebrum.mydndgame.domain.rules.entities.FeatType;

/**
 *
 * @author species8472
 */
public class Feats implements BonusSource, ObserverSource {

    private final List<FeatType> elements = new ArrayList<FeatType>();

    public final void addFeat(final FeatType feat) {
        this.elements.add(feat);
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        for (final FeatType feat : this.elements) {
            bonusSources.add(feat);
        }

        return bonusSources;
    }

    public final Iterator<FeatType> iterator() {
        return this.elements.iterator();
    }

}
