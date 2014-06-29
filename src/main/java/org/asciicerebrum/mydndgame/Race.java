package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class Race {

    /**
     * The unique id of this race.
     */
    private String id;

    /**
     * The sizeCategory which is associated with this race.
     */
    @BonusGranter
    private SizeCategory size;

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
     * @return the size
     */
    public final SizeCategory getSize() {
        return size;
    }

    /**
     * @param sizeInput the size to set
     */
    public final void setSize(final SizeCategory sizeInput) {
        this.size = sizeInput;
    }

}
