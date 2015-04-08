package org.asciicerebrum.mydndgame.domain.mechanics.bonus.source;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;

/**
 *
 * @author species8472
 */
public interface BonusSource {

    /**
     * @param context the context in order to contextualise context free boni
     * collections.
     * @param resolver the service for translating the uniqueId to the
     * corersponding entity.
     * @return the collection of boni in their specific context.
     */
    ContextBoni getBoni(UniqueEntity context, UniqueEntityResolver resolver);

}
