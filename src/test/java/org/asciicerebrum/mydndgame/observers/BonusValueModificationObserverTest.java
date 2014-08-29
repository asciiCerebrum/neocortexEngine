package org.asciicerebrum.mydndgame.observers;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonusType;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class BonusValueModificationObserverTest {

    private BonusValueModificationObserver bonusValueModificationObserver;

    private List<IBonus> bonusList;

    private ISituationContext sitCon;

    private IBonus referenceBonus;

    public BonusValueModificationObserverTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.referenceBonus = mock(IBonus.class);

        BonusTarget target = mock(BonusTarget.class);
        IBonusType bonusType = mock(IBonusType.class);

        when(this.referenceBonus.getTarget()).thenReturn(target);
        when(this.referenceBonus.getBonusType()).thenReturn(bonusType);

        this.bonusValueModificationObserver
                = new BonusValueModificationObserver();

        this.bonusValueModificationObserver.setModValue(5.0D);
        this.bonusValueModificationObserver.setOperation(
                BonusValueModificationObserver.Operation.MULTIPLICATION);
        this.bonusValueModificationObserver.setReferenceBonus(
                this.referenceBonus);

        this.sitCon = mock(ISituationContext.class);

        this.bonusList = new ArrayList<IBonus>();

        IBonus checkBonus = mock(IBonus.class);
        when(checkBonus.getEffectiveValue(this.sitCon)).thenReturn(5L);
        when(checkBonus.getTarget()).thenReturn(target);
        when(checkBonus.getBonusType()).thenReturn(bonusType);

        this.bonusList.add(checkBonus);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of triggerCallback method, of class BonusValueModificationObserver.
     */
    @Test
    public void testTriggerCallbackResultSize() {
        List<IBonus> resultBoni
                = (List<IBonus>) this.bonusValueModificationObserver
                .triggerCallback(this.bonusList, this.sitCon);

        assertEquals(Integer.valueOf(1), Integer.valueOf(resultBoni.size()));
    }

    @Test
    public void testTriggerCallbackBonusValue() {
        List<IBonus> resultBoni
                = (List<IBonus>) this.bonusValueModificationObserver
                .triggerCallback(this.bonusList, this.sitCon);

        verify(resultBoni.get(0)).setValue(25L);
    }

    @Test
    public void testTriggerCallbackNoDynProvider() {
        List<IBonus> resultBoni
                = (List<IBonus>) this.bonusValueModificationObserver
                .triggerCallback(this.bonusList, this.sitCon);

        verify(resultBoni.get(0)).setDynamicValueProvider(null);
    }

    @Test
    public void testTriggerCallbackNoRefBonus() {
        this.bonusValueModificationObserver.setReferenceBonus(null);
        List<IBonus> resultBoni
                = (List<IBonus>) this.bonusValueModificationObserver
                .triggerCallback(this.bonusList, this.sitCon);

        verify(resultBoni.get(0), never()).setValue(anyLong());
    }

    @Test
    public void testTriggerCallbackWrongBonusType() {
        when(this.referenceBonus.getBonusType())
                .thenReturn(mock(IBonusType.class));
        List<IBonus> resultBoni
                = (List<IBonus>) this.bonusValueModificationObserver
                .triggerCallback(this.bonusList, this.sitCon);

        verify(resultBoni.get(0), never()).setValue(anyLong());
    }

    @Test
    public void testTriggerCallbackWrongTarget() {
        when(this.referenceBonus.getTarget())
                .thenReturn(mock(BonusTarget.class));
        List<IBonus> resultBoni
                = (List<IBonus>) this.bonusValueModificationObserver
                .triggerCallback(this.bonusList, this.sitCon);

        verify(resultBoni.get(0), never()).setValue(anyLong());
    }

    @Test
    public void testTriggerCallbackBonusValueDivision() {
        this.bonusValueModificationObserver.setModValue(6.0D);
        this.bonusValueModificationObserver.setOperation(
                BonusValueModificationObserver.Operation.DIVISION);

        List<IBonus> resultBoni
                = (List<IBonus>) this.bonusValueModificationObserver
                .triggerCallback(this.bonusList, this.sitCon);

        verify(resultBoni.get(0)).setValue(0L);
    }

}
