package org.asciicerebrum.mydndgame.domain.core.particles;

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
public class AdvancementNumberTest {

    private AdvancementNumber advNum;

    public AdvancementNumberTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.advNum = new AdvancementNumber(42L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameClassTrueTest() {
        assertTrue(this.advNum.equals(new AdvancementNumber(42L)));
    }

    @Test
    public void equalsSameClassFalseTest() {
        assertFalse(this.advNum.equals(new AdvancementNumber(43L)));
    }

    @Test
    public void equalsDifferentClassTest() {
        assertFalse(this.advNum.equals(new AbilityScore(42L)));
    }
}
