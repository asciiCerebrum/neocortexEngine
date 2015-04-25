package org.asciicerebrum.mydndgame.mechanics.observertriggers;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.DoubleParticle;
import org.asciicerebrum.mydndgame.domain.core.particles.DoubleParticle.Operation;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBonus;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.ObserverTriggerStrategy;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class BonusValueModificationObserverTrigger
        implements ObserverTriggerStrategy {

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            BonusValueModificationObserverTrigger.class);

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
            final ICharacter dndCharacter, final UniqueEntity contextItem) {

        final ContextBoni ctxBoni = (ContextBoni) object;

        if (this.getReferenceBonus() == null) {
            return ctxBoni;
        }

        LOG.debug("Entering bonus value modification with {} context boni.",
                ctxBoni.size());

        final Boni boniToAdd = new Boni();

        final Iterator<ContextBonus> bonusIterator = ctxBoni.iterator();
        while (bonusIterator.hasNext()) {
            final ContextBonus ctxBonus = bonusIterator.next();
            final BonusValueTuple bonusEffectiveValue
                    = this.getBonusService().getEffectiveValues(ctxBonus,
                            (DndCharacter) dndCharacter);

            // it is enough to check for bonus type and target
            // keep in mind that the effectValue might be null
            // --> the bonus does not exist --> continue!
            if (this.getReferenceBonus().resembles(ctxBonus.getBonus(),
                    Bonus.ResemblanceFacet.BONUS_TYPE,
                    Bonus.ResemblanceFacet.TARGET)) {

                LOG.debug("Modifying bonus value {} with {} and {}.",
                        new Object[]{bonusEffectiveValue
                            .getBonusValueByRank(BonusRank.RANK_0)
                            .getValue(),
                            this.getOperation().toString(),
                            this.getModValue().getValue()});

                bonusEffectiveValue.applyOperation(this.getOperation(),
                        this.getModValue());

                LOG.debug("Modifying to result: {}.",
                        bonusEffectiveValue
                        .getBonusValueByRank(BonusRank.RANK_0)
                        .getValue());

                // creating a new bonus in order not to overwrite the ones
                // given from the ioc container.
                final Bonus bonus = new Bonus();
                bonus.setBonusType(ctxBonus.getBonus().getBonusType());
                bonus.setTarget(ctxBonus.getBonus().getTarget());
                bonus.setValues(bonusEffectiveValue);

                bonusIterator.remove();
                boniToAdd.addBonus(bonus);
            }
        }

        ctxBoni.add(boniToAdd, contextItem);
        return ctxBoni;
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

    /**
     * @return the operation
     */
    public final Operation getOperation() {
        return operation;
    }

    /**
     * @return the modValue
     */
    public final DoubleParticle getModValue() {
        return modValue;
    }

    /**
     * @return the referenceBonus
     */
    public final Bonus getReferenceBonus() {
        return referenceBonus;
    }

    /**
     * @return the bonusService
     */
    public final BonusCalculationService getBonusService() {
        return bonusService;
    }

}
