package org.asciicerebrum.mydndgame.domain.mechanics;

import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.Boni;

/**
 *
 * @author species8472
 */
public interface BonusSource {

    Boni getBoni();
    
    BonusSources getBonusSources();
    
}
