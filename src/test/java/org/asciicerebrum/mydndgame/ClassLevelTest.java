package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ILevel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author species8472
 */
public class ClassLevelTest {

    private ClassLevel cLevel;
    private Bonus bonusRank1;

    @Mock
    private ILevel prevCLevel;

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
        MockitoAnnotations.initMocks(this);

        Bonus prevBonusRank1 = new Bonus();
        prevBonusRank1.setValue(7L);
        prevBonusRank1.setRank(1L);

        when(this.prevCLevel.getBaseAtkBonusByRank(1L))
                .thenReturn(prevBonusRank1);

        this.cLevel = new ClassLevel();
        List<IBonus> rankedBoni = new ArrayList<IBonus>();
        Bonus bonusRank0 = new Bonus();
        bonusRank0.setRank(0L);
        this.bonusRank1 = new Bonus();
        this.bonusRank1.setRank(1L);
        this.bonusRank1.setValue(3L);
        Bonus bonusRank2 = new Bonus();
        bonusRank2.setRank(2L);
        bonusRank2.setValue(13L);
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

        IBonus resultBonus = this.cLevel.getBaseAtkBonusByRank(1L);

        assertEquals(this.bonusRank1, resultBonus);
    }

    /**
     * Test of getBaseAtkBonusByRank method, of class ClassLevel. Rank out of
     * range.
     */
    @Test
    public void testGetBaseAtkBonusByRankOutOfRange() {

        IBonus resultBonus = this.cLevel.getBaseAtkBonusByRank(5L);

        assertNull(resultBonus);
    }

    /**
     * Delta value without previous class level.
     */
    @Test
    public void testGetBaseAtkBonusValueDeltaByRankWithoutPrevCLevel() {
        Long deltaVal = this.cLevel.getBaseAtkBonusValueDeltaByRank(1L);

        assertEquals(Long.valueOf(3), deltaVal);
    }

    @Test
    public void testGetBaseAtkBonusValueDeltaByRank() {
        this.cLevel.setPreviousClassLevel(this.prevCLevel);
        Long deltaVal = this.cLevel.getBaseAtkBonusValueDeltaByRank(1L);

        assertEquals(Long.valueOf(-4), deltaVal);
    }

    /**
     * Delta value without the same rank in the previous class level.
     */
    @Test
    public void testGetBaseAtkBonusValueDeltaByRankWithoutRank() {
        this.cLevel.setPreviousClassLevel(this.prevCLevel);
        Long deltaVal = this.cLevel.getBaseAtkBonusValueDeltaByRank(2L);

        assertEquals(Long.valueOf(13), deltaVal);

    }

}
