package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.InventoryItem;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.BodySlot;
import org.asciicerebrum.neocortexengine.domain.ruleentities.BodySlotType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.PersonalizedBodySlots;
import org.asciicerebrum.neocortexengine.facades.game.InventoryItemServiceFacade;
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
public class CorrectInventoryItemSlotEvaluatorTest {

    private CorrectInventoryItemSlotEvaluator evaluator;

    private InventoryItemServiceFacade iiServiceFacade;

    public CorrectInventoryItemSlotEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new CorrectInventoryItemSlotEvaluator();
        this.iiServiceFacade = mock(InventoryItemServiceFacade.class);

        this.evaluator.setItemFacade(this.iiServiceFacade);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateCorrectTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();
        contextItem.setUniqueId(new UniqueId("contextItem"));

        final PersonalizedBodySlots pSlots = new PersonalizedBodySlots();
        final PersonalizedBodySlot pSlot = new PersonalizedBodySlot();
        final BodySlot slot = new BodySlot();
        final BodySlotType slotType = new BodySlotType();
        slot.setBodySlotType(slotType);
        pSlot.setItemId(contextItem.getUniqueId());
        pSlot.setBodySlot(slot);
        pSlots.add(pSlot);
        dndCharacter.setPersonalizedBodySlots(pSlots);

        when(this.iiServiceFacade.isCorrectlyWielded(slotType,
                (InventoryItem) contextItem, dndCharacter))
                .thenReturn(Boolean.TRUE);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }

    @Test
    public void evaluateNoSlotTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();
        contextItem.setUniqueId(new UniqueId("contextItem"));

        final PersonalizedBodySlots pSlots = new PersonalizedBodySlots();
        final PersonalizedBodySlot pSlot = new PersonalizedBodySlot();
        final BodySlot slot = new BodySlot();
        final BodySlotType slotType = new BodySlotType();
        slot.setBodySlotType(slotType);
        pSlot.setItemId(null);
        pSlot.setBodySlot(slot);
        pSlots.add(pSlot);
        dndCharacter.setPersonalizedBodySlots(pSlots);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateNoInventoryItemTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new DndCharacter();

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

}
