package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class CorrectWeaponAttackModeEvaluatorTest {

    private CorrectWeaponAttackModeEvaluator evaluator;

    private SituationContextService situationContextService;

    private WeaponServiceFacade weaponServiceFacade;

    private WeaponCategory refAttackMode;

    public CorrectWeaponAttackModeEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new CorrectWeaponAttackModeEvaluator();
        this.situationContextService = mock(SituationContextService.class);
        this.weaponServiceFacade = mock(WeaponServiceFacade.class);
        this.refAttackMode = new WeaponCategory();

        this.evaluator.setSituationContextService(this.situationContextService);
        this.evaluator.setWeaponServiceFacade(this.weaponServiceFacade);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateCorrectTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.situationContextService.getItemAttackMode(
                contextItem, (DndCharacter) dndCharacter))
                .thenReturn(this.refAttackMode);
        when(this.weaponServiceFacade.isAttackModeCompatible(
                this.refAttackMode, (Weapon) contextItem,
                (DndCharacter) dndCharacter)).thenReturn(Boolean.TRUE);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }

    @Test
    public void evaluateNullAttackModeTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.situationContextService.getItemAttackMode(
                contextItem, (DndCharacter) dndCharacter))
                .thenReturn(null);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateNullItemTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = null;

        when(this.situationContextService.getItemAttackMode(
                (UniqueEntity) anyObject(), (DndCharacter) eq(dndCharacter)))
                .thenReturn(null);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateAllWrongTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Armor();

        when(this.situationContextService.getItemAttackMode(
                contextItem, (DndCharacter) dndCharacter))
                .thenReturn(null);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateNoWeaponTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Armor();

        when(this.situationContextService.getItemAttackMode(
                contextItem, (DndCharacter) dndCharacter))
                .thenReturn(this.refAttackMode);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }
}
