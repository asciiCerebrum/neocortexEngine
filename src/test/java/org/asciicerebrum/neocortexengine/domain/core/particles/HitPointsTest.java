package org.asciicerebrum.neocortexengine.domain.core.particles;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author species8472
 */
public class HitPointsTest {

    private HitPoints hp;

    public HitPointsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.hp = new HitPoints(42L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameClassTrueTest() {
        assertTrue(this.hp.equals(new HitPoints(42L)));
    }

    @Test
    public void equalsSameClassFalseTest() {
        assertFalse(this.hp.equals(new HitPoints(43L)));
    }

    @Test
    public void equalsDifferentClassTest() {
        assertFalse(this.hp.equals(new AbilityScore(42L)));
    }

}
