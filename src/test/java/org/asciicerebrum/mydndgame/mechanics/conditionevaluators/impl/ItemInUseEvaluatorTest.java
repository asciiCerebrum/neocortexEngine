package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
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
public class ItemInUseEvaluatorTest {

    private ItemInUseEvaluator evaluator;

    private SituationContextService sitConService;

    public ItemInUseEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new ItemInUseEvaluator();
        this.sitConService = mock(SituationContextService.class);

        this.evaluator.setCtxService(this.sitConService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateBothNullTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = null;

        when(this.sitConService.getActiveItem((DndCharacter) dndCharacter))
                .thenReturn(null);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }

    @Test
    public void evaluateBothEqualTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.sitConService.getActiveItem((DndCharacter) dndCharacter))
                .thenReturn((InventoryItem) contextItem);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }

    @Test
    public void evaluateBothNotEqualTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.sitConService.getActiveItem((DndCharacter) dndCharacter))
                .thenReturn((InventoryItem) new Armor());

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateContextNullTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.sitConService.getActiveItem((DndCharacter) dndCharacter))
                .thenReturn((InventoryItem) contextItem);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, null);
        assertFalse(result);
    }

    @Test
    public void evaluateActiveNullTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.sitConService.getActiveItem((DndCharacter) dndCharacter))
                .thenReturn((InventoryItem) null);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

}
