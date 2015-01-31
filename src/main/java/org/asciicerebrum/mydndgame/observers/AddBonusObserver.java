package org.asciicerebrum.mydndgame.observers;

import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusType;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.core.attribution.StateRegistry.StateParticle;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class AddBonusObserver extends AbstractObserver {

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
    protected final Object triggerCallback(final Object object,
            final DndCharacter dndCharacter) {
        Boni boni = (Boni) object;

        if (this.addBonus != null) {
            boni.addBonus(this.addBonus);
            return boni;
        }

        BonusValue addBonusValue = this.situationContextService
                .getBonusValueForKey(this.registryStateKey, dndCharacter);

        if (addBonusValue != null && addBonusValue.isNonZero()) {
            final Bonus altAddBonus = new Bonus();
            altAddBonus.setBonusType(this.bonusType);
            altAddBonus.setTarget(this.bonusTarget);

            final BonusValueTuple bonusValueTuple = new BonusValueTuple();
            bonusValueTuple.addBonusValue(BonusRank.RANK_0, addBonusValue);

            altAddBonus.setValues(bonusValueTuple);

            boni.addBonus(altAddBonus);
        }

        return boni;
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
     * @param registryStateKey the registryStateKey to set
     */
    public final void setRegistryStateKey(
            final StateParticle registryStateKey) {
        this.registryStateKey = registryStateKey;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

}
