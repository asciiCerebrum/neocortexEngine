package org.asciicerebrum.mydndgame.domain.setup;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlotSetup extends AbstractEntitySetup {

    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.BODY_SLOT_TYPE, SetupProperty.BODY_SLOT_ITEM};

    public final void setItem(final String itemId) {
        this.singleProperties.put(SetupProperty.BODY_SLOT_ITEM, itemId);
    }

    public final void setBodySlotType(final String bodySlotTypeId) {
        this.singleProperties.put(SetupProperty.BODY_SLOT_TYPE, bodySlotTypeId);
    }

    public final void setIsPrimaryAttackSlot(final String primaryAttackSlot) {
        this.singleProperties.put(SetupProperty.BODY_SLOT_PRIMARY_ATTACK,
                primaryAttackSlot);
    }

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }
}
