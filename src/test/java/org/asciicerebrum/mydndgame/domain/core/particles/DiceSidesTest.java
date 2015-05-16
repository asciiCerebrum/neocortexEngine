package org.asciicerebrum.mydndgame.domain.core.particles;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class DiceSidesTest {

    private DiceSides sides;

    public DiceSidesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.sides = new DiceSides(42L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameClassTrueTest() {
        assertTrue(this.sides.equals(new DiceSides(42L)));
    }

    @Test
    public void equalsSameClassFalseTest() {
        assertFalse(this.sides.equals(new DiceSides(43L)));
    }

    @Test
    public void equalsDifferentClassTest() {
        assertFalse(this.sides.equals(new AbilityScore(42L)));
    }

}
