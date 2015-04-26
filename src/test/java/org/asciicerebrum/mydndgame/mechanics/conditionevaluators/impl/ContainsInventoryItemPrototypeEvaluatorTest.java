package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.InventoryItemPrototype;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponPrototype;
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
public class ContainsInventoryItemPrototypeEvaluatorTest {

    private ContainsInventoryItemPrototypeEvaluator evaluator;

    private WeaponPrototype prototype;

    public ContainsInventoryItemPrototypeEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new ContainsInventoryItemPrototypeEvaluator();

        this.prototype = new WeaponPrototype();
        final List<InventoryItemPrototype> protoList
                = new ArrayList<InventoryItemPrototype>();
        protoList.add(this.prototype);

        this.evaluator.setCheckList(protoList);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateWrongInstanceTest() {
        assertFalse(this.evaluator.evaluate(
                new DndCharacter(), new DndCharacter()));
    }

    @Test
    public void evaluateNormalNotInListTest() {
        final Weapon weapon = new Weapon();
        weapon.setInventoryItemPrototype(new WeaponPrototype());
        assertFalse(this.evaluator.evaluate(new DndCharacter(), weapon));
    }

    @Test
    public void evaluateNormalInListTest() {
        final Weapon weapon = new Weapon();
        weapon.setInventoryItemPrototype(this.prototype);
        assertTrue(this.evaluator.evaluate(new DndCharacter(), weapon));
    }

}
