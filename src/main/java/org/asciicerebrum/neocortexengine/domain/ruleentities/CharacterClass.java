package org.asciicerebrum.neocortexengine.domain.ruleentities;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;

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
    private Feats classFeats = new Feats();

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

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni ctxBoni = new ContextBoni();

        ctxBoni.add(this.classFeats.getBoni(context, resolver));

        return ctxBoni;
    }

    /**
     * @param classFeatsInput the classFeats to set
     */
    public final void setClassFeats(final Feats classFeatsInput) {
        this.classFeats = classFeatsInput;
    }

    /**
     * Retrieves the feat bindings by the given feat type.
     *
     * @param featType the feat type the bindings are needed for.
     * @return the collection of feat bindings.
     */
    public final FeatBindings getFeatBindingsByFeatType(
            final FeatType featType) {

        final FeatBindings featBindings = new FeatBindings();

        featBindings.add(this.getClassFeats()
                .getFeatBindingsByFeatType(featType));

        return featBindings;
    }

}
