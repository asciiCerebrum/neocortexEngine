package org.asciicerebrum.mydndgame.domain.setup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class StateRegistrySetup extends AbstractEntitySetup {

    /**
     * Subclass for the state registry entry setup.
     */
    public static class StateRegistryEntrySetup extends AbstractEntitySetup {

        /**
         * Required properties.
         */
        private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
                = {SetupProperty.STATE_REGISTRY_PARTICLE,
                    SetupProperty.STATE_REGISTRY_VALUE,
                    SetupProperty.STATE_REGISTRY_VALUE_TYPE};

        @Override
        public final boolean isSetupComplete() {
            return this.checkRequiredSingleProperties(
                    REQUIRED_SINGLE_PROPERTIES);
        }

        /**
         * @param particle the particle.
         */
        public final void setRegistryParticle(final String particle) {
            this.getSingleProperties()
                    .put(SetupProperty.STATE_REGISTRY_PARTICLE, particle);
        }

        /**
         * @param contextObjectId the context object id.
         */
        public final void setContextObjectId(final String contextObjectId) {
            this.getSingleProperties().put(
                    SetupProperty.STATE_REGISTRY_CONTEXT_OBJECT_ID,
                    contextObjectId);
        }

        /**
         * @param value the value.
         */
        public final void setRegistryValue(final String value) {
            this.getSingleProperties().put(SetupProperty.STATE_REGISTRY_VALUE,
                    value);
        }

        /**
         * @param valueType the value type.
         */
        public final void setRegistryValueType(final String valueType) {
            this.getSingleProperties().put(
                    SetupProperty.STATE_REGISTRY_VALUE_TYPE, valueType);
        }

    }

    @Override
    public final boolean isSetupComplete() {
        return true;
    }

    /**
     * @param entrySetup the entry setup.
     */
    public final void addStateRegistryEntry(final EntitySetup entrySetup) {
        List<EntitySetup> stateRegistryEntries
                = this.getListSetup().get(SetupProperty.STATE_REGISTRY_ENTRY);
        if (stateRegistryEntries == null) {
            stateRegistryEntries = new ArrayList<EntitySetup>();
            this.getListSetup().put(SetupProperty.STATE_REGISTRY_ENTRY,
                    stateRegistryEntries);
        }
        stateRegistryEntries.add(entrySetup);
    }

}
