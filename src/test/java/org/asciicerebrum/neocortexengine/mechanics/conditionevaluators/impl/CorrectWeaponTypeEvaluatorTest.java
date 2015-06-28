package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponType;
import org.asciicerebrum.neocortexengine.facades.game.WeaponServiceFacade;
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
public class CorrectWeaponTypeEvaluatorTest {

    private CorrectWeaponTypeEvaluator evaluator;

    private WeaponServiceFacade weaponServiceFacade;

    private WeaponType weaponType;

    public CorrectWeaponTypeEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new CorrectWeaponTypeEvaluator();
        this.weaponServiceFacade = mock(WeaponServiceFacade.class);
        this.weaponType = new WeaponType();

        this.evaluator.setWeaponServiceFacade(this.weaponServiceFacade);
        this.evaluator.setWeaponType(this.weaponType);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateCorrectTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final Weapon contextItem = new Weapon();

        when(this.weaponServiceFacade.hasWeaponType(this.weaponType,
                contextItem, (DndCharacter) dndCharacter))
                .thenReturn(Boolean.TRUE);

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
    public void evaluateNullTypeTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final Weapon contextItem = new Weapon();

        this.evaluator.setWeaponType(null);
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateNullItemTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final Weapon contextItem = null;

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

}
