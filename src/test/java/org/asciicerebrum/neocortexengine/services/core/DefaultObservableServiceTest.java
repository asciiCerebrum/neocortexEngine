package org.asciicerebrum.neocortexengine.services.core;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observer;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.ObserverTriggerStrategy;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.neocortexengine.services.core.accumulator.observer.ObserverAccumulatorStrategies;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class DefaultObservableServiceTest {

    private DefaultObservableService service;

    private ObserverAccumulatorStrategies accumulatorStrategies;

    public DefaultObservableServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.service = new DefaultObservableService();
        this.accumulatorStrategies = mock(ObserverAccumulatorStrategies.class);

        this.service.setAccumulatorStrategies(this.accumulatorStrategies);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void triggerObserversTest() {
        final Object observerTarget = new BonusValue(10L);
        final UniqueEntity targetEntiy = new Weapon();
        final Observers observers = new Observers();
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));

        final Observer obsA = mock(Observer.class);
        final Observer obsB = mock(Observer.class);
        final Observer obsC = mock(Observer.class);
        final ConditionEvaluator eval = mock(ConditionEvaluator.class);
        final ConditionEvaluator evalFalse = mock(ConditionEvaluator.class);
        final ObserverTriggerStrategy trig
                = mock(ObserverTriggerStrategy.class);
        obsA.setConditionEvaluator(eval);
        obsA.setTriggerStrategy(trig);
        obsB.setConditionEvaluator(evalFalse);
        obsB.setTriggerStrategy(trig);
        obsC.setConditionEvaluator(null);
        obsC.setTriggerStrategy(trig);
        observers.add(obsA);
        observers.add(obsB);
        observers.add(obsC);

        when(eval.evaluate(dndCharacter, targetEntiy)).thenReturn(Boolean.TRUE);
        when(evalFalse.evaluate(dndCharacter, targetEntiy))
                .thenReturn(Boolean.FALSE);

        this.service.triggerObservers(
                observerTarget, targetEntiy, observers, dndCharacter);

        verify(trig, times(2)).trigger(observerTarget, dndCharacter,
                targetEntiy);
    }
}
