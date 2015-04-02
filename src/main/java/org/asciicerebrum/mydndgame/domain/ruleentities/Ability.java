package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.GenericName;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public class Ability implements BonusSource, BonusTarget, ObserverSource {

    /**
     * Unique id of the ability.
     */
    private UniqueId id;
    /**
     * Descriptive name of the ability.
     */
    private GenericName name;
    /**
     * Collection of all boni associated with this ability.
     */
    private Boni boni;
    /**
     * The attack mode (ranged/melee) associated with this bonus target. Can
     * also be null.
     */
    private WeaponCategory associatedAttackMode;
    /**
     * The observer hook associated with this ability.
     */
    private ObserverHook associatedHook;

    /**
     * @return the id
     */
    public final UniqueId getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final UniqueId idInput) {
        this.id = idInput;
    }

    /**
     * @return the name
     */
    public final GenericName getName() {
        return name;
    }

    /**
     * @param nameInput the name to set
     */
    public final void setName(final GenericName nameInput) {
        this.name = nameInput;
    }

    /**
     * @return the boni
     */
    @Override
    public final Boni getBoni() {
        return boni;
    }

    /**
     * @param boniInput the boni to set
     */
    public final void setBoni(final Boni boniInput) {
        this.boni = boniInput;
    }

    /**
     * @return the associatedAttackMode
     */
    public final WeaponCategory getAssociatedAttackMode() {
        return associatedAttackMode;
    }

    /**
     * @param associatedAttackModeInput the associatedAttackMode to set
     */
    public final void setAssociatedAttackMode(
            final WeaponCategory associatedAttackModeInput) {
        this.associatedAttackMode = associatedAttackModeInput;
    }

    /**
     * @return the associatedHook
     */
    @Override
    public final ObserverHook getAssociatedHook() {
        return associatedHook;
    }

    /**
     * @param associatedHookIn the associatedHook to set
     */
    @Override
    public final void setAssociatedHook(final ObserverHook associatedHookIn) {
        this.associatedHook = associatedHookIn;
    }

    @Override
    public final BonusSources getBonusSources(
            final UniqueEntityResolver resolver) {
        return BonusSources.EMPTY_BONUSSOURCES;
    }

}
