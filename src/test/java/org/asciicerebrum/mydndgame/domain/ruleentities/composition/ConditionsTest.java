package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
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
public class ConditionsTest {
    
    private Conditions conditions;
    
    public ConditionsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.conditions = new Conditions();
        
        final Condition condA = new Condition();
        final Condition condB = new Condition();
        final Condition condC = new Condition();
        
        this.conditions.add(condA);
        this.conditions.add(condB);
        this.conditions.add(condC);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getBonusSourcesTest() {
        final BonusSources result = this.conditions.getBonusSources();
        
        assertEquals(3L, Iterators.size(result.iterator()));
    }
    
}
