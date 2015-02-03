package org.asciicerebrum.mydndgame.domain.rules;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 * The character class does not hold its class levels. The reference is the
 * other way round. This is because the dnd character only holds a number of
 * level advancements which only hold class levels. So the access direction
 * comes from the class levels first.
 *
 * @author species8472
 */
public class CharacterClass implements BonusSource, ObserverSource {

    /**
     * The unique id of the character class.
     */
    private UniqueId id;
    /**
     * The type of hit die associated with this character class.
     */
    private Dice hitDice;

    /**
     * The list of feats which are automatically granted to the character when
     * acquiring this class.
     */
    private Feats classFeats;

    public final Dice getHitDice() {
        return hitDice;
    }

    /**
     * @param hitDiceInput the hitDice to set
     */
    public final void setHitDice(final Dice hitDiceInput) {
        this.hitDice = hitDiceInput;
    }

    /**
     * @return the id
     */
    public UniqueId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UniqueId id) {
        this.id = id;
    }

    /**
     * @return the classFeats
     */
    public Feats getClassFeats() {
        return classFeats;
    }

    /**
     * @param classFeats the classFeats to set
     */
    public void setClassFeats(Feats classFeats) {
        this.classFeats = classFeats;
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        bonusSources.add(this.classFeats);

        return bonusSources;
    }

}
