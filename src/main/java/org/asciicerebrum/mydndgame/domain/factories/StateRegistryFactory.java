package org.asciicerebrum.mydndgame.domain.factories;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry.StateParticle;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry.StateValueType;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class StateRegistryFactory implements EntityFactory<StateRegistry> {

    @Override
    public final StateRegistry newEntity(final EntitySetup setup) {

        StateRegistry stateReg = new StateRegistry();

        if (setup == null) {
            return stateReg;
        }

        final List<EntitySetup> registryEntrySetups
                = setup.getPropertySetups(SetupProperty.STATE_REGISTRY_ENTRY);
        if (registryEntrySetups != null) {
            for (EntitySetup entrySetup : registryEntrySetups) {
                this.addSingleState(stateReg, entrySetup);
            }
        }

        return stateReg;
    }

    /**
     * Builds up a single state for the state registry.
     *
     * @param stateReg the state registry to put the state in.
     * @param entrySetup the setup for the registry entry.
     */
    final void addSingleState(final StateRegistry stateReg,
            final EntitySetup entrySetup) {

        if (!entrySetup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the state "
                    + " registry entry is not complete.");
        }

        final StateParticle particle
                = StateParticle.valueOf(entrySetup.getProperty(
                                SetupProperty.STATE_REGISTRY_PARTICLE));

        final String contextObjectId = entrySetup.getProperty(
                SetupProperty.STATE_REGISTRY_CONTEXT_OBJECT_ID);

        final StateValueType stateValueType
                = StateValueType.valueOf(entrySetup.getProperty(
                                SetupProperty.STATE_REGISTRY_VALUE_TYPE));
        final String stateValueString
                = entrySetup.getProperty(
                        SetupProperty.STATE_REGISTRY_VALUE);

        UniqueId uniqueId = null;
        if (StringUtils.isNotBlank(contextObjectId)) {
            uniqueId = new UniqueId(contextObjectId);
        }

        stateReg.putState(particle, uniqueId,
                stateValueType.parseToType(stateValueString,
                        ApplicationContextProvider.getApplicationContext()));
    }

}
