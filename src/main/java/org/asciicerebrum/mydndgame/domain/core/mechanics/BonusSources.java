package org.asciicerebrum.mydndgame.domain.core.mechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class BonusSources {

    public final static BonusSources EMPTY_BONUSSOURCES = new BonusSources();

    private final List<BonusSource> bonusSources = new ArrayList<BonusSource>();

    public BonusSources() {
    }

    public BonusSources(final BonusSource... bonusSources) {
        this.bonusSources.addAll(Arrays.asList(bonusSources));
    }

    public void add(final BonusSource bonusSource) {
        this.bonusSources.add(bonusSource);
    }

    /**
     * @return the bonusSources
     */
    public Iterator<BonusSource> iterator() {
        return this.bonusSources.iterator();
    }

}
