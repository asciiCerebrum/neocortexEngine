package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.IRace;

/**
 *
 * @author species8472
 */
public class Race implements IRace {

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
     * All the body slots this race is providing.
     */
    private List<IBodySlotType> providedBodySlotTypes
            = new ArrayList<IBodySlotType>();

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

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<IBodySlotType> getProvidedBodySlotTypes() {
        return providedBodySlotTypes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setProvidedBodySlotTypes(
            final List<IBodySlotType> providedBodySlotTypesInput) {
        this.providedBodySlotTypes = providedBodySlotTypesInput;
    }

}
