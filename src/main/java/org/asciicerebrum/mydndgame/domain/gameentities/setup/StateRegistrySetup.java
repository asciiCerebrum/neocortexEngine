package org.asciicerebrum.mydndgame.domain.gameentities.setup;

import org.asciicerebrum.mydndgame.domain.gameentities.StateRegistry;

/**
 *
 * @author species8472
 */
public class StateRegistrySetup extends AbstractEntitySetup<StateRegistry> {

    public static class StateRegistryEntrySetup extends AbstractEntitySetup {

        private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
                = {SetupProperty.STATE_REGISTRY_PARTICLE,
                    SetupProperty.STATE_REGISTRY_VALUE,
                    SetupProperty.STATE_REGISTRY_VALUE_TYPE};

        @Override
        public boolean isSetupComplete() {
            return this.checkRequiredSingleProperties(
                    REQUIRED_SINGLE_PROPERTIES);
        }

        public void setRegistryParticle(final String particle) {
            this.singleSetup.put(SetupProperty.STATE_REGISTRY_PARTICLE,
                    particle);
        }

        public void setContextObjectId(final String contextObjectId) {
            this.singleSetup.put(SetupProperty.STATE_REGISTRY_CONTEXT_OBJECT_ID,
                    contextObjectId);
        }

        public void setRegistryValue(final String value) {
            this.singleSetup.put(SetupProperty.STATE_REGISTRY_VALUE, value);
        }

        public void setRegistryValueType(final String valueType) {
            this.singleSetup.put(SetupProperty.STATE_REGISTRY_VALUE_TYPE,
                    valueType);
        }

    }

    @Override
    public boolean isSetupComplete() {
        return true;
    }

    public void setStateRegistryEntry(final EntitySetup entrySetup) {
        this.singleSetup.put(SetupProperty.STATE_REGISTRY_ENTRY, entrySetup);
    }

}
