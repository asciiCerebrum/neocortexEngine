package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.ruleentities.ArmorCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.ArmorPrototype;
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
public class CorrectArmorCategoryWearingEvaluatorTest {

    private CorrectArmorCategoryWearingEvaluator evaluator;

    private ArmorCategory armorCategory;

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

        this.evaluator.setArmorCategory(this.armorCategory);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateCorrectTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Armor contextItem = new Armor();

        final ArmorPrototype armorPrototype = new ArmorPrototype();
        armorPrototype.setArmorCategory(this.armorCategory);
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
    public void evaluateNullCategoryTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Armor contextItem = new Armor();

        this.evaluator.setArmorCategory(null);
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

}
