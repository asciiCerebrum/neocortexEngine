package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import org.asciicerebrum.mydndgame.domain.ruleentities.FeatBindings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.AbilityScore;
import org.asciicerebrum.mydndgame.domain.core.particles.AdvancementNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.ruleentities.ClassLevel;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatType;

/**
 *
 * @author species8472
 */
public class LevelAdvancements implements BonusSource, ObserverSource {

    /**
     * Iterator over the class levels that corresond to the leve advancements of
     * this collection.
     */
    private static class ClassLevelIterator implements Iterator<ClassLevel> {

        /**
         * The level advancement iterator.
         */
        private final Iterator<LevelAdvancement> lvlAdvIterator;

        /**
         * Constructing the class level iterator from a level advancement
         * iterator to delegate the calls.
         *
         * @param lvlAdvIteratorInput the level advancement iterator.
         */
        public ClassLevelIterator(
                final Iterator<LevelAdvancement> lvlAdvIteratorInput) {
            this.lvlAdvIterator = lvlAdvIteratorInput;
        }

        @Override
        public final boolean hasNext() {
            return this.lvlAdvIterator.hasNext();
        }

        @Override
        public final ClassLevel next() {
            return this.lvlAdvIterator.next().getClassLevel();
        }

        @Override
        public final void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    /**
     * Iterator over the hit points gathered with each level advancement.
     */
    private static class HpIterator implements Iterator<HitPoints> {

        /**
         * The iterator over the level advancement.
         */
        private final Iterator<LevelAdvancement> lvlAdvIterator;

        /**
         * Constructing the hp iterator from the level advancement iterator.
         *
         * @param lvlAdvIteratorInput the level advancement iterator.
         */
        public HpIterator(
                final Iterator<LevelAdvancement> lvlAdvIteratorInput) {
            this.lvlAdvIterator = lvlAdvIteratorInput;
        }

        @Override
        public final boolean hasNext() {
            return lvlAdvIterator.hasNext();
        }

        @Override
        public final HitPoints next() {
            return lvlAdvIterator.next().getHpAdvancement();
        }

        @Override
        public final void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    /**
     * The central collection of level advancements.
     */
    private final List<LevelAdvancement> elements
            = new ArrayList<LevelAdvancement>();

    /**
     * Adds a single level advancement to the list.
     *
     * @param levelAdvancement the level advancement to add.
     */
    public final void add(final LevelAdvancement levelAdvancement) {
        this.elements.add(levelAdvancement);
    }

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni ctxBoni = new ContextBoni();

        for (LevelAdvancement levelAdvancement : this.elements) {
            ctxBoni.add(levelAdvancement.getBoni(context, resolver));
        }

        return ctxBoni;
    }

    /**
     * Iterator over the level advancement.
     *
     * @return the iterator.
     */
    public final Iterator<LevelAdvancement> iterator() {
        return this.elements.iterator();
    }

    /**
     * Iterator over the class levels of the level advancements.
     *
     * @return the iterator.
     */
    public final Iterator<ClassLevel> classLevelIterator() {
        return new ClassLevelIterator(this.elements.iterator());
    }

    /**
     * Iterator over the HPs of the level advancements.
     *
     * @return the iterator.
     */
    public final Iterator<HitPoints> hpIterator() {
        return new HpIterator(this.elements.iterator());
    }

    /**
     * Counts the instances of a given ability within the collection of level
     * advancements.
     *
     * @param ability the ability to count.
     * @return the frequency of this ability.
     */
    public final AbilityScore countAbility(final Ability ability) {
        long count = 0;
        for (final LevelAdvancement lvlAdv : this.elements) {
            if (ability.equals(lvlAdv.getAbilityAdvancement())) {
                count++;
            }
        }
        return new AbilityScore(count);
    }

    /**
     * Returns the n-th level advancement.
     *
     * @param advNo the number n of the advancement.
     * @return the level advancement of that advancement number.
     */
    public final LevelAdvancement getLevelAdvancementByNumber(
            final AdvancementNumber advNo) {
        for (final LevelAdvancement lvlAdv : this.elements) {
            if (advNo.equals(lvlAdv.getAdvNumber())) {
                return lvlAdv;
            }
        }
        return null;
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

        for (final LevelAdvancement lvlAdv : this.elements) {
            featBindings.add(lvlAdv.getFeatBindingsByFeatType(featType));
        }

        return featBindings;
    }

    /**
     * Summates the unmodified base hit points of the collection of level
     * advancements.
     *
     * @return the base hit points.
     */
    public final HitPoints summateHpAdvancements() {
        final HitPoints baseHp = new HitPoints();
        for (final LevelAdvancement lvlAdv : this.elements) {
            baseHp.add(lvlAdv.getHpAdvancement());
        }
        return baseHp;
    }

    /**
     * Calculates the total number of level advancements. This equals the level
     * of the character.
     *
     * @return the total number of class levels.
     */
    public final AdvancementNumber countLevelAdvancements() {
        return new AdvancementNumber(this.elements.size());
    }

    /**
     * Summates the unmodified base attack boni of this collection of leven
     * advancements.
     *
     * @return the base attack boni.
     */
    public final BonusValueTuple summateBaseAtkBoni() {
        final BonusValueTuple boniTuple = new BonusValueTuple();

        final Iterator<ClassLevel> clLvlIterator
                = this.classLevelIterator();

        while (clLvlIterator.hasNext()) {
            final ClassLevel clLvl = clLvlIterator.next();
            boniTuple.add(clLvl.getBaseAtkBoniDelta());
        }

        return boniTuple;
    }

    /**
     * The size of the underlying list.
     *
     * @return the number of level advancements.
     */
    public final int size() {
        return this.elements.size();
    }

}
