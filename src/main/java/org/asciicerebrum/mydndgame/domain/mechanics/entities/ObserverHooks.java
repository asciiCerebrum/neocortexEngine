package org.asciicerebrum.mydndgame.domain.mechanics.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author species8472
 */
public class ObserverHooks {

    private final List<ObserverHook> elements
            = new ArrayList<ObserverHook>();

    public ObserverHooks(final ObserverHook... observerHooks) {
        this.elements.addAll(Arrays.asList(observerHooks));
    }

    public final boolean contains(final ObserverHook observerHook) {
        return this.elements.contains(observerHook);
    }

}
