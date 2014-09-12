package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.ISpecialAbility;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.springframework.context.ApplicationContext;

/**
 * Builder for a unique weapon from the prototype.
 *
 * @author species8472
 */
public class WeaponBuilder extends InventoryItemBuilder {

    /**
     * Constructor for creating a weapon builder.
     *
     * @param setupInput the weapon setup object.
     * @param contextInput the spring application context.
     */
    public WeaponBuilder(final WeaponSetup setupInput,
            final ApplicationContext contextInput) {
        super(setupInput, contextInput);
    }

    /**
     * Central method on building the weapon.
     *
     * @return the created unique weapon.
     */
    public final IWeapon build() {

        IWeapon weapon = (IWeapon) super.build(this.context.getBean(
                setup.getName(), Weapon.class));

        //TODO if not given, set default values for criticalFactor,
        // criticalMinimumLevel.
        // to achive this, use a defaultValuesProvider bean and define
        // those default values in the application context xml.
        return weapon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final ISpecialAbility getSpecialAbilityFromContext(
            final String specialAbilityKey) {

        return this.context.getBean(
                specialAbilityKey, WeaponSpecialAbility.class);
    }
}
