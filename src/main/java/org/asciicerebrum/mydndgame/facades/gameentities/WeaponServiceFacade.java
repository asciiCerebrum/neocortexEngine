package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.Encumbrance;
import org.asciicerebrum.mydndgame.domain.core.attribution.Proficiency;
import org.asciicerebrum.mydndgame.domain.core.attribution.WeaponCategories;
import org.asciicerebrum.mydndgame.domain.core.attribution.WeaponTypes;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.Weapon;
import org.asciicerebrum.mydndgame.domain.gameentities.prototypes.DiceAction;

/**
 *
 * @author species8472
 */
public interface WeaponServiceFacade {

    CriticalMinimumLevel getCriticalMinimumLevel(CriticalMinimumLevel baseValue,
            Weapon weapon, DndCharacter dndCharacter);

    CriticalFactor getCriticalFactor(CriticalFactor baseValue,
            Weapon weapon, DndCharacter dndCharacter);

    DiceAction getDamage(DiceAction baseValue,
            Weapon weapon, DndCharacter dndCharacter);

    WeaponTypes getWeaponTypes(WeaponTypes baseValue,
            Weapon weapon, DndCharacter dndCharacter);

    Encumbrance getEncumbrance(Encumbrance baseValue,
            Weapon weapon, DndCharacter dndCharacter);

    Proficiency getProficiency(Proficiency baseValue,
            Weapon weapon, DndCharacter dndCharacter);

    WeaponCategories getCategories(WeaponCategories baseValue,
            Weapon weapon, DndCharacter dndCharacter);

}
