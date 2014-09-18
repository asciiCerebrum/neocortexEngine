/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asciicerebrum.mydndgame.observers;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusValueProvider;
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
public class SimpleArithmeticObserverTest {

    private SimpleArithmeticObserver saObserver;

    private ISituationContext sitCon;

    public SimpleArithmeticObserverTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        this.saObserver = new SimpleArithmeticObserver();
        this.sitCon = mock(ISituationContext.class);

        BonusValueProvider valProvider = mock(BonusValueProvider.class);
        when(valProvider.getDynamicValue(this.sitCon)).thenReturn(37L);

        this.saObserver.setModValueProvider(valProvider);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of triggerCallback method, of class SimpleArithmeticObserver.
     */
    @Test
    public void testTriggerCallbackAddition() {
        this.saObserver.setOperation(
                SimpleArithmeticObserver.Operation.ADDITION);

        Long result = (Long) this.saObserver.triggerCallback(42L, this.sitCon);

        assertEquals(Long.valueOf(79L), result);
    }

    @Test
    public void testTriggerCallbackMultiplication() {
        this.saObserver.setOperation(
                SimpleArithmeticObserver.Operation.MULTIPLICATION);

        Long result = (Long) this.saObserver.triggerCallback(42L, this.sitCon);

        assertEquals(Long.valueOf(1554L), result);
    }

    @Test
    public void testTriggerCallbackMinimum() {
        this.saObserver.setOperation(
                SimpleArithmeticObserver.Operation.MINIMUM);

        Long result = (Long) this.saObserver.triggerCallback(42L, this.sitCon);

        assertEquals(Long.valueOf(37L), result);
    }

    @Test
    public void testTriggerCallbackAdditionConstModValue() {
        this.saObserver.setOperation(
                SimpleArithmeticObserver.Operation.ADDITION);

        this.saObserver.setModValueProvider(null);
        this.saObserver.setModValue(41L);

        Long result = (Long) this.saObserver.triggerCallback(42L, this.sitCon);

        assertEquals(Long.valueOf(83L), result);
    }

    @Test
    public void testTriggerCallbackAdditionAllNull() {
        this.saObserver.setOperation(
                SimpleArithmeticObserver.Operation.ADDITION);

        this.saObserver.setModValueProvider(null);
        this.saObserver.setModValue(null);

        Long result = (Long) this.saObserver.triggerCallback(42L, this.sitCon);

        assertEquals(Long.valueOf(42L), result);
    }

}
