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
public class ExperiencePointsTest {

    private ExperiencePoints xp;

    public ExperiencePointsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.xp = new ExperiencePoints("42");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameClassTrueTest() {
        assertTrue(this.xp.equals(new ExperiencePoints("42")));
    }

    @Test
    public void equalsSameClassFalseTest() {
        assertFalse(this.xp.equals(new ExperiencePoints("43")));
    }

    @Test
    public void equalsDifferentClassTest() {
        assertFalse(this.xp.equals(new AbilityScore(42L)));
    }

}
