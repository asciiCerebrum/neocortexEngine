package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import com.google.common.collect.Iterators;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observer;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.PersonalizedBodySlots;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlotsObserverAccumulatorStrategyTest {

    private PersonalizedBodySlotsObserverAccumulatorStrategy strategy;

    private ObserverAccumulatorStrategy personalizedBodySlotsStrategy;

    public PersonalizedBodySlotsObserverAccumulatorStrategyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.strategy = new PersonalizedBodySlotsObserverAccumulatorStrategy();
        this.personalizedBodySlotsStrategy
                = mock(ObserverAccumulatorStrategy.class);

        this.strategy.setPersonalizedBodySlotsStrategy(
                this.personalizedBodySlotsStrategy);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getObserversWrongInstanceTest() {
        final Ability observerSource = new Ability();
        final UniqueEntity targetEntity = new Weapon();

        final Observers result = this.strategy.getObservers(
                observerSource, targetEntity);

        assertEquals(0L, Iterators.size(result.iterator()));
    }

    @Test
    public void getObserversCorrectSizeTest() {
        final PersonalizedBodySlots observerSource
                = new PersonalizedBodySlots();
        final UniqueEntity targetEntity = new Weapon();

        final Observer subObserver = new Observer();
        final Observers subObservers = new Observers();
        subObservers.add(subObserver);
        
        observerSource.add(new PersonalizedBodySlot());
        observerSource.add(new PersonalizedBodySlot());

        when(this.personalizedBodySlotsStrategy.getObservers(
                (ObserverSource) anyObject(),
                eq(targetEntity))).thenReturn(subObservers);

        final Observers result = this.strategy.getObservers(
                observerSource, targetEntity);

        assertEquals(2L, Iterators.size(result.iterator()));
    }

}
