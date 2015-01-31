package org.asciicerebrum.mydndgame.conditionevaluator;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.rules.entities.FeatBinding;
import org.asciicerebrum.mydndgame.domain.rules.entities.FeatBindings;
import org.asciicerebrum.mydndgame.domain.rules.entities.Proficiency;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.rules.entities.FeatType;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.entities.Weapon;
import org.asciicerebrum.mydndgame.facades.gameentities.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.observers.IObserver;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class CorrectProficiencyForFeatEvaluator implements ConditionEvaluator {

    private FeatType featType;

    private SituationContextService situationContextService;

    /**
     * Getting modified real-time-values from the weapon.
     */
    private WeaponServiceFacade weaponServiceFacade;

    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final IObserver referenceObserver) {

        if (this.featType == null) {
            return false;
        }

        final FeatBindings featBindings = dndCharacter.getLevelAdvancements()
                .getFeatBindingsByFeatType(this.featType);

        final InventoryItem item = this.situationContextService
                .getActiveItem(dndCharacter);

        if (!(item instanceof Weapon)) {
            return false;
        }

        final Weapon weapon = (Weapon) item;
        final Iterator<FeatBinding> featBindingIterator
                = featBindings.iterator();
        while (featBindingIterator.hasNext()) {
            final FeatBinding featBinding = featBindingIterator.next();
            if (!(featBinding instanceof Proficiency)) {
                continue;
            }

            if (this.weaponServiceFacade.hasProficiency(
                    (Proficiency) featBinding, weapon, dndCharacter)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        // nothing to do here

        return false;
    }

    /**
     * @param featTypeInput the featType to set
     */
    public final void setFeatType(final FeatType featTypeInput) {
        this.featType = featTypeInput;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

    /**
     * @param weaponServiceFacadeInput the weaponServiceFacade to set
     */
    public final void setWeaponServiceFacade(
            final WeaponServiceFacade weaponServiceFacadeInput) {
        this.weaponServiceFacade = weaponServiceFacadeInput;
    }

}
