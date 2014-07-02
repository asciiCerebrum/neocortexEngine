package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
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
public class ClassLevelTest {

    private ClassLevel cLevel;
    private Bonus bonusRank1;

    public ClassLevelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.cLevel = new ClassLevel();
        List<Bonus> rankedBoni = new ArrayList<Bonus>();
        Bonus bonusRank0 = new Bonus();
        bonusRank0.setRank(0L);
        this.bonusRank1 = new Bonus();
        this.bonusRank1.setRank(1L);
        Bonus bonusRank2 = new Bonus();
        bonusRank2.setRank(2L);
        rankedBoni.add(bonusRank0);
        rankedBoni.add(this.bonusRank1);
        rankedBoni.add(bonusRank2);

        this.cLevel.setBaseAtkBoni(rankedBoni);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getBaseAtkBonusByRank method, of class ClassLevel.
     */
    @Test
    public void testGetBaseAtkBonusByRank() {

        Bonus resultBonus = this.cLevel.getBaseAtkBonusByRank(1L);

        assertEquals(this.bonusRank1, resultBonus);
    }

    /**
     * Test of getBaseAtkBonusByRank method, of class ClassLevel. Rank out of
     * range.
     */
    @Test
    public void testGetBaseAtkBonusByRankOutOfRange() {

        Bonus resultBonus = this.cLevel.getBaseAtkBonusByRank(5L);

        assertNull(resultBonus);
    }

}
