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
public class StringParticleTest {

    private StringParticle string;

    public StringParticleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.string = new StringParticle() {

            @Override
            public boolean equals(Object o) {
                return this.equalsHelper(o);
            }

            @Override
            public int hashCode() {
                return this.hashCodeHelper();
            }
        };
        this.string.setValue("fact");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameInstanceTest() {
        assertTrue(this.string.equals(this.string));
    }

    @Test
    public void equalsDiffInstanceTrueTest() {
        assertTrue(this.string.equals(new EventFact("fact")));
    }

    @Test
    public void equalsDiffInstanceFalseTest() {
        assertFalse(this.string.equals(new EventFact("fact2")));
    }

    @Test
    public void equalsOtherClassTest() {
        assertFalse(this.string.equals(new ArmorClass(42L)));
    }

}
