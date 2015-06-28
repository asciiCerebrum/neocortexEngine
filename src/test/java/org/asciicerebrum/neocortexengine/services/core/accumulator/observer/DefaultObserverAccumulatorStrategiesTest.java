package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Feat;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Race;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class DefaultObserverAccumulatorStrategiesTest {

    private DefaultObserverAccumulatorStrategies strategies;

    private ObserverAccumulatorStrategy strategyA;

    private ObserverAccumulatorStrategy strategyB;

    public DefaultObserverAccumulatorStrategiesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.strategyA = new ConditionTypeObserverAccumulatorStrategy();
        this.strategyB = new FeatObserverAccumulatorStrategy();

        List<ObserverAccumulatorStrategy> strategiesList
                = new ArrayList<ObserverAccumulatorStrategy>();
        strategiesList.add(this.strategyA);
        strategiesList.add(this.strategyB);

        this.strategies = new DefaultObserverAccumulatorStrategies(
                strategiesList);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findForSourceHitTest() {
        final ObserverSource observerSource = new Feat();

        final ObserverAccumulatorStrategy strategyResult
                = this.strategies.findForSource(observerSource);
        assertEquals(this.strategyB, strategyResult);
    }

    @Test
    public void findForSourceNoHitTest() {
        final ObserverSource observerSource = new Race();

        final ObserverAccumulatorStrategy strategyResult
                = this.strategies.findForSource(observerSource);
        assertNull(strategyResult);
    }

}
