package org.asciicerebrum.mydndgame.domain.core.attribution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.gameentities.EntityFactory;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.EntitySetup;

/**
 *
 * @author species8472
 */
public class Reassignments {

    public static class ReassignmentEntry {

        private EntitySetup setup;

        private Object entity;

        private EntityFactory factory;

        /**
         * @return the setup
         */
        public EntitySetup getSetup() {
            return setup;
        }

        /**
         * @param setup the setup to set
         */
        public void setSetup(EntitySetup setup) {
            this.setup = setup;
        }

        /**
         * @return the entity
         */
        public Object getEntity() {
            return entity;
        }

        /**
         * @param entity the entity to set
         */
        public void setEntity(Object entity) {
            this.entity = entity;
        }

        /**
         * @return the factory
         */
        public EntityFactory getFactory() {
            return factory;
        }

        /**
         * @param factory the factory to set
         */
        public void setFactory(EntityFactory factory) {
            this.factory = factory;
        }

    }

    private List<ReassignmentEntry> reassignments
            = new ArrayList<ReassignmentEntry>();

    public final void addEntry(final EntityFactory factory,
            final EntitySetup setup, final Object entity) {

        ReassignmentEntry entry = new ReassignmentEntry();
        entry.setEntity(entity);
        entry.setFactory(factory);
        entry.setSetup(setup);

        this.reassignments.add(entry);
    }

    public Iterator<ReassignmentEntry> getIterator() {
        return this.reassignments.iterator();
    }
}
