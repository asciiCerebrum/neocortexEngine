package org.asciicerebrum.neocortexengine.domain.ruleentities;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.Boni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;

/**
 *
 * @author species8472
 */
public abstract class Feature implements BonusSource, ObserverSource {

    /**
     * The unique identifier of the feature.
     */
    private UniqueId uniqueId;

    /**
     * The list of quasi static boni that are granted by this feature.
     */
    private Boni boni = new Boni();

    /**
     * The list of observers that hook into the entity to modify certain
     * attributes dynamically.
     */
    private Observers observers = new Observers();

    /**
     * @return the uniqueId
     */
    public final UniqueId getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueIdInput the uniqueId to set
     */
    public final void setUniqueId(final UniqueId uniqueIdInput) {
        this.uniqueId = uniqueIdInput;
    }

    /**
     * Helper function to let the children override the getBoni method by
     * calling this one if they have nothing to add for themselves.
     *
     * @param context the context in order to contextualise context free boni
     * collections.
     * @return the collection of boni in their specific context.
     */
    public final ContextBoni getFeatureBoni(final UniqueEntity context) {
        return new ContextBoni(this.boni, context);
    }

    /**
     * @param boniInput the boni to set
     */
    public final void setBoni(final Boni boniInput) {
        this.boni = boniInput;
    }

    /**
     * @return the observers
     */
    public final Observers getObservers() {
        return observers;
    }

    /**
     * @param observersInput the observers to set
     */
    public final void setObservers(final Observers observersInput) {
        this.observers = observersInput;
    }

}
