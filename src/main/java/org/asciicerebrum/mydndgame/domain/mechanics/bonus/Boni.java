package org.asciicerebrum.mydndgame.domain.mechanics.bonus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class Boni {

    /**
     * Ready to use empty collection of boni.
     */
    public static final Boni EMPTY_BONI = new Boni();

    /**
     * The list of boni.
     */
    private final List<Bonus> elements = new ArrayList<Bonus>();

    /**
     * Empty constructor for an empty list of boni.
     */
    public Boni() {

    }

    /**
     * Adding the given list of boni to the fresh instance.
     *
     * @param boni the list of boni to add.
     */
    public Boni(final List<Bonus> boni) {
        this.elements.addAll(boni);
    }

    /**
     * @param boniInput the boni to set
     */
    final void addBoni(final Collection<Bonus> boniInput) {
        this.elements.addAll(boniInput);
    }

    /**
     * Adds a further collection of boni to the instance.
     *
     * @param boniInput the boni to add.
     */
    public final void addBoni(final Boni boniInput) {
        if (boniInput == null) {
            return;
        }
        this.elements.addAll(boniInput.elements);
    }

    /**
     * Adds a further single bonus to the instance.
     *
     * @param bonusInput the bonus to add.
     */
    public final void addBonus(final Bonus bonusInput) {
        this.elements.add(bonusInput);
    }

    /**
     * Retrieves an iterator over the collection of boni.
     *
     * @return the iterator.
     */
    public final Iterator<Bonus> iterator() {
        return this.elements.iterator();
    }

    /**
     * Tests if the given bonus is part of the list of boni.
     *
     * @param bonus the bonus to test.
     * @return true if bonus was found, false otherwise.
     */
    public final boolean contains(final Bonus bonus) {
        return this.elements.contains(bonus);
    }

}
