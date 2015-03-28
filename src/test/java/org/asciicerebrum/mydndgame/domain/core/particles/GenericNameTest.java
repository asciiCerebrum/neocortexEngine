package org.asciicerebrum.mydndgame.domain.core.particles;

import org.asciicerebrum.mydndgame.domain.game.Weapon;
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
public class GenericNameTest {

    private GenericName genericName;

    public GenericNameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.genericName = new GenericName("testname");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsNotInstanceTest() {
        assertFalse(this.genericName.equals(new Weapon()));
    }

    @Test
    public void equalsIdenticalInstanceTest() {
        assertTrue(this.genericName.equals(this.genericName));
    }

    @Test
    public void equalsEqualInstanceTest() {
        final GenericName testName = new GenericName("testname");
        assertTrue(this.genericName.equals(testName));
    }

    @Test
    public void equalsUnequalInstanceTest() {
        final GenericName testName = new GenericName("testname2");
        assertFalse(this.genericName.equals(testName));
    }

}
