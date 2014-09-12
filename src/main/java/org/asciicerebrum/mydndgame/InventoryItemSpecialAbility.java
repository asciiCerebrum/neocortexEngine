package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusSource;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.ISpecialAbility;

/**
 *
 * @author species8472
 */
public abstract class InventoryItemSpecialAbility
        implements ISpecialAbility, BonusSource {

    /**
     * The id of the special ability.
     */
    private String id;

    /**
     * The list of quasi static boni that are granted by this special ability.
     */
    private List<IBonus> boni;

    /**
     * The list of observers that hook into the weapon to modify certain
     * attributes dynamically.
     */
    private List<IObserver> observers = new ArrayList<IObserver>();

    /**
     * For example a magic weapon is always a masterwork. So the masterwork is
     * the subAbility of the magic feature.
     */
    private List<ISpecialAbility> subAbilities;

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<IBonus> getBoni() {
        return boni;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setBoni(final List<IBonus> boniInput) {
        this.boni = boniInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<IObserver> getObservers() {
        return observers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setObservers(final List<IObserver> observersInput) {
        this.observers = observersInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ISpecialAbility> getSubAbilities() {
        return subAbilities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setSubAbilities(
            final List<ISpecialAbility> subAbilitiesInput) {
        this.subAbilities = subAbilitiesInput;
    }

}
