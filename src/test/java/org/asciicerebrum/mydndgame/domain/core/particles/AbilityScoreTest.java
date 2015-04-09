package org.asciicerebrum.mydndgame.domain.core.particles;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class AbilityScoreTest {

    private AbilityScore score;

    public AbilityScoreTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.score = new AbilityScore(42L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addNullTest() {
        this.score.add((BonusValue) null);
        assertEquals(42L, this.score.getValue());
    }

    @Test
    public void addNormalTest() {
        final BonusValue bonusVal = new BonusValue(13L);
        this.score.add(bonusVal);
        assertEquals(55L, this.score.getValue());
    }

}
