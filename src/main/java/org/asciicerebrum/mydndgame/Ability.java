package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusSource;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IAbility;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;
import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;

/**
 *
 * @author species8472
 */
public class Ability implements IAbility, BonusSource, BonusTarget {

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
    private List<IBonus> boni = new ArrayList<IBonus>();
    /**
     * The attack mode (ranged/melee) associated with this bonus target. Can
     * also be null.
     */
    private IWeaponCategory associatedAttackMode;
    /**
     * The observer hook associated with this ability.
     */
    private ObserverHook associatedHook;

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
    public final List<IBonus> getBoni() {
        return boni;
    }

    /**
     * @param boniInput the boni to set
     */
    public final void setBoni(final List<IBonus> boniInput) {
        this.boni = boniInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IWeaponCategory getAssociatedAttackMode() {
        return this.associatedAttackMode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setAssociatedAttackMode(
            final IWeaponCategory associatedAttackModeInput) {
        this.associatedAttackMode = associatedAttackModeInput;
    }

    /**
     * @return the associatedHook
     */
    public final ObserverHook getAssociatedHook() {
        return associatedHook;
    }

    /**
     * @param associatedHookInput the associatedHook to set
     */
    public final void setAssociatedHook(
            final ObserverHook associatedHookInput) {
        this.associatedHook = associatedHookInput;
    }

}
