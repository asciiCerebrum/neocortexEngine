package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.rules.entities.Encumbrance;
import org.asciicerebrum.mydndgame.domain.rules.entities.Proficiency;
import org.asciicerebrum.mydndgame.domain.rules.entities.WeaponCategories;
import org.asciicerebrum.mydndgame.domain.rules.entities.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.rules.entities.WeaponType;
import org.asciicerebrum.mydndgame.domain.rules.entities.WeaponTypes;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.Weapon;
import org.asciicerebrum.mydndgame.domain.game.entities.prototypes.DiceAction;

/**
 *
 * @author species8472
 */
public interface WeaponServiceFacade extends InventoryItemServiceFacade {

    CriticalMinimumLevel getCriticalMinimumLevel(Weapon weapon,
            DndCharacter dndCharacter);

    CriticalFactor getCriticalFactor(Weapon weapon, DndCharacter dndCharacter);

    DiceAction getDamage(Weapon weapon, DndCharacter dndCharacter);

    WeaponTypes getWeaponTypes(Weapon weapon, DndCharacter dndCharacter);

    boolean hasWeaponType(WeaponType weaponType, Weapon weapon,
            DndCharacter dndCharacter);

    Encumbrance getEncumbrance(Weapon weapon, DndCharacter dndCharacter);

    boolean hasEncumbrance(Encumbrance encumbrance, Weapon weapon,
            DndCharacter dndCharacter);

    Proficiency getProficiency(Weapon weapon, DndCharacter dndCharacter);

    boolean hasProficiency(Proficiency proficiency, Weapon weapon,
            DndCharacter dndCharacter);

    WeaponCategories getCategories(Weapon weapon, DndCharacter dndCharacter);

    boolean isAttackModeCompatible(WeaponCategory attackMode, Weapon weapon,
            DndCharacter dndCharacter);

}
