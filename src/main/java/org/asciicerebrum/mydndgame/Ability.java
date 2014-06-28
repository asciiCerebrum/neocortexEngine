package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class Ability implements BonusSource, BonusTarget {

    private String id;
    private String name;
    private List<Bonus> boni = new ArrayList<Bonus>();

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
