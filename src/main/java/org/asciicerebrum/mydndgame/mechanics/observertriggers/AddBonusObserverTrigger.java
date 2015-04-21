package org.asciicerebrum.mydndgame.mechanics.observertriggers;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusType;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry.StateParticle;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.ObserverTriggerStrategy;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class AddBonusObserverTrigger implements ObserverTriggerStrategy {

    /**
     * Type of bonus to add.
     */
    private BonusType bonusType;
    /**
     * Retrieve the value from the character's state registry by this key.
     */
    private StateParticle registryStateKey;
    /**
     * Target of the bonus.
     */
    private BonusTarget bonusTarget;
    /**
     * The bonus to add.
     */
    private Bonus addBonus;
    /**
     * Retrieves the bonus from the registry.
     */
    private SituationContextService situationContextService;

    /**
     * {@inheritDoc} A given bonus has priority over a lookup in the characters
     * state registry.
     */
    @Override
    public final Object trigger(final Object object,
            final ICharacter dndCharacter, final UniqueEntity contextItem) {
        final ContextBoni ctxBoni = (ContextBoni) object;

        if (this.getAddBonus() != null) {
            ctxBoni.add(this.getAddBonus(), contextItem);
            return ctxBoni;
        }

        BonusValue addBonusValue = this.getSituationContextService()
                .getBonusValueForKey(this.getRegistryStateKey(),
                        (DndCharacter) dndCharacter, contextItem.getUniqueId());

        if (addBonusValue.isNonZero()) {
            final Bonus altAddBonus = new Bonus();
            altAddBonus.setBonusType(this.getBonusType());
            altAddBonus.setTarget(this.getBonusTarget());

            final BonusValueTuple bonusValueTuple = new BonusValueTuple();
            bonusValueTuple.addBonusValue(BonusRank.RANK_0, addBonusValue);

            altAddBonus.setValues(bonusValueTuple);

            ctxBoni.add(altAddBonus, contextItem);
        }

        return ctxBoni;
    }

    /**
     * @param bonusTypeInput the bonusType to set
     */
    public final void setBonusType(final BonusType bonusTypeInput) {
        this.bonusType = bonusTypeInput;
    }

    /**
     * @param bonusTargetInput the bonusTarget to set
     */
    public final void setBonusTarget(final BonusTarget bonusTargetInput) {
        this.bonusTarget = bonusTargetInput;
    }

    /**
     * @param addBonusInput the addBonus to set
     */
    public final void setAddBonus(final Bonus addBonusInput) {
        this.addBonus = addBonusInput;
    }

    /**
     * @param registryStateKeyInput the registryStateKey to set
     */
    public final void setRegistryStateKey(
            final StateParticle registryStateKeyInput) {
        this.registryStateKey = registryStateKeyInput;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

    /**
     * @return the bonusType
     */
    public final BonusType getBonusType() {
        return bonusType;
    }

    /**
     * @return the registryStateKey
     */
    public final StateParticle getRegistryStateKey() {
        return registryStateKey;
    }

    /**
     * @return the bonusTarget
     */
    public final BonusTarget getBonusTarget() {
        return bonusTarget;
    }

    /**
     * @return the addBonus
     */
    public final Bonus getAddBonus() {
        return addBonus;
    }

    /**
     * @return the situationContextService
     */
    public final SituationContextService getSituationContextService() {
        return situationContextService;
    }

}
