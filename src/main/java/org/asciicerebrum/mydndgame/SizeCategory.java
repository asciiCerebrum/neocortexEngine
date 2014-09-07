package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusSource;
import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ISizeCategory;

/**
 *
 * @author species8472
 */
public class SizeCategory implements ISizeCategory, BonusSource {

    /**
     * The unique id of this size category. E.g. small, medium, etc.
     */
    private String id;
    /**
     * The list of boni associated with this size category.
     */
    private List<IBonus> boni = new ArrayList<IBonus>();

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setId(final String idInput) {
        this.id = idInput;
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
