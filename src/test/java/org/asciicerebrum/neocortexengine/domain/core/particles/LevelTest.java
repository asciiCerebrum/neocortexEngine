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
public class LevelTest {

    private Level lvl;

    public LevelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.lvl = new Level(42L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameClassTrueTest() {
        assertTrue(this.lvl.equals(new Level(42L)));
    }

    @Test
    public void equalsSameClassFalseTest() {
        assertFalse(this.lvl.equals(new Level(43L)));
    }

    @Test
    public void equalsDifferentClassTest() {
        assertFalse(this.lvl.equals(new AbilityScore(42L)));
    }

}
