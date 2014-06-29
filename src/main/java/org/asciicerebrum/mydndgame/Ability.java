package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class Ability implements BonusSource, BonusTarget {

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
    private List<Bonus> boni = new ArrayList<Bonus>();

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
    public final List<Bonus> getBoni() {
        return boni;
    }

    /**
     * @param boniInput the boni to set
     */
    public final void setBoni(final List<Bonus> boniInput) {
        this.boni = boniInput;
    }

}
