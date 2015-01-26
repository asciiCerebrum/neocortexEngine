package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.domain.core.attribution.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.gameentities.Weapon;
import org.asciicerebrum.mydndgame.observers.IObserver;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class CorrectWeaponAttackModeEvaluator implements ConditionEvaluator {

    /**
     * Getting settings from the character.
     */
    private SituationContextService situationContextService;

    /**
     * {@inheritDoc} Checks if the given weapon is used in its destined way.
     *
     * @return
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final IObserver referenceObserver) {

        final WeaponCategory refAttackMode = this.situationContextService
                .getActiveAttackMode(dndCharacter);

        final InventoryItem refWeapon = this.situationContextService
                .getActiveItem(dndCharacter);

        if (refAttackMode == null || refWeapon == null
                || !(refWeapon instanceof Weapon)) {
            return false;
        }

        return ((Weapon) refWeapon).isAttackModeCompatible(refAttackMode,
                dndCharacter);
    }

    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        return this.evaluate(dndCharacter, (IObserver) null);
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

}
