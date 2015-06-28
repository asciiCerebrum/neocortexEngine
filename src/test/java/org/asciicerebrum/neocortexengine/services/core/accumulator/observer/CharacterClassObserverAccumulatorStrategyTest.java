package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import com.google.common.collect.Iterators;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observer;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;
import org.asciicerebrum.neocortexengine.domain.ruleentities.CharacterClass;
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
public class CharacterClassObserverAccumulatorStrategyTest {
    
    private CharacterClassObserverAccumulatorStrategy strategy;
    
    private ObserverAccumulatorStrategy classFeatsStrategy;
    
    public CharacterClassObserverAccumulatorStrategyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.strategy = new CharacterClassObserverAccumulatorStrategy();
        this.classFeatsStrategy = mock(ObserverAccumulatorStrategy.class);
        
        this.strategy.setClassFeatsStrategy(this.classFeatsStrategy);
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
        final CharacterClass observerSource = new CharacterClass();
        observerSource.setId(new UniqueId("characterClass"));
        final UniqueEntity targetEntity = new Weapon();
        
        final Observer subObserver = new Observer();
        final Observers subObservers = new Observers();
        subObservers.add(subObserver);
        
        when(this.classFeatsStrategy.getObservers(
                (ObserverSource) anyObject(),
                eq(targetEntity))).thenReturn(subObservers);
        
        final Observers result = this.strategy.getObservers(
                observerSource, targetEntity);
        
        assertEquals(1L, Iterators.size(result.iterator()));
    }
    
}
