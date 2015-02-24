package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import static junit.framework.Assert.assertFalse;
import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.InventoryItemPrototype;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponPrototype;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class CorrectWeaponEvaluatorTest {

    private CorrectWeaponEvaluator evaluator;

    private Weapon weapon;

    private InventoryItemPrototype prototype;

    public CorrectWeaponEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new CorrectWeaponEvaluator();
        this.weapon = new Weapon();
        this.prototype = new WeaponPrototype();
        this.weapon.setInventoryItemPrototype(this.prototype);

        this.evaluator.setWeapon(this.weapon);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateBothDifferentTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final Weapon contextItem = new Weapon();
        contextItem.setInventoryItemPrototype(new WeaponPrototype());

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateBothSameTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final Weapon contextItem = new Weapon();
        contextItem.setInventoryItemPrototype(this.prototype);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }

    @Test
    public void evaluateNoWeaponTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final Armor contextItem = new Armor();

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateWeaponNullTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final Weapon contextItem = new Weapon();
        contextItem.setInventoryItemPrototype(this.prototype);

        this.evaluator.setWeapon(null);
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }

    @Test
    public void evaluateContextItemNullTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final Weapon contextItem = null;

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

}
