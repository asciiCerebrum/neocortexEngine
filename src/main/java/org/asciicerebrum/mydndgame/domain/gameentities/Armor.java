package org.asciicerebrum.mydndgame.domain.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.ArmorCategory;
import org.asciicerebrum.mydndgame.domain.core.attribution.Proficiency;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.gameentities.prototypes.ArmorPrototype;
import org.asciicerebrum.mydndgame.facades.gameentities.ArmorServiceFacade;

/**
 *
 * @author species8472
 */
public class Armor extends InventoryItem {

    private ArmorServiceFacade armorServiceFacade;

    public final BonusValue getArmorCheckPenalty(
            final DndCharacter dndCharacter) {
        return this.armorServiceFacade.getArmorCheckPenalty(
                this.getInventoryItemPrototype().getArmorCheckPenalty(), this,
                dndCharacter);
    }

    public final BonusValue getMaxDexBonus(final DndCharacter dndCharacter) {
        return this.armorServiceFacade.getMaxDexBonus(
                this.getInventoryItemPrototype().getMaxDexBonus(), this,
                dndCharacter);
    }

    @Override
    protected final ArmorPrototype getInventoryItemPrototype() {
        return (ArmorPrototype) super.getInventoryItemPrototype();
    }

    /**
     * @param armorServiceFacadeInput the armorServiceFacade to set
     */
    public final void setArmorServiceFacade(
            final ArmorServiceFacade armorServiceFacadeInput) {
        this.armorServiceFacade = armorServiceFacadeInput;
    }

    public final boolean hasProficiency(final Proficiency proficiency) {
        return proficiency.equals(this.getInventoryItemPrototype()
                .getProficiency());
    }

    public final boolean hasArmorCategory(final ArmorCategory armorCategory) {
        return armorCategory.equals(this.getInventoryItemPrototype()
                .getArmorCategory());
    }

}
