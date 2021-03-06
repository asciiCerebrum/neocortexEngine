package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponPrototype;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;
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
public class CorrectAttackModeEvaluatorTest {

    private CorrectAttackModeEvaluator evaluator;

    private SituationContextService situationContextService;

    private WeaponCategory weaponCategory;

    public CorrectAttackModeEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new CorrectAttackModeEvaluator();
        this.situationContextService = mock(SituationContextService.class);
        this.weaponCategory = new WeaponCategory();

        this.evaluator.setSituationContextService(this.situationContextService);
        this.evaluator.setWeaponCategory(this.weaponCategory);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateCorrectTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final UniqueEntity contextItem = new Weapon();
        contextItem.setUniqueId(new UniqueId("contextItem"));

        when(this.situationContextService.getItemAttackMode(
                contextItem.getUniqueId(), (DndCharacter) dndCharacter))
                .thenReturn(this.weaponCategory);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }

    @Test
    public void evaluateNullRefCategoryTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final Weapon contextItem = new Weapon();
        final WeaponPrototype proto = new WeaponPrototype();
        contextItem.setInventoryItemPrototype(proto);
        contextItem.setUniqueId(new UniqueId("contextItem"));

        when(this.situationContextService.getItemAttackMode(
                contextItem.getUniqueId(), (DndCharacter) dndCharacter))
                .thenReturn(null);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateNullCategoryTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final UniqueEntity contextItem = new Weapon();
        contextItem.setUniqueId(new UniqueId("contextItem"));

        when(this.situationContextService.getItemAttackMode(
                contextItem.getUniqueId(), (DndCharacter) dndCharacter))
                .thenReturn(this.weaponCategory);

        this.evaluator.setWeaponCategory(null);
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

}
