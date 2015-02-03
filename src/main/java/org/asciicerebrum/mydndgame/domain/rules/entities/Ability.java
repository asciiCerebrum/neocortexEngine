package org.asciicerebrum.mydndgame.domain.rules.entities;

import org.asciicerebrum.mydndgame.domain.mechanics.entities.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.GenericName;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.BonusTarget;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.ObserverHook;

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
    public UniqueId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UniqueId id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public GenericName getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(GenericName name) {
        this.name = name;
    }

    /**
     * @return the boni
     */
    @Override
    public Boni getBoni() {
        return boni;
    }

    /**
     * @param boni the boni to set
     */
    public void setBoni(Boni boni) {
        this.boni = boni;
    }

    /**
     * @return the associatedAttackMode
     */
    public WeaponCategory getAssociatedAttackMode() {
        return associatedAttackMode;
    }

    /**
     * @param associatedAttackMode the associatedAttackMode to set
     */
    public void setAssociatedAttackMode(WeaponCategory associatedAttackMode) {
        this.associatedAttackMode = associatedAttackMode;
    }

    /**
     * @return the associatedHook
     */
    @Override
    public ObserverHook getAssociatedHook() {
        return associatedHook;
    }

    /**
     * @param associatedHook the associatedHook to set
     */
    @Override
    public void setAssociatedHook(ObserverHook associatedHook) {
        this.associatedHook = associatedHook;
    }

    @Override
    public BonusSources getBonusSources() {
        return BonusSources.EMPTY_BONUSSOURCES;
    }

}
