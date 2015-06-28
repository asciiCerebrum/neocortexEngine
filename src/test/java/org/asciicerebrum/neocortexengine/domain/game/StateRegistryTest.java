package org.asciicerebrum.neocortexengine.domain.game;

import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.StateRegistry.StateValueType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DamageType;
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
public class StateRegistryTest {

    private StateRegistry reg;

    private UniqueId uniqueEntity;

    public StateRegistryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.reg = new StateRegistry();
        this.uniqueEntity = new UniqueId("itemId");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getStateNullKeyTest() {
        assertNull(this.reg.getState(StateRegistry.StateParticle.ACTIVE_ITEM,
                null));
    }

    @Test
    public void getStateValueTypeForKeyTest() {
        this.reg.putState(StateRegistry.StateParticle.WEAPON_ATTACK_MODE,
                this.uniqueEntity, "yes");

        final StateValueType valType = this.reg.getStateValueTypeForKey(
                StateRegistry.StateParticle.WEAPON_ATTACK_MODE,
                this.uniqueEntity);

        assertEquals(StateValueType.STRING, valType);
    }

    @Test
    public void getStateValueTypeForKeyUniqueEntityTest() {
        this.reg.putState(StateRegistry.StateParticle.ACTIVE_ITEM,
                this.uniqueEntity);

        final StateValueType valType = this.reg.getStateValueTypeForKey(
                StateRegistry.StateParticle.ACTIVE_ITEM);

        assertEquals(StateValueType.UNIQUE_ID, valType);
    }

    @Test
    public void getStateValueTypeForKeyBeanTest() {
        this.reg.putState(StateRegistry.StateParticle.WEAPON_DAMAGE_TYPE,
                new DamageType());

        final StateValueType valType = this.reg.getStateValueTypeForKey(
                StateRegistry.StateParticle.WEAPON_DAMAGE_TYPE);

        assertEquals(StateValueType.BEAN, valType);
    }

}
