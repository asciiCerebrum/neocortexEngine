package org.asciicerebrum.mydndgame.services.application;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.damage.Damage;
import org.asciicerebrum.mydndgame.domain.mechanics.damage.Damages;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.services.core.ObservableService;

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
        //TODO subtract from temporary hit points first! (e.g. spell false life)
        //TODO hit points gained during barbarian rage, for example, are also
        // treated differently - they are NOT used first like temporary hit
        // points are!
        //TODO handle nonlethal damage correctly!
        //TODO what happens when negative HPs are reached?
        // you have to self-apply the condition unconscious or dead.
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

}