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
public class CriticalMinimumLevelTest {

    private CriticalMinimumLevel lvl;

    public CriticalMinimumLevelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.lvl = new CriticalMinimumLevel(42L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameClassTrueTest() {
        assertTrue(this.lvl.equals(new CriticalMinimumLevel(42L)));
    }

    @Test
    public void equalsSameClassFalseTest() {
        assertFalse(this.lvl.equals(new CriticalMinimumLevel(43L)));
    }

    @Test
    public void equalsDifferentClassTest() {
        assertFalse(this.lvl.equals(new AbilityScore(42L)));
    }

}
