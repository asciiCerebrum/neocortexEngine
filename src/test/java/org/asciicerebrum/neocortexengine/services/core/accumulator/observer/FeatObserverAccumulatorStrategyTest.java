package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import com.google.common.collect.Iterators;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observer;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Feat;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatType;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class FeatObserverAccumulatorStrategyTest {
    
    private FeatObserverAccumulatorStrategy strategy;
    
    public FeatObserverAccumulatorStrategyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.strategy = new FeatObserverAccumulatorStrategy();
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
        final Feat observerSource = new Feat();
        final FeatType featType = new FeatType();
        featType.setUniqueId(new UniqueId("feattype"));
        observerSource.setFeatType(featType);
        final UniqueEntity targetEntity = new Weapon();
        
        final Observers observers = new Observers();
        final Observer observer = new Observer();
        observers.add(observer);
        
        observerSource.setObservers(observers);
        
        final Observers result = this.strategy.getObservers(
                observerSource, targetEntity);
        
        assertEquals(1L, Iterators.size(result.iterator()));
    }
    
}
