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
public class LongParticleTest {

    private LongParticle longParticle;

    public LongParticleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.longParticle = new LongParticle(42L) {

            @Override
            public boolean equals(Object o) {
                return super.equalsHelper(o);
            }

            @Override
            public int hashCode() {
                return super.hashCodeHelper();
            }
        };
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsHelperNotInstanceTest() {
        assertFalse(this.longParticle.equals(new Weapon()));
    }

    @Test
    public void lessThanOrEqualToTrueTest() {
        assertTrue(this.longParticle.lessThanOrEqualTo(new BonusValue(50L)));
    }

    @Test
    public void lessThanOrEqualToTrueEqualTest() {
        assertTrue(this.longParticle.lessThanOrEqualTo(new BonusValue(42L)));
    }

    @Test
    public void lessThanOrEqualToFalseTest() {
        assertFalse(this.longParticle.lessThanOrEqualTo(new BonusValue(41L)));
    }

    @Test
    public void lessThanTrueTest() {
        assertTrue(this.longParticle.lessThan(new BonusValue(50L)));
    }

    @Test
    public void lessThanFalseTest() {
        assertFalse(this.longParticle.lessThan(new BonusValue(41L)));
    }

}
