package org.asciicerebrum.neocortexengine.domain.setup;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlotSetup extends AbstractEntitySetup {

    /**
     * The required properties. The body slot holder has no setter as it is
     * injected by the dnd character factory.
     */
    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.BODY_SLOT_TYPE, SetupProperty.BODY_SLOT_ITEM,
                SetupProperty.BODY_SLOT_PRIMARY_ATTACK};

    /**
     * @param itemId the item id.
     */
    public final void setItem(final String itemId) {
        this.getSingleProperties().put(SetupProperty.BODY_SLOT_ITEM, itemId);
    }

    /**
     * @param bodySlotTypeId the body slot type.
     */
    public final void setBodySlotType(final String bodySlotTypeId) {
        this.getSingleProperties()
                .put(SetupProperty.BODY_SLOT_TYPE, bodySlotTypeId);
    }

    /**
     * @param primaryAttackSlot whether it is a primary attack slot.
     */
    public final void setIsPrimaryAttackSlot(final String primaryAttackSlot) {
        this.getSingleProperties().put(SetupProperty.BODY_SLOT_PRIMARY_ATTACK,
                primaryAttackSlot);
    }

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }
}
