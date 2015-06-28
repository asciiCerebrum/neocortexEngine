package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.Armors;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorCategory;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorPrototype;
import org.asciicerebrum.neocortexengine.facades.game.CharacterServiceFacade;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class CorrectArmorCategoryWearingEvaluatorTest {
    
    private CorrectArmorCategoryWearingEvaluator evaluator;
    
    private ArmorCategory armorCategory;
    
    private CharacterServiceFacade characterServiceFacade;
    
    public CorrectArmorCategoryWearingEvaluatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.evaluator = new CorrectArmorCategoryWearingEvaluator();
        this.armorCategory = new ArmorCategory();
        this.characterServiceFacade = mock(CharacterServiceFacade.class);
        
        this.evaluator.setArmorCategory(this.armorCategory);
        this.evaluator.setCharacterServiceFacade(this.characterServiceFacade);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void evaluateCorrectTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Armor contextItem = new Armor();
        contextItem.setUniqueId(new UniqueId("contextItem"));
        
        final ArmorPrototype armorPrototype = new ArmorPrototype();
        armorPrototype.setArmorCategory(this.armorCategory);
        contextItem.setInventoryItemPrototype(armorPrototype);
        
        final Armors armors = new Armors();
        armors.add(contextItem);
        
        when(this.characterServiceFacade.getArmorWorn(dndCharacter))
                .thenReturn(armors);
        
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }
    
    @Test
    public void evaluateNullCategoryTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Armor contextItem = new Armor();
        
        this.evaluator.setArmorCategory(null);
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }
    
}
