package org.asciicerebrum.mydndgame.mechanics.observertriggers;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.LongParticle;
import org.asciicerebrum.mydndgame.domain.core.particles.LongParticle.Operation;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.ObserverTriggerStrategy;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.DynamicValueProvider;

/**
 *
 * @author species8472
 */
public class SimpleArithmeticObserverTrigger implements ObserverTriggerStrategy {

    /**
     * Numeric modificatioin of the base value called numeric.
     */
    private LongParticle modValue;

    /**
     * Not a static but a dynamic mod value. The dynamic value has priority over
     * the static one.
     */
    private DynamicValueProvider modValueProvider;

    /**
     * How to modificate the base value.
     */
    private Operation operation;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object trigger(final Object object,
            final DndCharacter dndCharacter, final UniqueEntity contextItem) {

        final LongParticle numeric = (LongParticle) object;

        LongParticle effectiveModValue = this.modValue;
        if (this.modValueProvider != null) {
            effectiveModValue = this.modValueProvider
                    .getDynamicValue(dndCharacter);
        }

        if (effectiveModValue == null) {
            return numeric;
        }

        numeric.applyOperation(this.operation, effectiveModValue);
        return numeric;
    }

    /**
     * @param modValueInput the modValue to set
     */
    public final void setModValue(final LongParticle modValueInput) {
        this.modValue = modValueInput;
    }

    /**
     * @param operationInput the operation to set
     */
    public final void setOperation(final Operation operationInput) {
        this.operation = operationInput;
    }

    /**
     * @param modValueProviderInput the modValueProvider to set
     */
    public final void setModValueProvider(
            final DynamicValueProvider modValueProviderInput) {
        this.modValueProvider = modValueProviderInput;
    }

}
