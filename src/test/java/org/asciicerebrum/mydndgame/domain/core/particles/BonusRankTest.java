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
public class BonusRankTest {

    private BonusRank rank;

    public BonusRankTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.rank = new BonusRank(42L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameClassTrueTest() {
        assertTrue(this.rank.equals(new BonusRank(42L)));
    }

    @Test
    public void equalsSameClassFalseTest() {
        assertFalse(this.rank.equals(new BonusRank(43L)));
    }

    @Test
    public void equalsDifferentClassTest() {
        assertFalse(this.rank.equals(new AbilityScore(42L)));
    }

}
