package org.asciicerebrum.neocortexengine.mechanics.valueproviders;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.LongParticle;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorPrototype;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class ArmorBonusValueProviderTest {

    public ArmorBonusValueProvider provider;

    public ArmorBonusValueProviderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.provider = new ArmorBonusValueProvider();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getDynamicValueNoArmorTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        final LongParticle result = this.provider
                .getDynamicValue(dndCharacter, contextItem);

        assertNull(result);
    }

    @Test
    public void getDynamicValueNormalTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Armor contextItem = new Armor();

        final ArmorPrototype proto = new ArmorPrototype();
        final BonusValue bonus = new BonusValue(42L);
        proto.setArmorBonus(bonus);

        contextItem.setInventoryItemPrototype(proto);

        final LongParticle result = this.provider
                .getDynamicValue(dndCharacter, contextItem);

        assertEquals(42L, result.getValue());
    }

}
