package org.asciicerebrum.mydndgame.services.context;

import org.asciicerebrum.mydndgame.domain.rules.DamageType;
import org.asciicerebrum.mydndgame.domain.rules.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BooleanParticle;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry.StateParticle;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;

/**
 *
 * @author species8472
 */
public class SituationContextService {

    public final InventoryItem getActiveItem(
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry()
                .getState(StateRegistry.StateParticle.ACTIVE_ITEM, null);
    }

    public final WeaponCategory getItemAttackMode(final UniqueEntity item,
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry()
                .getState(StateRegistry.StateParticle.WEAPON_ATTACK_MODE, item);
    }

    public final DamageType getItemDamageType(final UniqueEntity item,
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry()
                .getState(StateRegistry.StateParticle.WEAPON_DAMAGE_TYPE, item);
    }

    public final BonusValue getBonusValueForKey(final StateParticle stateKey,
            final DndCharacter dndCharacter) {
        return new BonusValue((Long) dndCharacter.getStateRegistry()
                .getState(stateKey, dndCharacter));
    }

    public final BooleanParticle getFlagForKey(final StateParticle stateKey,
            final DndCharacter dndCharacter, final UniqueEntity uniqueEntity) {
        return new BooleanParticle(
                (Boolean) dndCharacter.getStateRegistry()
                .getState(stateKey, uniqueEntity));
    }

    //TODO when I load the character through deserialization, how do I know what
    // the current body slot is? They are not unique entities!
    // A solution might be to only save the active item (which is a unique
    // entity). Then the active body slot is determined by this active item.
    //TODO handle default values
}
