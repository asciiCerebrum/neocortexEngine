package org.asciicerebrum.mydndgame;

import java.util.List;

/**
 *
 * @author species8472
 */
public class SizeCategory implements BonusSource {

    private String id;
    private List<Bonus> boni;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the boni
     */
    public List<Bonus> getBoni() {
        return boni;
    }

    /**
     * @param boni the boni to set
     */
    public void setBoni(List<Bonus> boni) {
        this.boni = boni;
    }

}
