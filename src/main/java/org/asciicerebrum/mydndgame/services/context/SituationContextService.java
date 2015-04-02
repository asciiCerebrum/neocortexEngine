package org.asciicerebrum.mydndgame.services.context;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BooleanParticle;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry;
import org.asciicerebrum.mydndgame.domain.ruleentities.DamageType;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponCategory;

/**
 *
 * @author species8472
 */
public interface SituationContextService {

    /**
     * Retrieves the item marked as active from the situation context.
     *
     * @param dndCharacter the character giving the context.
     * @return the active item.
     */
    InventoryItem getActiveItem(DndCharacter dndCharacter);

    /**
     * Retrieves the attack mode of the given item from the situation context.
     *
     * @param itemId the item the attack mode is needed for.
     * @param dndCharacter the character giving the context.
     * @return the attack mode of that item in the context of the given
     * character.
     */
    WeaponCategory getItemAttackMode(UniqueId itemId,
            DndCharacter dndCharacter);

    /**
     * Retrieves the damage type of the given item from the situation context.
     *
     * @param itemId the item the damage type is needed for.
     * @param dndCharacter the character giving the context.
     * @return the damage type of that item in the context of the given
     * character.
     */
    DamageType getItemDamageType(UniqueId itemId,
            DndCharacter dndCharacter);

    /**
     * Retrieves a bonus value from a given state key. The registry can only
     * save long values but it is cast to a bonus value.
     *
     * @param stateKey the key of the state of interest.
     * @param dndCharacter the character giving the context.
     * @return the bonus value behind that key.
     */
    BonusValue getBonusValueForKey(StateRegistry.StateParticle stateKey,
            DndCharacter dndCharacter);

    /**
     * Retrieves a boolean value from a given state key.
     *
     * @param stateKey the key of the state of interest.
     * @param dndCharacter the character giving the context.
     * @param entityId the unique entity defining the key further.
     * @return the boolean value of that key.
     */
    BooleanParticle getFlagForKey(StateRegistry.StateParticle stateKey,
            DndCharacter dndCharacter, UniqueId entityId);
}
