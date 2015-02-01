package org.asciicerebrum.mydndgame.observers.impl;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.DoubleParticle;
import org.asciicerebrum.mydndgame.domain.core.particles.DoubleParticle.Operation;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;

/**
 *
 * @author species8472
 */
public class BonusValueModificationObserver extends AbstractObserver {

    /**
     * Defines how to arithmetically modify the value.
     */
    private Operation operation;

    /**
     * The value of modification.
     */
    private DoubleParticle modValue;

    /**
     * The bonus resembling the one to modify.
     */
    private Bonus referenceBonus;

    /**
     * {@inheritDoc} If a bonus resembling the reference bonus is encountered in
     * the list, its value is modified. If the value was based on a dynamic
     * value provider, it is replaced by a static value.
     */
    @Override
    protected final Object triggerCallback(final Object object,
            final DndCharacter dndCharacter) {

        Boni boni = (Boni) object;

        if (this.referenceBonus == null) {
            return boni;
        }

        final Iterator<Bonus> bonusIterator = boni.iterator();
        while (bonusIterator.hasNext()) {
            final Bonus bonus = bonusIterator.next();
            final BonusValueTuple bonusEffectiveValue
                    = bonus.getEffectiveValues(dndCharacter);

            // it is enough to check for bonus type and target
            // keep in mind that the effectValue might be null
            // --> the bonus does not exist --> continue!
            if (this.referenceBonus.resembles(bonus,
                    Bonus.ResemblanceFacet.BONUS_TYPE,
                    Bonus.ResemblanceFacet.TARGET)) {

                bonusEffectiveValue.applyOperation(
                        this.operation, this.modValue);

                bonus.setValues(bonusEffectiveValue);
                bonus.setDynamicValueProvider(null);
            }
        }

        return boni;
    }

    /**
     * @param operationInput the operation to set
     */
    public final void setOperation(final Operation operationInput) {
        this.operation = operationInput;
    }

    /**
     * @param modValueInput the modValue to set
     */
    public final void setModValue(final DoubleParticle modValueInput) {
        this.modValue = modValueInput;
    }

    /**
     * @param referenceBonusInput the referenceBonus to set
     */
    public final void setReferenceBonus(final Bonus referenceBonusInput) {
        this.referenceBonus = referenceBonusInput;
    }

}
