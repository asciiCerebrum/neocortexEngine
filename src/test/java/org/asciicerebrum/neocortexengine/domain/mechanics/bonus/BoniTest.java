package org.asciicerebrum.neocortexengine.domain.mechanics.bonus;

import com.google.common.collect.Iterators;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class BoniTest {

    private Boni boni;

    public BoniTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.boni = new Boni();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addBoniNullTest() {
        final Boni addBoni = null;
        this.boni.addBoni(addBoni);
        assertEquals(0L, Iterators.size(this.boni.iterator()));
    }

    @Test
    public void addBoniNormalTest() {
        final Bonus bonus = new Bonus();
        final Boni addBoni = new Boni();
        addBoni.addBonus(bonus);
        this.boni.addBoni(addBoni);
        assertEquals(1L, Iterators.size(this.boni.iterator()));
    }

}
