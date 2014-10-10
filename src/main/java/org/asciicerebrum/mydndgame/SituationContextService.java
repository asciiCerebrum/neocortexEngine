package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IDamageType;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;
import org.asciicerebrum.mydndgame.interfaces.entities.StateRegistryKey;

/**
 *
 * @author species8472
 */
public class SituationContextService implements ISituationContext {

    /**
     * The character to retrieve situation contextual information for.
     */
    private ICharacter character;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setCharacter(final ICharacter characterInput) {
        this.character = characterInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IBodySlotType getBodySlotType() {
        return this.character.getSetup().getStateRegistryBeanForKey(
                StateRegistryKey.ACTIVE_BODY_SLOT_TYPE.toString(),
                BodySlotType.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IWeaponCategory getAttackMode() {
        return this.character.getSetup().getStateRegistryBeanForKey(
                StateRegistryKey.ACTIVE_ATTACK_MODE.toString(),
                WeaponCategory.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IDamageType getDamageType() {
        IBodySlotType bsType = this.getBodySlotType();
        IDamageType damageType = null;
        IInventoryItem item = null;
        if (bsType != null) {
            item = this.character.getBodySlotByType(bsType).getItem();
        }
        if (item != null && item instanceof IWeapon) {

            damageType = this.character.getSetup()
                    .getStateRegistryBeanForKey(
                            StateRegistryKey.WEAPON_DAMAGE_TYPE_PREFIX
                            .toString() + item.getId(),
                            DamageType.class);
            //TODO check if weapon supports this damage type - if not fall back
            // to default!
            if (damageType == null) {
                damageType = ((IWeapon) item).getDefaultDamageType();
            }
        }
        return damageType;
    }

}
