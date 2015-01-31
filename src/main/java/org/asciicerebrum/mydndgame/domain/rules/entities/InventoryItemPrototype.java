package org.asciicerebrum.mydndgame.domain.rules.entities;

import org.asciicerebrum.mydndgame.domain.rules.composition.BodySlotTypes;
import org.asciicerebrum.mydndgame.domain.rules.entities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.rules.entities.SpecialAbilities;
import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.core.particles.Weight;

/**
 *
 * @author species8472
 */
public abstract class InventoryItemPrototype extends GameEntity {

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
    private SpecialAbilities specialAbilities;

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

}
