package org.asciicerebrum.mydndgame.domain.mechanics.bonus.source;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;

/**
 *
 * @author species8472
 */
public interface BonusSource {

    /**
     * @return the collection of boni.
     */
    Boni getBoni();

    /**
     * @param resolver the service for translating the uniqueId to the
     * corersponding entity.
     * @return the collection of bonus sources.
     */
    BonusSources getBonusSources(UniqueEntityResolver resolver);

}
