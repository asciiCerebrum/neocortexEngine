package org.asciicerebrum.neocortexengine.domain.core.particles;

import org.asciicerebrum.neocortexengine.domain.game.Weapon;
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
public class UniqueIdTest {

    private UniqueId uniqueId;

    public UniqueIdTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.uniqueId = new UniqueId("uniqueId");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsNotInstanceTest() {
        assertFalse(this.uniqueId.equals(new Weapon()));
    }

    @Test
    public void equalsIdenticalInstanceTest() {
        assertTrue(this.uniqueId.equals(this.uniqueId));
    }

    @Test
    public void equalsEqualInstanceTest() {
        final UniqueId testId = new UniqueId("uniqueId");
        assertTrue(this.uniqueId.equals(testId));
    }

    @Test
    public void equalsUnequalInstanceTest() {
        final UniqueId testId = new UniqueId("uniqueId2");
        assertFalse(this.uniqueId.equals(testId));
    }

}
