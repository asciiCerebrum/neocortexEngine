package org.asciicerebrum.mydndgame.domain.mechanics.interfaces;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;

/**
 *
 * @author species8472
 */
public interface BonusSource {

    Boni getBoni();

    BonusSources getBonusSources();

}
