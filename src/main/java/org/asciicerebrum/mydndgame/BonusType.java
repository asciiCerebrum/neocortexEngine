package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IBonusType;

/**
 *
 * @author species8472
 */
public class BonusType implements IBonusType {

    /**
     * The unique id of the bonus type.
     */
    private String id;

    /**
     * Determines, if boni of this type are stackable.
     */
    private Boolean doesStack = Boolean.FALSE;

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
     * @return the doesStack
     */
    public final Boolean getDoesStack() {
        return doesStack;
    }

    /**
     * @param doesStackInput the doesStack to set
     */
    public final void setDoesStack(final Boolean doesStackInput) {
        this.doesStack = doesStackInput;
    }

}
