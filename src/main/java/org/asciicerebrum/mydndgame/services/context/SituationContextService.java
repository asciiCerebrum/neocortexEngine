package org.asciicerebrum.mydndgame.services.context;

import org.asciicerebrum.mydndgame.domain.core.attribution.DamageType;
import org.asciicerebrum.mydndgame.domain.core.attribution.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BooleanParticle;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.gameentities.StateRegistry;
import org.asciicerebrum.mydndgame.domain.gameentities.StateRegistry.StateParticle;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.gameentities.Weapon;

/**
 *
 * @author species8472
 */
public class SituationContextService {

    public final InventoryItem getActiveItem(
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry()
                .getState(StateRegistry.StateParticle.ACTIVE_ITEM, null,
                        InventoryItem.class);
    }

    public final WeaponCategory getActiveAttackMode(
            final DndCharacter dndCharacter) {
        final InventoryItem item = this.getActiveItem(dndCharacter);
        if (item instanceof Weapon) {
            return this.getWeaponAttackMode((Weapon) item, dndCharacter);
        }
        return null;
    }

    public final DamageType getActiveDamageType(
            final DndCharacter dndCharacter) {
        final InventoryItem item = this.getActiveItem(dndCharacter);
        if (item instanceof Weapon) {
            return this.getWeaponDamageType((Weapon) item, dndCharacter);
        }
        return null;
    }

    public final WeaponCategory getWeaponAttackMode(final Weapon weapon,
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry()
                .getState(StateRegistry.StateParticle.WEAPON_ATTACK_MODE,
                        weapon, WeaponCategory.class);
    }

    public final DamageType getWeaponDamageType(final Weapon weapon,
            final DndCharacter dndCharacter) {

        return dndCharacter.getStateRegistry()
                .getState(StateRegistry.StateParticle.WEAPON_DAMAGE_TYPE,
                        weapon, DamageType.class);
    }

    public final BonusValue getBonusValueForKey(final StateParticle stateKey,
            final DndCharacter dndCharacter) {
        return new BonusValue((long) dndCharacter.getStateRegistry()
                .getState(stateKey, dndCharacter, Long.class));
    }

    public final BooleanParticle getFlagForKey(final StateParticle stateKey,
            final DndCharacter dndCharacter, final UniqueEntity uniqueEntity) {
        return new BooleanParticle(
                (boolean) dndCharacter.getStateRegistry()
                .getState(stateKey, uniqueEntity, Boolean.class));
    }

    //TODO when I load the character through deserialization, how do I know what
    // the current body slot is? They are not unique entities!
    // A solution might be to only save the active item (which is a unique
    // entity). Then the active body slot is determined by this active item.
    //TODO handle default values
}
