package org.asciicerebrum.mydndgame.domain.mechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author species8472
 */
public class ObserverHooks {

    /**
     * Central collection of observer hooks.
     */
    private final List<ObserverHook> elements
            = new ArrayList<ObserverHook>();

    /**
     * Adds further observer hooks to the collection.
     *
     * @param observerHooks the hooks to add.
     */
    public ObserverHooks(final ObserverHook... observerHooks) {
        this.elements.addAll(Arrays.asList(observerHooks));
    }

    /**
     * Tests if the given observer hook is part of the collection.
     *
     * @param observerHook the hook to check.
     * @return true if given hook is part of the collection, false otherwise.
     */
    public final boolean contains(final ObserverHook observerHook) {
        return this.elements.contains(observerHook);
    }

    @Override
    public final String toString() {
        final StringBuilder returnString = new StringBuilder();
        for (final ObserverHook hook : elements) {
            returnString.append(hook);
            returnString.append(" ");
        }
        return returnString.toString().trim();
    }

}
