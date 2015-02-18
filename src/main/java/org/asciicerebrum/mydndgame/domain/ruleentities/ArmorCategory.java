package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;

/**
 *
 * @author species8472
 */
public class ArmorCategory extends Feature {

    @Override
    public final BonusSources getBonusSources() {
        return BonusSources.EMPTY_BONUSSOURCES;
    }
}
