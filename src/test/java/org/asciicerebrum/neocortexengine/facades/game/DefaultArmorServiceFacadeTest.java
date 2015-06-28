package org.asciicerebrum.neocortexengine.facades.game;

import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.Armors;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHooks;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorPrototype;
import org.asciicerebrum.neocortexengine.services.core.ObservableService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
public class DefaultArmorServiceFacadeTest {

    private DefaultArmorServiceFacade facade;

    private ObservableService observableService;

    public DefaultArmorServiceFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.facade = new DefaultArmorServiceFacade();
        this.observableService = mock(ObservableService.class);

        this.facade.setObservableService(this.observableService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getMinimumArmorCheckPenaltyTest() {
        final Armors armors = new Armors();
        final DndCharacter dndCharacter = new DndCharacter();

        final Armor armorA = new Armor();
        final ArmorPrototype protoA = new ArmorPrototype();
        final BonusValue penaltyA = new BonusValue(-6L);
        protoA.setArmorCheckPenalty(penaltyA);
        armorA.setInventoryItemPrototype(protoA);

        final Armor armorB = new Armor();
        final ArmorPrototype protoB = new ArmorPrototype();
        final BonusValue penaltyB = new BonusValue(-4L);
        protoB.setArmorCheckPenalty(penaltyB);
        armorB.setInventoryItemPrototype(protoB);

        armors.add(armorA);
        armors.add(armorB);

        when(this.observableService.triggerObservers(
                eq(penaltyA),
                eq(armorA),
                (ObserverSources) anyObject(),
                (ObserverHooks) anyObject(),
                eq(dndCharacter))).thenReturn(penaltyA);
        when(this.observableService.triggerObservers(
                eq(penaltyB),
                eq(armorB),
                (ObserverSources) anyObject(),
                (ObserverHooks) anyObject(),
                eq(dndCharacter))).thenReturn(penaltyB);

        final BonusValue result = this.facade.getMinimumArmorCheckPenalty(
                armors, dndCharacter);

        assertEquals(-6L, result.getValue());
    }

    @Test
    public void getMinimumMaxDexBonusTest() {
        final Armors armors = new Armors();
        final DndCharacter dndCharacter = new DndCharacter();

        final Armor armorA = new Armor();
        final ArmorPrototype protoA = new ArmorPrototype();
        final BonusValue maxdexA = new BonusValue(10L);
        protoA.setMaxDexBonus(maxdexA);
        armorA.setInventoryItemPrototype(protoA);

        final Armor armorB = new Armor();
        final ArmorPrototype protoB = new ArmorPrototype();
        final BonusValue maxdexB = new BonusValue(12L);
        protoB.setMaxDexBonus(maxdexB);
        armorB.setInventoryItemPrototype(protoB);

        armors.add(armorA);
        armors.add(armorB);

        when(this.observableService.triggerObservers(
                eq(maxdexA),
                eq(armorA),
                (ObserverSources) anyObject(),
                (ObserverHooks) anyObject(),
                eq(dndCharacter))).thenReturn(maxdexA);
        when(this.observableService.triggerObservers(
                eq(maxdexB),
                eq(armorB),
                (ObserverSources) anyObject(),
                (ObserverHooks) anyObject(),
                eq(dndCharacter))).thenReturn(maxdexB);

        final BonusValue result = this.facade.getMinimumMaxDexBonus(
                armors, dndCharacter);

        assertEquals(10L, result.getValue());
    }
}
