package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.ISpecialAbility;
import org.springframework.context.ApplicationContext;

/**
 * Builder for a unique weapon from the prototype.
 *
 * @author species8472
 */
public class WeaponBuilder {

    /**
     * Setup information for the weapon.
     */
    private final WeaponSetup setup;
    /**
     * Spring context to get the beans from.
     */
    private final ApplicationContext context;

    /**
     * Constructor for creating a weapon builder.
     *
     * @param setupInput the weapon setup object.
     * @param contextInput the spring application context.
     */
    public WeaponBuilder(final WeaponSetup setupInput,
            final ApplicationContext contextInput) {
        this.setup = setupInput;
        this.context = contextInput;
    }

    /**
     * Central method on building the weapon.
     *
     * @return the created unique weapon.
     */
    public final Weapon build() {
        Weapon weapon = this.context.getBean(
                setup.getName(), Weapon.class);

        weapon.setId(setup.getId());

        SizeCategory size = this.context.getBean(
                setup.getSizeCategory(), SizeCategory.class);

        weapon.adaptToSize(size);

        //TODO move this (partially) to Inventory item build process.
        // adding special abilities
        for (String specialAbilityKey : setup.getSpecialAbilities()) {
            ISpecialAbility specAb = this.context.getBean(
                    specialAbilityKey, WeaponSpecialAbility.class);
            weapon.getSpecialAbilities().add(specAb);

            // registering special abillity hooks
            for (IObserver observer : specAb.getObservers()) {
                weapon.getObservableDelegate().registerListener(
                        observer.getHook(), observer,
                        weapon.getObserverMap());
            }
        }

        //TODO if not given, set default values for criticalFactor,
        // criticalMinimumLevel.
        // to achive this, use a defaultValuesProvider bean and define
        // those default values in the application context xml.
        return weapon;
    }
}
