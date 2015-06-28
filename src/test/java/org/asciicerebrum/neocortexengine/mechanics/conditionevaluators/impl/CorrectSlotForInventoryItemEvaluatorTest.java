package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.facades.game.InventoryItemServiceFacade;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author species8472
 */
public class CorrectSlotForInventoryItemEvaluatorTest {

    private CorrectSlotForInventoryItemEvaluator evaluator;

    private InventoryItemServiceFacade facade;

    public CorrectSlotForInventoryItemEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new CorrectSlotForInventoryItemEvaluator();
        this.facade = mock(InventoryItemServiceFacade.class);

        this.evaluator.setInventoryItemServiceFacade(this.facade);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateNoInventoryItemTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity entity = new DndCharacter();
        entity.setUniqueId(new UniqueId("entity"));

        final boolean result = this.evaluator.evaluate(dndCharacter, entity);
        assertFalse(result);
    }

    @Test
    public void evaluateNullSlotTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity entity = new Weapon();
        entity.setUniqueId(new UniqueId("entity"));

        final boolean result = this.evaluator.evaluate(dndCharacter, entity);
        assertFalse(result);
    }

}
