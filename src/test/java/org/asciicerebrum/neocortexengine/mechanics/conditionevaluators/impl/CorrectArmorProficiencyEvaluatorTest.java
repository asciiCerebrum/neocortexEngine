package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.Armors;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorPrototype;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Proficiency;
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
public class CorrectArmorProficiencyEvaluatorTest {

    private CorrectArmorProficiencyEvaluator evaluator;

    private Proficiency proficiency;
    
    private CharacterServiceFacade characterServiceFacade;

    public CorrectArmorProficiencyEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new CorrectArmorProficiencyEvaluator();
        this.proficiency = new Proficiency();
        this.characterServiceFacade = mock(CharacterServiceFacade.class);

        this.evaluator.setProficiency(this.proficiency);
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
        armorPrototype.setProficiency(this.proficiency);
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
    public void evaluateNullProficiencyTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Armor contextItem = new Armor();

        this.evaluator.setProficiency(null);
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

}
