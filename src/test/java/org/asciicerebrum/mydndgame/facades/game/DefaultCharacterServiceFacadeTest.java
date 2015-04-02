package org.asciicerebrum.mydndgame.facades.game;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlots;
import org.asciicerebrum.mydndgame.services.context.EntityPoolService;
import org.asciicerebrum.mydndgame.services.context.MapBasedEntityPoolService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class DefaultCharacterServiceFacadeTest {

    private DefaultCharacterServiceFacade facade;

    private EntityPoolService entityPoolService;

    public DefaultCharacterServiceFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.facade = new DefaultCharacterServiceFacade();
        this.entityPoolService = new MapBasedEntityPoolService();

        this.facade.setEntityPoolService(this.entityPoolService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getItemsWornNormalSizeTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));

        final PersonalizedBodySlots slots = new PersonalizedBodySlots();
        dndCharacter.setPersonalizedBodySlots(slots);

        final Weapon weaponA = new Weapon();
        weaponA.setUniqueId(new UniqueId("itemA"));

        final Weapon weaponB = new Weapon();
        weaponB.setUniqueId(new UniqueId("itemB"));

        final PersonalizedBodySlot slotA = new PersonalizedBodySlot();
        slotA.setItemId(new UniqueId("itemA"));
        final PersonalizedBodySlot slotB = new PersonalizedBodySlot();
        slotB.setItemId(new UniqueId("itemB"));

        slots.add(slotA);
        slots.add(slotB);

        this.entityPoolService.registerUniqueEntity(dndCharacter);
        this.entityPoolService.registerUniqueEntity(weaponA);
        this.entityPoolService.registerUniqueEntity(weaponB);

        assertEquals(2L, Iterators.size(
                this.facade.getItemsWorn(dndCharacter).iterator()));
    }

    @Test
    public void getArmorWornNormalSizeTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));

        final PersonalizedBodySlots slots = new PersonalizedBodySlots();
        dndCharacter.setPersonalizedBodySlots(slots);

        final Weapon weaponA = new Weapon();
        weaponA.setUniqueId(new UniqueId("itemA"));

        final Armor armorB = new Armor();
        armorB.setUniqueId(new UniqueId("itemB"));

        final PersonalizedBodySlot slotA = new PersonalizedBodySlot();
        slotA.setItemId(new UniqueId("itemA"));
        final PersonalizedBodySlot slotB = new PersonalizedBodySlot();
        slotB.setItemId(new UniqueId("itemB"));

        slots.add(slotA);
        slots.add(slotB);

        this.entityPoolService.registerUniqueEntity(dndCharacter);
        this.entityPoolService.registerUniqueEntity(weaponA);
        this.entityPoolService.registerUniqueEntity(armorB);

        assertEquals(1L, Iterators.size(
                this.facade.getArmorWorn(dndCharacter).iterator()));
    }

}
