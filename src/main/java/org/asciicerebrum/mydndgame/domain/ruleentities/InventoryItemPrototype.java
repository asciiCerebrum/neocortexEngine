package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.core.particles.Weight;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;

/**
 *
 * @author species8472
 */
public abstract class InventoryItemPrototype extends GameEntity
        implements BonusSource, ObserverSource {

    /**
     * Weight of the object.
     */
    private Weight baseWeight;

    /**
     * Base price without modifications (like mwk, magic, etc.) always in gp of
     * the object.
     */
    private Cost baseCost;

    /**
     * Size category of the object. Based on the prototype, which is mostly
     * always medium size.
     */
    private SizeCategory baseSize;

    /**
     * For which kinds of body slots this weapon is intended.
     */
    private BodySlotTypes designatedBodySlotTypes;

    /**
     * Collection of special abilities of this item. E.g. mwk, magical, etc.
     */
    private SpecialAbilities specialAbilities = new SpecialAbilities();

    /**
     * @return the baseWeight
     */
    public final Weight getBaseWeight() {
        return baseWeight;
    }

    /**
     * @param baseWeightInput the baseWeight to set
     */
    public final void setBaseWeight(final Weight baseWeightInput) {
        this.baseWeight = baseWeightInput;
    }

    /**
     * @return the baseCost
     */
    public final Cost getBaseCost() {
        return baseCost;
    }

    /**
     * @param baseCostInput the baseCost to set
     */
    public final void setBaseCost(final Cost baseCostInput) {
        this.baseCost = baseCostInput;
    }

    /**
     * @return the baseSize
     */
    public final SizeCategory getBaseSize() {
        return baseSize;
    }

    /**
     * @param baseSizeInput the baseSize to set
     */
    public final void setBaseSize(final SizeCategory baseSizeInput) {
        this.baseSize = baseSizeInput;
    }

    /**
     * @return the specialAbilities
     */
    public final SpecialAbilities getSpecialAbilities() {
        return specialAbilities;
    }

    /**
     * @param specialAbilitiesInput the specialAbilities to set
     */
    public final void setSpecialAbilities(
            final SpecialAbilities specialAbilitiesInput) {
        this.specialAbilities = specialAbilitiesInput;
    }

    /**
     * @return the designatedBodySlotTypes
     */
    public final BodySlotTypes getDesignatedBodySlotTypes() {
        return designatedBodySlotTypes;
    }

    /**
     * @param designatedBodySlotTypesInput the designatedBodySlotTypes to set
     */
    public final void setDesignatedBodySlotTypes(
            final BodySlotTypes designatedBodySlotTypesInput) {
        this.designatedBodySlotTypes = designatedBodySlotTypesInput;
    }

    /**
     * Helper method for the child classes to call when invoking getBoni.
     *
     * @param context the context in order to contextualise context free boni
     * collections.
     * @param resolver the service for translating the uniqueId to the
     * corersponding entity.
     * @return the collection of boni in their specific context.
     */
    public final ContextBoni getInventoryItemBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni ctxBoni = new ContextBoni();

        ctxBoni.add(this.getSpecialAbilities().getBoni(context, resolver));

        return ctxBoni;
    }

}
