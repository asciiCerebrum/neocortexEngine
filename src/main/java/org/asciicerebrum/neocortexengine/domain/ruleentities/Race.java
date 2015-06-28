package org.asciicerebrum.neocortexengine.domain.ruleentities;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public class Race implements BonusSource, ObserverSource {

    /**
     * The unique id of this race.
     */
    private UniqueId uniqueId;

    /**
     * The sizeCategory which is associated with this race.
     */
    private SizeCategory size;

    /**
     * All the body slots this race is providing. The structure of body slots
     * has to be wrapped into personalized body slots when a new character is
     * created and to be put into the dnd character object.
     */
    private BodySlots bodySlotBluePrint;

    /**
     * @return the size category.
     */
    public final SizeCategory getSize() {
        return size;
    }

    /**
     * @param sizeInput the size category.
     */
    public final void setSize(final SizeCategory sizeInput) {
        this.size = sizeInput;
    }

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

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni contextBoni = new ContextBoni();

        contextBoni.add(this.size.getBoni(context, resolver));

        return contextBoni;
    }

    /**
     * @param bodySlotBluePrintInput the bodySlotBluePrint to set
     */
    public final void setBodySlotBluePrint(
            final BodySlots bodySlotBluePrintInput) {
        this.bodySlotBluePrint = bodySlotBluePrintInput;
    }

    /**
     * @return the bodySlotBluePrint
     */
    public final BodySlots getBodySlotBluePrint() {
        return bodySlotBluePrint;
    }

}
