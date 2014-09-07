package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
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

        IArmor armor = (IArmor) super.build(this.context.getBean(
                setup.getName(), Armor.class));

        return armor;
    }
}
