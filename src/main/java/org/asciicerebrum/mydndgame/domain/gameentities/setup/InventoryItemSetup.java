package org.asciicerebrum.mydndgame.domain.gameentities.setup;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.gameentities.InventoryItem;

/**
 *
 * @author species8472
 */
public abstract class InventoryItemSetup
        extends AbstractEntitySetup<InventoryItem> {

    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.NAME, SetupProperty.SIZE_CATEGORY,
                SetupProperty.UNIQUEID};

    @Override
    public boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    /**
     * @param nameInput the name to set
     */
    public final void setName(final String nameInput) {
        this.singleProperties.put(SetupProperty.NAME, nameInput);
    }

    /**
     * @param sizeCategoryInput the sizeCategory to set
     */
    public final void setSizeCategory(final String sizeCategoryInput) {
        this.singleProperties.put(SetupProperty.SIZE_CATEGORY,
                sizeCategoryInput);
    }

    /**
     * @param specialAbilityInput the itemFeature to add
     */
    public final void addSpecialAbility(
            final String specialAbilityInput) {
        List<String> specialAbilities
                = this.listProperties.get(SetupProperty.SPECIAL_ABILITIES);
        if (specialAbilities == null) {
            specialAbilities = new ArrayList<String>();
            this.listProperties.put(SetupProperty.SPECIAL_ABILITIES,
                    specialAbilities);
        }
        specialAbilities.add(specialAbilityInput);
    }
}
