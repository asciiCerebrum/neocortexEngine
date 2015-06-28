package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Proficiency;
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
public class CorrectProficiencyEvaluatorTest {

    private CorrectProficiencyEvaluator evaluator;

    private Proficiency proficiency;

    private WeaponServiceFacade weaponServiceFacade;

    public CorrectProficiencyEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new CorrectProficiencyEvaluator();
        this.proficiency = new Proficiency();
        this.weaponServiceFacade = mock(WeaponServiceFacade.class);

        this.evaluator.setProficiency(this.proficiency);
        this.evaluator.setWeaponServiceFacade(this.weaponServiceFacade);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateCorrectTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.weaponServiceFacade.hasProficiency(this.proficiency,
                (Weapon) contextItem, (DndCharacter) dndCharacter))
                .thenReturn(Boolean.TRUE);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }

    @Test
    public void evaluateNullProficiencyTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        this.evaluator.setProficiency(null);
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateNoWeaponTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Armor();

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateNullItemTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = null;

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

}
