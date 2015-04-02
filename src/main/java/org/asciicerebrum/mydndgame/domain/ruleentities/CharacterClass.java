package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;

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

    /**
     * @return the hit dice.
     */
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
    public final UniqueId getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final UniqueId idInput) {
        this.id = idInput;
    }

    /**
     * @return the classFeats
     */
    public final Feats getClassFeats() {
        return classFeats;
    }

    /**
     * @param classFeatsInput the classFeats to set
     */
    public final void setClassFeats(final Feats classFeatsInput) {
        this.classFeats = classFeatsInput;
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources(
            final UniqueEntityResolver resolver) {
        final BonusSources bonusSources = new BonusSources();

        bonusSources.add(this.classFeats);

        return bonusSources;
    }

}
