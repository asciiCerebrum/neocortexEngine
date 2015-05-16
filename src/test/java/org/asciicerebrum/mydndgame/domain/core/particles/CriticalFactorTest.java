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
public class CriticalFactorTest {

    private CriticalFactor factor;

    public CriticalFactorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factor = new CriticalFactor(42L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameClassTrueTest() {
        assertTrue(this.factor.equals(new CriticalFactor(42L)));
    }

    @Test
    public void equalsSameClassFalseTest() {
        assertFalse(this.factor.equals(new CriticalFactor(43L)));
    }

    @Test
    public void equalsDifferentClassTest() {
        assertFalse(this.factor.equals(new AbilityScore(42L)));
    }

}
