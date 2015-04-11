package org.asciicerebrum.mydndgame.mechanics.observertriggers;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.LongParticle;
import org.asciicerebrum.mydndgame.domain.core.particles.LongParticle.Operation;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.ObserverTriggerStrategy;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.DynamicValueProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class SimpleArithmeticObserverTrigger
        implements ObserverTriggerStrategy {

    /**
     * The logger.
     */
    private static final Logger LOG
            = LoggerFactory.getLogger(SimpleArithmeticObserverTrigger.class);

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
            final ICharacter dndCharacter, final UniqueEntity contextItem) {

        final LongParticle numeric = (LongParticle) object;

        LongParticle effectiveModValue = this.modValue;
        if (this.modValueProvider != null) {
            effectiveModValue = this.modValueProvider
                    .getDynamicValue(dndCharacter, contextItem);
        }

        if (effectiveModValue == null) {
            return numeric;
        }

        LOG.debug("Exec of SimpleArithmeticObserverTrigger: {} for {} and {}.",
                new Object[]{this.operation, effectiveModValue.getValue(),
                    numeric.getValue()});

        final LongParticle result = numeric.getNewInstanceOfSameType();

        numeric.applyOperation(this.operation, effectiveModValue, result);
        return result;
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
