package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observer;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponPrototype;
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
public class InventoryItemPrototypeObserverAccumulatorStrategyTest {

    private InventoryItemPrototypeObserverAccumulatorStrategy strategy;

    private ObserverAccumulatorStrategy specialAbilitiesStrategy;

    public InventoryItemPrototypeObserverAccumulatorStrategyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.strategy = new InventoryItemPrototypeObserverAccumulatorStrategy();
        this.specialAbilitiesStrategy = mock(ObserverAccumulatorStrategy.class);

        this.strategy.setSpecialAbilitiesStrategy(
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
        final WeaponPrototype observerSource = new WeaponPrototype();
        final UniqueEntity targetEntity = new Weapon();

        final Observer subObserver = new Observer();
        final Observers subObservers = new Observers();
        subObservers.add(subObserver);

        when(this.specialAbilitiesStrategy.getObservers(
                (ObserverSource) anyObject(),
                eq(targetEntity))).thenReturn(subObservers);

        final Observers result = this.strategy.getObservers(
                observerSource, targetEntity);

        assertEquals(1L, Iterators.size(result.iterator()));
    }

}
