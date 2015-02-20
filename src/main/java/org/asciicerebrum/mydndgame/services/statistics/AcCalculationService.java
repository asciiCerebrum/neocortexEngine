package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.particles.ArmorClass;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;

/**
 *
 * @author species8472
 */
public interface AcCalculationService {

    /**
     * Calculates the armor class of a given dnd character.
     *
     * @param dndCharacter the character the ac is needed for.
     * @return the ac.
     */
    ArmorClass calcAc(DndCharacter dndCharacter);

    /**
     * Keep the following use cases in mind.
     *
     * 1. Flat-footed: A character who has not yet acted during a combat is
     * flat-footed, not yet reacting normally to the situation. A flat-footed
     * character loses his Dexterity bonus to AC (if any) and cannot make
     * attacks of opportunity.<br />
     * 2. Touch attack<br />
     * 3. Wearing a shield and armor (both mwk)<br />
     * 3a. Armor that can also be used as a weapon<br />
     * 3b. Armor that is not worn/wielded does not contribute to the ac.<br />
     * 4. Feat Dodge: against a designated opponent --> this is only relevant
     * the real current AC method.<br />
     * 5. Armor proficiency.<br />
     * 6. Max Dexterity Bonus limit.
     *
     * This method is for statistical purpose only. What would be the ideal AC
     * of this character if nothing else (no special negative conditions, etc.)
     * would apply.
     *
     * @param dndCharacter the context.
     * @return the calculated standard armor class of this character.
     */
    //TODO implement all use cases mentioned above.
    ArmorClass calcAcStandard(DndCharacter dndCharacter);

    /**
     * This method is for statistcial purpose only. What would be AC be if the
     * character was flat-footed and nothing else would apply. It is a
     * simulation of this condition.
     *
     * @param dndCharacter the context.
     * @return the armor class under the condition flat-footed.
     */
    ArmorClass calcAcFlatFooted(DndCharacter dndCharacter);

    /**
     * This method is for statistical purpose only. What would the AC be if it
     * was a touch attack and nothing else would apply.
     *
     * @param dndCharacter the context.
     * @return the armor class when experiencing a touch attack.
     */
    ArmorClass calcAcTouch(DndCharacter dndCharacter);
}
