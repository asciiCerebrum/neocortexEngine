package org.asciicerebrum.mydndgame.conditionevaluator.impl;

import org.asciicerebrum.mydndgame.conditionevaluator.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.entities.Weapon;
import org.asciicerebrum.mydndgame.observers.IObserver;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class CorrectWeaponEvaluator implements ConditionEvaluator {

    /**
     * The weapon to check.
     */
    private Weapon weapon;

    /**
     * Getting settings from the character.
     */
    private SituationContextService situationContextService;

    /**
     * {@inheritDoc} Checks if the given weapon corresponds to the one from the
     * situation context.
     *
     * @return if weapons resemble each other. True if weapon is not set.
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final IObserver referenceObserver) {

        if (this.weapon == null) {
            return true;
        }

        final InventoryItem refWeapon = this.situationContextService
                .getActiveItem(dndCharacter);

        if (refWeapon == null || !(refWeapon instanceof Weapon)) {
            return false;
        }

        return this.weapon.resembles(refWeapon);

    }

    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        return this.evaluate(dndCharacter, (IObserver) null);
    }

    /**
     * @param weaponInput the weapon to set
     */
    public final void setWeapon(final Weapon weaponInput) {
        this.weapon = weaponInput;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

}
