package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class DefaultObservableDelegateTest {

    private DefaultObservableDelegate defObsDelegate;

    private IObserver observer;

    private Map<ObserverHook, List<IObserver>> observerMap;

    private Object someObject;

    private ISituationContext sitCon;

    public DefaultObservableDelegateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.defObsDelegate = new DefaultObservableDelegate();
        this.observer = mock(IObserver.class);
        this.sitCon = mock(ISituationContext.class);

        when(this.observer.trigger(this.someObject, this.sitCon))
                .thenReturn(this.someObject);

        this.observerMap = new EnumMap<ObserverHook, List<IObserver>>(
                ObserverHook.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of registerListener method, of class DefaultObservableDelegate.
     */
    @Test
    public void testRegisterListener() {
        this.defObsDelegate.registerListener(
                ObserverHook.PRICE, this.observer, this.observerMap);

        assertEquals(this.observer,
                this.observerMap.get(ObserverHook.PRICE).get(0));
    }

    @Test
    public void testRegisterListenerNonNullHookList() {
        this.observerMap.put(ObserverHook.PRICE, new ArrayList<IObserver>());

        this.defObsDelegate.registerListener(
                ObserverHook.PRICE, this.observer, this.observerMap);

        assertEquals(this.observer,
                this.observerMap.get(ObserverHook.PRICE).get(0));
    }

    /**
     * Test of unregisterListener method, of class DefaultObservableDelegate.
     */
    @Test
    public void testUnregisterListener() {
        this.observerMap.put(ObserverHook.PRICE, new ArrayList<IObserver>() {
            {
                this.add(observer);
            }
        });

        this.defObsDelegate.unregisterListener(
                ObserverHook.PRICE, this.observer, this.observerMap);

        assertTrue(this.observerMap.get(ObserverHook.PRICE).isEmpty());
    }

    @Test
    public void testUnregisterListenerNullList() {
        this.observerMap.put(ObserverHook.PRICE, null);

        this.defObsDelegate.unregisterListener(
                ObserverHook.PRICE, this.observer, this.observerMap);

        assertNull(this.observerMap.get(ObserverHook.PRICE));
    }

    /**
     * Test of triggerObservers method, of class DefaultObservableDelegate.
     */
    @Test
    public void testTriggerObservers() {
        this.observerMap.put(ObserverHook.PRICE, new ArrayList<IObserver>() {
            {
                this.add(observer);
            }
        });

        Object resultObject = this.defObsDelegate.triggerObservers(
                ObserverHook.PRICE, this.someObject, this.observerMap,
                this.sitCon);

        assertEquals(this.someObject, resultObject);
    }

    @Test
    public void testTriggerObserversNullList() {
        this.observerMap.put(ObserverHook.PRICE, null);

        Object resultObject = this.defObsDelegate.triggerObservers(
                ObserverHook.PRICE, this.someObject, this.observerMap,
                this.sitCon);

        assertEquals(this.someObject, resultObject);
    }

}
