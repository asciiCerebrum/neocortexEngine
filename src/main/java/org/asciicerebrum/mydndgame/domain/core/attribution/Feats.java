package org.asciicerebrum.mydndgame.domain.core.attribution;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.observers.Observers;
import org.asciicerebrum.mydndgame.domain.gameentities.FeatType;

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

    @Override
    public final Observers getObservers() {
        return Observers.EMPTY_OBSERVERS;
    }

    @Override
    public final ObserverSources getObserverSources() {
        final ObserverSources observerSources = new ObserverSources();

        for (final FeatType feat : this.elements) {
            observerSources.add(feat);
        }

        return observerSources;
    }

}
