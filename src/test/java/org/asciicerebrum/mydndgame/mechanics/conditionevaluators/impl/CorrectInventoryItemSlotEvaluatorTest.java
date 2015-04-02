package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotType;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlots;
import org.asciicerebrum.mydndgame.facades.game.InventoryItemServiceFacade;
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
