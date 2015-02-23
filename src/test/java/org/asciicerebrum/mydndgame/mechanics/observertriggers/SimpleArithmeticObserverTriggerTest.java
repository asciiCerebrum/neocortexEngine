/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asciicerebrum.mydndgame.mechanics.observertriggers;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.LongParticle;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.DynamicValueProvider;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class SimpleArithmeticObserverTriggerTest {

    private SimpleArithmeticObserverTrigger trigger;

    private LongParticle modValue;

    private DynamicValueProvider dynValProvider;

    public SimpleArithmeticObserverTriggerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.trigger = new SimpleArithmeticObserverTrigger();
        this.modValue = new BonusValue(3L);
        this.dynValProvider = mock(DynamicValueProvider.class);

        this.trigger.setModValue(this.modValue);
        this.trigger.setModValueProvider(this.dynValProvider);
        this.trigger.setOperation(LongParticle.Operation.ADDITION);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void triggerNormalOperationTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();
        final LongParticle object = new BonusValue(8L);

        when(this.dynValProvider.getDynamicValue(dndCharacter, contextItem))
                .thenReturn(new BonusValue(2L));

        Object testResult = this.trigger.trigger(
                object, dndCharacter, contextItem);
        assertEquals(10L, ((LongParticle) testResult).getValue());
    }

    @Test
    public void triggerNoProviderTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();
        final LongParticle object = new BonusValue(8L);

        this.trigger.setModValueProvider(null);

        Object testResult = this.trigger.trigger(
                object, dndCharacter, contextItem);
        assertEquals(11L, ((LongParticle) testResult).getValue());
    }

    @Test
    public void triggerNothingGivenTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();
        final LongParticle object = new BonusValue(8L);

        this.trigger.setModValueProvider(null);
        this.trigger.setModValue(null);

        Object testResult = this.trigger.trigger(
                object, dndCharacter, contextItem);
        assertEquals(8L, ((LongParticle) testResult).getValue());
    }

}
