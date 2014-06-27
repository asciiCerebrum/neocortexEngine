package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class BonusType {

    private String id;

    private Boolean doesStack = Boolean.FALSE;

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
     * @return the doesStack
     */
    public Boolean getDoesStack() {
        return doesStack;
    }

    /**
     * @param doesStack the doesStack to set
     */
    public void setDoesStack(Boolean doesStack) {
        this.doesStack = doesStack;
    }

}
