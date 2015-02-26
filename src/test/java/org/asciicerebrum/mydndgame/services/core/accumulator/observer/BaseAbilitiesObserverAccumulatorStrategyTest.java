package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.AbilityScore;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observer;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.BaseAbilities;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.BaseAbilityEntry;
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
public class BaseAbilitiesObserverAccumulatorStrategyTest {

    private BaseAbilitiesObserverAccumulatorStrategy accumulatorStrategy;

    private ObserverAccumulatorStrategy abilityStrategy;

    public BaseAbilitiesObserverAccumulatorStrategyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.accumulatorStrategy
                = new BaseAbilitiesObserverAccumulatorStrategy();
        this.abilityStrategy = mock(ObserverAccumulatorStrategy.class);

        this.accumulatorStrategy.setAbilityStrategy(this.abilityStrategy);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getObserversWrongInstanceTest() {
        final Ability observerSource = new Ability();
        final UniqueEntity targetEntity = new Weapon();

        final Observers result = this.accumulatorStrategy.getObservers(
                observerSource, targetEntity);

        assertEquals(0L, Iterators.size(result.iterator()));
    }

    @Test
    public void getObserversCorrectSizeTest() {
        final BaseAbilities observerSource = new BaseAbilities();
        final UniqueEntity targetEntity = new Weapon();
        final Ability abilityA = new Ability();
        final Ability abilityB = new Ability();
        final BaseAbilityEntry entryA = new BaseAbilityEntry();
        entryA.setAbility(abilityA);
        entryA.setAbilityValue(new AbilityScore(10L));
        final BaseAbilityEntry entryB = new BaseAbilityEntry();
        entryB.setAbility(abilityB);
        entryB.setAbilityValue(new AbilityScore(10L));
        observerSource.addBaseAbilityEntry(entryA);
        observerSource.addBaseAbilityEntry(entryB);

        final Observer subObserver = new Observer();
        final Observers subObservers = new Observers();
        subObservers.add(subObserver);

        when(this.abilityStrategy.getObservers((ObserverSource) anyObject(),
                eq(targetEntity))).thenReturn(subObservers);

        final Observers result = this.accumulatorStrategy.getObservers(
                observerSource, targetEntity);

        assertEquals(2L, Iterators.size(result.iterator()));
    }
}
