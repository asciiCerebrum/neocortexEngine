package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.IDamageType;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;

/**
 *
 * @author species8472
 */
public class SituationContext implements ISituationContext {

    /**
     * The contextual body slot type of the character.
     */
    private IBodySlotType bodySlotType;

    /**
     * The mode in which the attack is executed: melee or ranged.
     */
    private IWeaponCategory attackMode;

    /**
     * Defines, for example, whether the dagger is used in piercing or slashing
     * mode.
     */
    private IDamageType damageType;

    /**
     * {@inheritDoc}
     */
    @Override
    public final IBodySlotType getBodySlotType() {
        return this.bodySlotType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setBodySlotType(final IBodySlotType iBodySlotType) {
        this.bodySlotType = iBodySlotType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IWeaponCategory getAttackMode() {
        return attackMode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setAttackMode(final IWeaponCategory attackModeInput) {
        this.attackMode = attackModeInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IDamageType getDamageType() {
        return damageType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setDamageType(final IDamageType damageTypeInput) {
        this.damageType = damageTypeInput;
    }

}
