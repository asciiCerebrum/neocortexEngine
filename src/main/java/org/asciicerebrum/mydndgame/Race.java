package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class Race {

    private String id;
    
    @BonusGranter
    private SizeCategory size;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * @return the size
     */
    public SizeCategory getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(SizeCategory size) {
        this.size = size;
    }

}
