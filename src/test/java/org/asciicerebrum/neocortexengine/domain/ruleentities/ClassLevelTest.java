package org.asciicerebrum.neocortexengine.domain.ruleentities;

import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
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
public class ClassLevelTest {

    private ClassLevel classLevel;

    private BonusValueTuple baseAtkBoni;

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
        this.classLevel = new ClassLevel();
        this.baseAtkBoni = new BonusValueTuple();
        this.baseAtkBoni.addBonusValue(BonusRank.RANK_0, new BonusValue(13L));
        this.classLevel.setBaseAtkBoni(this.baseAtkBoni);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBaseAtkBoniDeltaNoPreviousTest() {
        final BonusValueTuple result = this.classLevel.getBaseAtkBoniDelta();

        assertEquals(13L, result.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

    @Test
    public void getBaseAtkBoniDeltaWithPreviousTest() {
        final ClassLevel prevClLvl = new ClassLevel();
        final BonusValueTuple prevBaseAtkBoni = new BonusValueTuple();
        prevBaseAtkBoni.addBonusValue(BonusRank.RANK_0, new BonusValue(8L));
        prevClLvl.setBaseAtkBoni(prevBaseAtkBoni);

        this.classLevel.setPreviousClassLevel(prevClLvl);

        final BonusValueTuple result = this.classLevel.getBaseAtkBoniDelta();

        assertEquals(5L, result.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

}
