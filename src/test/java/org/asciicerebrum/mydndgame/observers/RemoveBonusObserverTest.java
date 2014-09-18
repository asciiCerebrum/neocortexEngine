package org.asciicerebrum.mydndgame.observers;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.Bonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus.ResemblanceFacet;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class RemoveBonusObserverTest {

    private RemoveBonusObserver rbObserver;

    private List<IBonus> boni;

    private ISituationContext mockSitCon;

    private ConditionEvaluator conditionEval;

    private IBonus refBonus;

    public RemoveBonusObserverTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.rbObserver = new RemoveBonusObserver();
        this.boni = new ArrayList<IBonus>();
        this.mockSitCon = mock(ISituationContext.class);
        this.conditionEval = mock(ConditionEvaluator.class);

        IBonus bonusA = new Bonus();
        IBonus bonusB = new Bonus();

        bonusA.setValue(42L);

        this.boni.add(bonusA);
        this.boni.add(bonusB);

        this.refBonus = mock(IBonus.class);

        when(this.refBonus.resembles(bonusB, null)).thenReturn(Boolean.TRUE);
        when(this.conditionEval.evaluate(this.mockSitCon))
                .thenReturn(Boolean.TRUE);

        this.rbObserver.setRemoveBonus(this.refBonus);
        this.rbObserver.setConditionEvaluator(this.conditionEval);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of trigger method, of class RemoveBonusObserver.
     */
    @Test
    public void testTrigger() {
        List<IBonus> resultBoni = (List<IBonus>) this.rbObserver
                .trigger(this.boni, this.mockSitCon);

        assertEquals(Integer.valueOf(1), Integer.valueOf(resultBoni.size()));
    }

    /**
     * Test of trigger method, of class RemoveBonusObserver.
     */
    @Test
    public void testTriggerCorrectBonus() {
        List<IBonus> resultBoni = (List<IBonus>) this.rbObserver
                .trigger(this.boni, this.mockSitCon);

        assertEquals(Long.valueOf(42L), resultBoni.get(0).getValue());
    }

    /**
     * Test of trigger method, of class RemoveBonusObserver. Condition not met.
     */
    @Test
    public void testTriggerCorrectBonusInvalidCondition() {
        when(this.conditionEval.evaluate(this.mockSitCon))
                .thenReturn(Boolean.FALSE);

        List<IBonus> resultBoni = (List<IBonus>) this.rbObserver
                .trigger(this.boni, this.mockSitCon);

        assertEquals(Integer.valueOf(2), Integer.valueOf(resultBoni.size()));
    }

    @Test
    public void testTriggerWithFacets() {
        List<ResemblanceFacet> facets = new ArrayList<ResemblanceFacet>();
        facets.add(ResemblanceFacet.BONUS_TYPE);

        this.rbObserver.setResemblanceFacets(facets);

        when(this.refBonus.resembles(this.boni.get(1), facets))
                .thenReturn(Boolean.TRUE);

        List<IBonus> resultBoni = (List<IBonus>) this.rbObserver
                .trigger(this.boni, this.mockSitCon);

        assertEquals(Integer.valueOf(1), Integer.valueOf(resultBoni.size()));
    }

}
