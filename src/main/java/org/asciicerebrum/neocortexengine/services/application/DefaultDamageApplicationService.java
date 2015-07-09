package org.asciicerebrum.neocortexengine.services.application;

import java.util.Iterator;
import org.asciicerebrum.neocortexengine.domain.core.particles.EventFact;
import org.asciicerebrum.neocortexengine.domain.events.EventEntry;
import org.asciicerebrum.neocortexengine.domain.events.EventType;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHooks;
import org.asciicerebrum.neocortexengine.domain.mechanics.damage.Damage;
import org.asciicerebrum.neocortexengine.domain.mechanics.damage.Damages;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.neocortexengine.services.core.ObservableService;
import org.asciicerebrum.neocortexengine.services.events.EventTriggerService;
import org.asciicerebrum.neocortexengine.services.statistics.HpCalculationService;

/**
 *
 * @author species8472
 */
public class DefaultDamageApplicationService
        implements DamageApplicationService {

    /**
     * The observable service.
     */
    private ObservableService observableService;

    /**
     * Triggering events.
     */
    private EventTriggerService eventTriggerService;

    /**
     * The service for calculating everything related to hp.
     */
    private HpCalculationService hpCalculationService;

    @Override
    public final void applyDamage(final DndCharacter dndCharacter,
            final Damages damages) {

        final Iterator<Damage> damageIterator = damages.iterator();
        while (damageIterator.hasNext()) {
            final Damage damage = damageIterator.next();

            final Damage convertedDamage
                    = (Damage) this.getObservableService().triggerObservers(
                            damage, dndCharacter,
                            new ObserverSources(dndCharacter),
                            new ObserverHooks(ObserverHook.DAMAGE_APPLICATION),
                            dndCharacter);

            if (convertedDamage != null) {
                this.applySingleDamage(dndCharacter, convertedDamage);

                if (this.getEventTriggerService() != null) {
                    final EventEntry resultEvent
                            = new EventEntry(EventType.DAMAGE_APPLICATION);
                    resultEvent.setWho(dndCharacter.getUniqueId());
                    resultEvent.addEventFact(new EventFact(
                            Long.toString(convertedDamage.getDamageValue()
                                    .getValue())));
                    resultEvent.addEventFact(new EventFact(
                            Long.toString(this.getHpCalculationService()
                                    .calcCurrentHp(dndCharacter).getValue())));
                    this.getEventTriggerService().trigger(resultEvent);
                }
            }
        }
    }

    /**
     * Applies a single damage on the character.
     *
     * @param dndCharacter the character to apply the damage on.
     * @param damage the damage to apply.
     */
    final void applySingleDamage(final DndCharacter dndCharacter,
            final Damage damage) {
        dndCharacter.getCurrentStaticHp().subtract(damage.getDamageValue());
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
     * @return the eventTriggerService
     */
    public final EventTriggerService getEventTriggerService() {
        return eventTriggerService;
    }

    /**
     * @param eventTriggerServiceInput the eventTriggerService to set
     */
    public final void setEventTriggerService(
            final EventTriggerService eventTriggerServiceInput) {
        this.eventTriggerService = eventTriggerServiceInput;
    }

    /**
     * @return the hpCalculationService
     */
    public final HpCalculationService getHpCalculationService() {
        return hpCalculationService;
    }

    /**
     * @param hpCalculationServiceInput the hpCalculationService to set
     */
    public final void setHpCalculationService(
            final HpCalculationService hpCalculationServiceInput) {
        this.hpCalculationService = hpCalculationServiceInput;
    }

}
