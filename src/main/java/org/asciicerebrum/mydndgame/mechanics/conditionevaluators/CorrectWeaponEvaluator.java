package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Observer;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.entities.Weapon;
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
            final Observer referenceObserver) {

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
        return this.evaluate(dndCharacter, (Observer) null);
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
