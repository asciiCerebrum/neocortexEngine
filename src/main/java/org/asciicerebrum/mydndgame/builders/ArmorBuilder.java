package org.asciicerebrum.mydndgame.builders;

import org.asciicerebrum.mydndgame.Armor;
import org.asciicerebrum.mydndgame.ArmorSetup;
import org.asciicerebrum.mydndgame.ArmorSpecialAbility;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
import org.asciicerebrum.mydndgame.interfaces.entities.ISpecialAbility;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class ArmorBuilder extends InventoryItemBuilder {

    /**
     * Constructor for creating an armor builder.
     *
     * @param setupInput the armor setup object.
     * @param contextInput the spring application context.
     */
    public ArmorBuilder(final ArmorSetup setupInput,
            final ApplicationContext contextInput) {
        super(setupInput, contextInput);
    }

    /**
     * Central method on building the armor.
     *
     * @return the created unique armor.
     */
    public final IArmor build() {

        return (IArmor) super.build(this.getContext().getBean(
                this.getSetup().getName(), Armor.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final ISpecialAbility getSpecialAbilityFromContext(
            final String specialAbilityKey) {

        return this.getContext().getBean(
                specialAbilityKey, ArmorSpecialAbility.class);
    }
}
