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
public class WeightTest {

    private Weight weight;

    public WeightTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.weight = new Weight(42L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameClassTrueTest() {
        assertTrue(this.weight.equals(new Weight(42L)));
    }

    @Test
    public void equalsSameClassFalseTest() {
        assertFalse(this.weight.equals(new Weight(43L)));
    }

    @Test
    public void equalsDifferentClassTest() {
        assertFalse(this.weight.equals(new AbilityScore(42L)));
    }

}
