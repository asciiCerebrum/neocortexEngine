package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observer;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.services.context.EntityPoolService;
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
public class PersonalizedBodySlotObserverAccumulatorStrategyTest {

    private PersonalizedBodySlotObserverAccumulatorStrategy strategy;

    private ObserverAccumulatorStrategy itemStrategy;

    private EntityPoolService entityPoolService;

    public PersonalizedBodySlotObserverAccumulatorStrategyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.strategy = new PersonalizedBodySlotObserverAccumulatorStrategy();
        this.itemStrategy = mock(ObserverAccumulatorStrategy.class);
        this.entityPoolService = mock(EntityPoolService.class);

        this.strategy.setItemStrategy(this.itemStrategy);
        this.strategy.setEntityPoolService(this.entityPoolService);
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
        final PersonalizedBodySlot observerSource = new PersonalizedBodySlot();
        final UniqueEntity targetEntity = new Weapon();
        targetEntity.setUniqueId(new UniqueId("weapon"));

        observerSource.setItemId(targetEntity.getUniqueId());

        final Observer subObserver = new Observer();
        final Observers subObservers = new Observers();
        subObservers.add(subObserver);

        when(this.itemStrategy.getObservers(
                (ObserverSource) anyObject(),
                eq(targetEntity))).thenReturn(subObservers);

        final Observers result = this.strategy.getObservers(
                observerSource, targetEntity);

        assertEquals(1L, Iterators.size(result.iterator()));
    }

}
