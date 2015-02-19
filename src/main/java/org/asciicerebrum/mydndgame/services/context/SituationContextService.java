package org.asciicerebrum.mydndgame.services.context;

import org.asciicerebrum.mydndgame.domain.ruleentities.DamageType;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponCategory;
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

    /**
     * Retrieves the item marked as active from the situation context.
     *
     * @param dndCharacter the character giving the context.
     * @return the active item.
     */
    public final InventoryItem getActiveItem(
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry()
                .getState(StateRegistry.StateParticle.ACTIVE_ITEM, null);
    }

    /**
     * Retrieves the attack mode of the given item from the situation context.
     *
     * @param item the item the attack mode is needed for.
     * @param dndCharacter the character giving the context.
     * @return the attack mode of that item in the context of the given
     * character.
     */
    public final WeaponCategory getItemAttackMode(final UniqueEntity item,
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry()
                .getState(StateRegistry.StateParticle.WEAPON_ATTACK_MODE, item);
    }

    /**
     * Retrieves the damage type of the given item from the situation context.
     *
     * @param item the item the damage type is needed for.
     * @param dndCharacter the character giving the context.
     * @return the damage type of that item in the context of the given
     * character.
     */
    public final DamageType getItemDamageType(final UniqueEntity item,
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry()
                .getState(StateRegistry.StateParticle.WEAPON_DAMAGE_TYPE, item);
    }

    /**
     * Retrieves a bonus value from a given state key. The registry can only
     * save long values but it is cast to a bonus value.
     *
     * @param stateKey the key of the state of interest.
     * @param dndCharacter the character giving the context.
     * @return the bonus value behind that key.
     */
    public final BonusValue getBonusValueForKey(final StateParticle stateKey,
            final DndCharacter dndCharacter) {
        return new BonusValue((Long) dndCharacter.getStateRegistry()
                .getState(stateKey, dndCharacter));
    }

    /**
     * Retrieves a boolean value from a given state key.
     *
     * @param stateKey the key of the state of interest.
     * @param dndCharacter the character giving the context.
     * @param uniqueEntity the unique entity defining the key further.
     * @return the boolean value of that key.
     */
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
