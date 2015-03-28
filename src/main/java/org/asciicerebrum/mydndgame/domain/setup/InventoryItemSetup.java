package org.asciicerebrum.mydndgame.domain.setup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public abstract class InventoryItemSetup extends AbstractEntitySetup {

    /**
     * The required properties.
     */
    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.NAME, SetupProperty.SIZE_CATEGORY,
                SetupProperty.UNIQUEID};

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    /**
     * @param nameInput the name to set
     */
    public final void setName(final String nameInput) {
        this.getSingleProperties().put(SetupProperty.NAME, nameInput);
    }

    /**
     * @param sizeCategoryInput the sizeCategory to set
     */
    public final void setSizeCategory(final String sizeCategoryInput) {
        this.getSingleProperties().put(SetupProperty.SIZE_CATEGORY,
                sizeCategoryInput);
    }

    /**
     * @param specialAbilityInput the itemFeature to add
     */
    public final void addSpecialAbility(
            final String specialAbilityInput) {
        List<String> specialAbilities
                = this.getListProperties().get(SetupProperty.SPECIAL_ABILITIES);
        if (specialAbilities == null) {
            specialAbilities = new ArrayList<String>();
            this.getListProperties().put(SetupProperty.SPECIAL_ABILITIES,
                    specialAbilities);
        }
        specialAbilities.add(specialAbilityInput);
    }

    /**
     * @param conditionSetups the conditions setups.
     */
    public final void setConditionSetups(
            final List<EntitySetup> conditionSetups) {
        this.getListSetup().put(SetupProperty.CONDITIONS, conditionSetups);
    }

}
