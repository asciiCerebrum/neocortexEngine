package org.asciicerebrum.mydndgame.valueproviders;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusValueProvider;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmorCategory;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;

/**
 *
 * @author species8472
 */
public class ArmorCheckPenaltyProvider implements BonusValueProvider {

    /**
     * The armor category in question.
     */
    private IArmorCategory armorCategory;

    /**
     * {@inheritDoc} Collects the armor of the given category and returns the
     * minimum check penalty. The lowest value counts!
     */
    @Override
    public final Long getDynamicValue(final ISituationContext context) {
        ICharacter dndCharacter = context.getCharacter();

        Long checkPenalty = 0L;

        if (this.getArmorCategory() == null) {
            return checkPenalty;
        }

        for (IArmor armor : dndCharacter.getWieldedArmor()) {
            if (armor.getArmorCategory() == null
                    || !armor.getArmorCategory()
                    .equals(this.getArmorCategory())) {
                continue;
            }
            if (armor.getArmorCheckPenalty() != null
                    && armor.getArmorCheckPenalty() < checkPenalty) {
                checkPenalty = armor.getArmorCheckPenalty();
            }
        }

        return checkPenalty;
    }

    /**
     * @return the armorCategory
     */
    public final IArmorCategory getArmorCategory() {
        return armorCategory;
    }

    /**
     * @param armorCategoryInput the armorCategory to set
     */
    public final void setArmorCategory(
            final IArmorCategory armorCategoryInput) {
        this.armorCategory = armorCategoryInput;
    }

}
