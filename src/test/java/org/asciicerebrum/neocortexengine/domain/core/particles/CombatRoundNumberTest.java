package org.asciicerebrum.neocortexengine.domain.core.particles;

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
public class CombatRoundNumberTest {

    private CombatRoundNumber number;

    public CombatRoundNumberTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.number = new CombatRoundNumber(42L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameClassTrueTest() {
        assertTrue(this.number.equals(new CombatRoundNumber(42L)));
    }

    @Test
    public void equalsSameClassFalseTest() {
        assertFalse(this.number.equals(new CombatRoundNumber(43L)));
    }

    @Test
    public void equalsDifferentClassTest() {
        assertFalse(this.number.equals(new AbilityScore(42L)));
    }

}
