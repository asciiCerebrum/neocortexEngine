package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.ruleentities.Encumbrance;
import org.asciicerebrum.mydndgame.domain.ruleentities.Proficiency;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponCategories;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponType;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponTypes;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.Weapon;
import org.asciicerebrum.mydndgame.domain.gameentities.prototypes.DiceAction;

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
