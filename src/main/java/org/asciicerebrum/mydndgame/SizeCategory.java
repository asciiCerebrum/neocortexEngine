package org.asciicerebrum.mydndgame;

import java.util.List;

/**
 *
 * @author species8472
 */
public class SizeCategory implements BonusSource {

    /**
     * The unique if of this size category. E.g. small, medium, etc.
     */
    private String id;
    /**
     * The list of boni associated with this size category.
     */
    private List<Bonus> boni;

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
