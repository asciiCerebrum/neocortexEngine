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
public class BooleanParticleTest {

    private BooleanParticle booleanParticle;

    public BooleanParticleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.booleanParticle = new BooleanParticle(true);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsNotInstanceTest() {
        assertFalse(this.booleanParticle.equals(new Weapon()));
    }

    @Test
    public void equalsIdenticalInstanceTest() {
        assertTrue(this.booleanParticle.equals(this.booleanParticle));
    }

}
