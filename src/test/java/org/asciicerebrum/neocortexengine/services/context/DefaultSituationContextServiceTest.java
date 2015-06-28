package org.asciicerebrum.neocortexengine.services.context;

import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.StateRegistry;
import org.asciicerebrum.neocortexengine.domain.game.StateRegistry.StateParticle;
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
public class DefaultSituationContextServiceTest {

    private DefaultSituationContextService service;

    public DefaultSituationContextServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.service = new DefaultSituationContextService();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBonusValueForKeyNullStateTest() {
        final StateParticle stateParticle
                = StateRegistry.StateParticle.POWER_ATTACK_BONUS;
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("dndCharacter"));
        final StateRegistry reg = new StateRegistry();
        dndCharacter.setStateRegistry(reg);
        final UniqueId unqiueId = new UniqueId("weapon");

        final BonusValue valueResult
                = this.service.getBonusValueForKey(
                        stateParticle, dndCharacter, unqiueId);

        assertNull(valueResult);
    }

    @Test
    public void getBonusValueForKeyNormalStateTest() {
        final StateParticle stateParticle
                = StateRegistry.StateParticle.POWER_ATTACK_BONUS;
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("dndCharacter"));
        final StateRegistry reg = new StateRegistry();
        dndCharacter.setStateRegistry(reg);

        final UniqueId unqiueId = new UniqueId("weapon");

        reg.putState(stateParticle, unqiueId, 2L);

        final BonusValue valueResult
                = this.service.getBonusValueForKey(
                        stateParticle, dndCharacter, unqiueId);

        assertEquals(2L, valueResult.getValue());
    }

}
