package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public class Proficiency extends Feature implements FeatBinding {

    @Override
    public final BonusSources getBonusSources(
            final UniqueEntityResolver resolver) {
        return BonusSources.EMPTY_BONUSSOURCES;
    }
}
