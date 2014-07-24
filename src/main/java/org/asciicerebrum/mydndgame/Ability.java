package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusSource;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IAbility;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;

/**
 *
 * @author species8472
 */
public class Ability implements IAbility, BonusSource, BonusTarget {

    /**
     * Unique id of the ability.
     */
    private String id;
    /**
     * Descriptive name of the ability.
     */
    private String name;
    /**
     * Collection of all boni associated with this ability.
     */
    private List<IBonus> boni = new ArrayList<IBonus>();

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param nameInput the name to set
     */
    public final void setName(final String nameInput) {
        this.name = nameInput;
    }

    /**
     * @return the boni
     */
    public final List<IBonus> getBoni() {
        return boni;
    }

    /**
     * @param boniInput the boni to set
     */
    public final void setBoni(final List<IBonus> boniInput) {
        this.boni = boniInput;
    }

}
