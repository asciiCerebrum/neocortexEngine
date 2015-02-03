package org.asciicerebrum.mydndgame.mechanics.observertriggers;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.DoubleParticle;
import org.asciicerebrum.mydndgame.domain.core.particles.DoubleParticle.Operation;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.core.ObserverTriggerStrategy;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;

/**
 *
 * @author species8472
 */
public class BonusValueModificationObserverTrigger implements ObserverTriggerStrategy {

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
     * The service around the boni.
     */
    private BonusCalculationService bonusService;

    /**
     * {@inheritDoc} If a bonus resembling the reference bonus is encountered in
     * the list, its value is modified. If the value was based on a dynamic
     * value provider, it is replaced by a static value.
     */
    @Override
    public final Object trigger(final Object object,
            final DndCharacter dndCharacter, final UniqueEntity contextItem) {

        Boni boni = (Boni) object;

        if (this.referenceBonus == null) {
            return boni;
        }

        final Iterator<Bonus> bonusIterator = boni.iterator();
        while (bonusIterator.hasNext()) {
            final Bonus bonus = bonusIterator.next();
            final BonusValueTuple bonusEffectiveValue
                    = this.bonusService.getEffectiveValues(bonus, contextItem,
                            dndCharacter);

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

    /**
     * @param bonusServiceInput the bonusService to set
     */
    public final void setBonusService(
            final BonusCalculationService bonusServiceInput) {
        this.bonusService = bonusServiceInput;
    }

}
