package org.asciicerebrum.mydndgame.domain.core.mechanics;

import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public interface ObserverSource {

    Observers getObservers();

    ObserverSources getObserverSources();

}
