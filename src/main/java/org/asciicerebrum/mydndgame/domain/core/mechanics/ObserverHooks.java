package org.asciicerebrum.mydndgame.domain.core.mechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author species8472
 */
public class ObserverHooks {

    private final List<ObserverHook> observerHooks
            = new ArrayList<ObserverHook>();

    public ObserverHooks(final ObserverHook... observerHooks) {
        this.observerHooks.addAll(Arrays.asList(observerHooks));
    }

    public final boolean contains(final ObserverHook observerHook) {
        return this.observerHooks.contains(observerHook);
    }

}
