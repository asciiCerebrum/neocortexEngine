package org.asciicerebrum.mydndgame.facades.game;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.Armors;
import org.asciicerebrum.mydndgame.domain.ruleentities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItems;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.services.core.EntityPoolService;
import org.asciicerebrum.mydndgame.services.core.ObservableService;

/**
 *
 * @author species8472
 */
public class DefaultCharacterServiceFacade implements CharacterServiceFacade {

    /**
     * The observable service.
     */
    private ObservableService observableService;

    /**
     * The entity pool service.
     */
    private EntityPoolService entityPoolService;

    @Override
    public final SizeCategory getSize(final DndCharacter dndCharacter) {

        final SizeCategory baseValue = dndCharacter.getBaseSize();
        return (SizeCategory) this.getObservableService()
                .triggerObservers(baseValue, dndCharacter,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.SIZE_CATEGORY),
                        dndCharacter);

    }

    @Override
    public final Armors getArmorWorn(final DndCharacter dndCharacter) {
        final Armors armors = new Armors();
        armors.merge(this.getItemsWorn(dndCharacter));
        return armors;
    }

    @Override
    public final InventoryItems getItemsWorn(final DndCharacter dndCharacter) {

        final InventoryItems items = new InventoryItems();

        final Iterator<PersonalizedBodySlot> slotIterator
                = dndCharacter.getPersonalizedBodySlots().iterator();

        while (slotIterator.hasNext()) {
            final PersonalizedBodySlot slot = slotIterator.next();

            final UniqueEntity item = this.getEntityPoolService().getEntityById(
                    slot.getItemId());
            items.add(item);
        }
        return items;
    }

    /**
     * @param observableServiceInput the observableService to set
     */
    public final void setObservableService(
            final ObservableService observableServiceInput) {
        this.observableService = observableServiceInput;
    }

    /**
     * @return the observableService
     */
    public final ObservableService getObservableService() {
        return observableService;
    }

    /**
     * @return the entityPoolService
     */
    public final EntityPoolService getEntityPoolService() {
        return entityPoolService;
    }

    /**
     * @param entityPoolServiceInput the entityPoolService to set
     */
    public final void setEntityPoolService(
            final EntityPoolService entityPoolServiceInput) {
        this.entityPoolService = entityPoolServiceInput;
    }

}
