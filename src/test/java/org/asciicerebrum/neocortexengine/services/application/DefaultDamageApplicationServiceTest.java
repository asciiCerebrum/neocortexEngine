package org.asciicerebrum.neocortexengine.services.application;

import org.asciicerebrum.neocortexengine.domain.core.particles.DiceRoll;
import org.asciicerebrum.neocortexengine.domain.core.particles.HitPoints;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHooks;
import org.asciicerebrum.neocortexengine.domain.mechanics.damage.Damage;
import org.asciicerebrum.neocortexengine.domain.mechanics.damage.Damages;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSources;
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
public class DefaultDamageApplicationServiceTest {

    private DefaultDamageApplicationService service;

    private ObservableService observableService;

    public DefaultDamageApplicationServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.service = new DefaultDamageApplicationService();
        this.observableService = mock(ObservableService.class);

        this.service.setObservableService(this.observableService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void applyDamageNoApplicationTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setCurrentStaticHp(new HitPoints(100L));
        final Damages damages = new Damages();
        final Damage damageA = new Damage();
        final Damage damageB = new Damage();
        damageA.setDamageValue(new DiceRoll(7L));
        damageB.setDamageValue(new DiceRoll(13L));
        damages.addDamage(damageA);
        damages.addDamage(damageB);

        this.service.applyDamage(dndCharacter, damages);
        assertEquals(100L, dndCharacter.getCurrentStaticHp().getValue());
    }

    @Test
    public void applyDamageNormalApplicationTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setCurrentStaticHp(new HitPoints(100L));
        final Damages damages = new Damages();
        final Damage damageA = new Damage();
        final Damage damageB = new Damage();
        damageA.setDamageValue(new DiceRoll(7L));
        damageB.setDamageValue(new DiceRoll(13L));
        damages.addDamage(damageA);
        damages.addDamage(damageB);

        when(this.observableService.triggerObservers(eq(damageA),
                eq(dndCharacter), (ObserverSources) anyObject(),
                (ObserverHooks) anyObject(), eq(dndCharacter)))
                .thenReturn(damageA);
        when(this.observableService.triggerObservers(eq(damageB),
                eq(dndCharacter), (ObserverSources) anyObject(),
                (ObserverHooks) anyObject(), eq(dndCharacter)))
                .thenReturn(damageB);

        this.service.applyDamage(dndCharacter, damages);
        assertEquals(80L, dndCharacter.getCurrentStaticHp().getValue());
    }

}
