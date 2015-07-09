package org.asciicerebrum.neocortexengine.services.context;

import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.BooleanParticle;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.StateRegistry;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DamageType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponCategory;

/**
 *
 * @author species8472
 */
public class DefaultSituationContextService implements SituationContextService {

    @Override
    public final UniqueId getActiveItemId(
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry()
                .getState(StateRegistry.StateParticle.ACTIVE_ITEM, null);
    }

    @Override
    public final WeaponCategory getItemAttackMode(final UniqueId itemId,
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry().getState(
                StateRegistry.StateParticle.WEAPON_ATTACK_MODE, itemId);
    }

    @Override
    public final DamageType getItemDamageType(final UniqueId itemId,
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry().getState(
                StateRegistry.StateParticle.WEAPON_DAMAGE_TYPE, itemId);
    }

    @Override
    public final BonusValue getBonusValueForKey(
            final StateRegistry.StateParticle stateKey,
            final DndCharacter dndCharacter, final UniqueId entityId) {
        final Long stateVal = dndCharacter.getStateRegistry()
                .getState(stateKey, entityId);

        if (stateVal == null) {
            return null;
        }

        return new BonusValue(stateVal);
    }

    @Override
    public final BooleanParticle getFlagForKey(
            final StateRegistry.StateParticle stateKey,
            final DndCharacter dndCharacter, final UniqueId entityId) {
        final Boolean state = dndCharacter.getStateRegistry()
                .getState(stateKey, entityId);

        return new BooleanParticle(state);
    }

}
