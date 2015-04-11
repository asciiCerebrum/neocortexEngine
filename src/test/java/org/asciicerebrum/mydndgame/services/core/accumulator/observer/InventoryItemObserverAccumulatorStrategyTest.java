package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observer;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
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
public class InventoryItemObserverAccumulatorStrategyTest {

    private InventoryItemObserverAccumulatorStrategy strategy;

    private ObserverAccumulatorStrategy specialAbilitiesStrategy;

    public InventoryItemObserverAccumulatorStrategyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.strategy = new InventoryItemObserverAccumulatorStrategy();
        this.specialAbilitiesStrategy = mock(ObserverAccumulatorStrategy.class);

        this.strategy.setSpecialAbilitiesStrategy(
                this.specialAbilitiesStrategy);
        this.strategy.setInventoryItemPrototypeStrategy(
                this.specialAbilitiesStrategy);
        this.strategy.setConditionsStrategy(
                this.specialAbilitiesStrategy);
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
        final Weapon observerSource = new Weapon();
        final UniqueEntity targetEntity = new Weapon();
        observerSource.setUniqueId(new UniqueId("sourceWeapon"));
        targetEntity.setUniqueId(new UniqueId("targetWeapon"));

        final Observer subObserverA = new Observer();
        final Observer subObserverB = new Observer();
        subObserverB.setScope(Observer.ObserverScope.SPECIFIC);
        final Observers subObservers = new Observers();
        subObservers.add(subObserverA);
        subObservers.add(subObserverB);

        when(this.specialAbilitiesStrategy.getObservers(
                (ObserverSource) anyObject(),
                eq(targetEntity))).thenReturn(subObservers);

        final Observers result = this.strategy.getObservers(
                observerSource, targetEntity);

        assertEquals(3L, Iterators.size(result.iterator()));
    }

    @Test
    public void getObserversCorrectSizeSourceIsTargetTest() {
        final Weapon observerSource = new Weapon();
        final UniqueEntity targetEntity = observerSource;
        observerSource.setUniqueId(new UniqueId("sourceWeapon"));

        final Observer subObserverA = new Observer();
        final Observer subObserverB = new Observer();
        subObserverB.setScope(Observer.ObserverScope.SPECIFIC);
        final Observers subObservers = new Observers();
        subObservers.add(subObserverA);
        subObservers.add(subObserverB);

        when(this.specialAbilitiesStrategy.getObservers(
                (ObserverSource) anyObject(),
                eq(targetEntity))).thenReturn(subObservers);

        final Observers result = this.strategy.getObservers(
                observerSource, targetEntity);

        assertEquals(6L, Iterators.size(result.iterator()));
    }

}
