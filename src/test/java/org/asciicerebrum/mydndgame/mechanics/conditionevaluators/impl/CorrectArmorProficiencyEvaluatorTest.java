package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.ruleentities.ArmorPrototype;
import org.asciicerebrum.mydndgame.domain.ruleentities.Proficiency;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlots;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class CorrectArmorProficiencyEvaluatorTest {

    private CorrectArmorProficiencyEvaluator evaluator;

    private Proficiency proficiency;

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

        this.evaluator.setProficiency(this.proficiency);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateCorrectTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Armor contextItem = new Armor();

        final ArmorPrototype armorPrototype = new ArmorPrototype();
        armorPrototype.setProficiency(this.proficiency);
        contextItem.setInventoryItemPrototype(armorPrototype);

        final PersonalizedBodySlots pSlots = new PersonalizedBodySlots();
        final PersonalizedBodySlot pSlot = new PersonalizedBodySlot();
        pSlot.setHolder(dndCharacter);
        pSlot.setItem(contextItem);
        pSlots.add(pSlot);
        dndCharacter.setPersonalizedBodySlots(pSlots);

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
