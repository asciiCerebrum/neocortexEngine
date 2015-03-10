package org.asciicerebrum.mydndgame.domain.core.particles;

import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class BonusValueTupleTest {

    private BonusValueTuple bonusValueTuple;

    public BonusValueTupleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.bonusValueTuple = new BonusValueTuple();

        this.bonusValueTuple.addBonusValue(BonusRank.RANK_0,
                new BonusValue(5L));
        this.bonusValueTuple.addBonusValue(BonusRank.RANK_1,
                new BonusValue(10L));
        this.bonusValueTuple.addBonusValue(BonusRank.RANK_2,
                new BonusValue(16L));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsNotInstanceTest() {
        assertFalse(this.bonusValueTuple.equals(new Weapon()));
    }

    @Test
    public void equalsTrueTest() {
        final BonusValueTuple testTuple = new BonusValueTuple();

        testTuple.addBonusValue(BonusRank.RANK_2, new BonusValue(16L));
        testTuple.addBonusValue(BonusRank.RANK_1, new BonusValue(10L));
        testTuple.addBonusValue(BonusRank.RANK_0, new BonusValue(5L));

        assertTrue(this.bonusValueTuple.equals(testTuple));
    }

    @Test
    public void equalsFalseByValueTest() {
        final BonusValueTuple testTuple = new BonusValueTuple();

        testTuple.addBonusValue(BonusRank.RANK_2, new BonusValue(16L));
        testTuple.addBonusValue(BonusRank.RANK_1, new BonusValue(11L));
        testTuple.addBonusValue(BonusRank.RANK_0, new BonusValue(5L));

        assertFalse(this.bonusValueTuple.equals(testTuple));
    }

    @Test
    public void equalsFalseByRankTest() {
        final BonusValueTuple testTuple = new BonusValueTuple();

        testTuple.addBonusValue(BonusRank.RANK_2, new BonusValue(16L));
        testTuple.addBonusValue(BonusRank.RANK_3, new BonusValue(10L));
        testTuple.addBonusValue(BonusRank.RANK_0, new BonusValue(5L));

        assertFalse(this.bonusValueTuple.equals(testTuple));
    }

    @Test
    public void subtractNoSubtrahendTest() {
        final BonusValueTuple testTuple = new BonusValueTuple();

        testTuple.addBonusValue(BonusRank.RANK_2, new BonusValue(16L));
        testTuple.addBonusValue(BonusRank.RANK_0, new BonusValue(5L));

        final BonusValueTuple result = this.bonusValueTuple.subtract(testTuple);

        assertEquals(10L, result.getBonusValueByRank(BonusRank.RANK_1)
                .getValue());
    }

    @Test
    public void addNullTest() {
        this.bonusValueTuple.add((BonusValueTuple) null);

        assertEquals(10L, this.bonusValueTuple
                .getBonusValueByRank(BonusRank.RANK_1).getValue());
    }

    @Test
    public void addSimpleValueRank1Test() {
        this.bonusValueTuple.add(new BonusValue(7L));

        assertEquals(17L, this.bonusValueTuple
                .getBonusValueByRank(BonusRank.RANK_1).getValue());
    }

    @Test
    public void addSimpleValueRank2Test() {
        this.bonusValueTuple.add(new BonusValue(7L));

        assertEquals(23L, this.bonusValueTuple
                .getBonusValueByRank(BonusRank.RANK_2).getValue());
    }

    @Test
    public void addSimpleValueRank0Test() {
        this.bonusValueTuple.add(new BonusValue(7L));

        assertEquals(12L, this.bonusValueTuple
                .getBonusValueByRank(BonusRank.RANK_0).getValue());
    }

}
