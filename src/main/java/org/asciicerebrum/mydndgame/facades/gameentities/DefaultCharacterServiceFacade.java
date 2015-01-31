package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.rules.entities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
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

    @Override
    public final SizeCategory getSize(final DndCharacter dndCharacter) {

        final SizeCategory baseValue = dndCharacter.getBaseSize();
        return (SizeCategory) this.observableService
                .triggerObservers(baseValue, dndCharacter,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.SIZE_CATEGORY),
                        dndCharacter);

    }

    /**
     * @param observableServiceInput the observableService to set
     */
    public final void setObservableService(
            final ObservableService observableServiceInput) {
        this.observableService = observableServiceInput;
    }

}
