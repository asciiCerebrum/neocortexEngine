package org.asciicerebrum.mydndgame.domain.factories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;

/**
 *
 * @author species8472
 */
public class Reassignments {

    /**
     * Holds a single item to resolve along with the necessary parameters.
     */
    public static class ReassignmentEntry {

        /**
         * The setup of the item to resolve.
         */
        private EntitySetup setup;

        /**
         * The entity instance to resolve.
         */
        private Object entity;

        /**
         * The factory of the item to resolve.
         */
        private EntityFactory factory;

        /**
         * @return the setup
         */
        public final EntitySetup getSetup() {
            return setup;
        }

        /**
         * @param setupInput the setup to set
         */
        public final void setSetup(final EntitySetup setupInput) {
            this.setup = setupInput;
        }

        /**
         * @return the entity
         */
        public final Object getEntity() {
            return entity;
        }

        /**
         * @param entityInput the entity to set
         */
        public final void setEntity(final Object entityInput) {
            this.entity = entityInput;
        }

        /**
         * @return the factory
         */
        public final EntityFactory getFactory() {
            return factory;
        }

        /**
         * @param factoryInput the factory to set
         */
        public final void setFactory(final EntityFactory factoryInput) {
            this.factory = factoryInput;
        }

    }

    /**
     * List of reassignment entries to resolve later on.
     */
    private final List<ReassignmentEntry> reassignments
            = new ArrayList<ReassignmentEntry>();

    /**
     * Adds an entry to the list of to-resolve-later items.
     *
     * @param factory the factory of the item to resolve.
     * @param setup the setup of the item to resolve.
     * @param entity the instance of the item that has to be resolved.
     */
    public final void addEntry(final EntityFactory factory,
            final EntitySetup setup, final Object entity) {

        ReassignmentEntry entry = new ReassignmentEntry();
        entry.setEntity(entity);
        entry.setFactory(factory);
        entry.setSetup(setup);

        this.reassignments.add(entry);
    }

    /**
     * Iterator over the list of unresolved entries.
     *
     * @return the iterator over the list.
     */
    public final Iterator<ReassignmentEntry> getIterator() {
        return this.reassignments.iterator();
    }
}
