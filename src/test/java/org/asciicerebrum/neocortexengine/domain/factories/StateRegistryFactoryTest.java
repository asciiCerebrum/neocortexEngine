package org.asciicerebrum.neocortexengine.domain.factories;

import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.StateRegistry;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.domain.setup.StateRegistrySetup;
import org.asciicerebrum.neocortexengine.domain.setup.StateRegistrySetup.StateRegistryEntrySetup;
import org.asciicerebrum.neocortexengine.infrastructure.ApplicationContextProvider;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author species8472
 */
public class StateRegistryFactoryTest {

    private StateRegistryFactory factory;

    private ApplicationContext applicationContext;

    public StateRegistryFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new StateRegistryFactory();
        this.applicationContext = mock(ApplicationContext.class,
                withSettings()
                .extraInterfaces(ConfigurableApplicationContext.class));

        ApplicationContextProvider ctxProvider
                = new ApplicationContextProvider();
        ctxProvider.setApplicationContext(this.applicationContext);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newEntitySimpleCompleteTest() {
        final StateRegistrySetup setup = new StateRegistrySetup();

        final StateRegistry result
                = this.factory.newEntity(setup);

        assertNotNull(result);
    }

    private void makePrepare(StateRegistrySetup setup) {
        final StateRegistryEntrySetup entrySetup
                = new StateRegistryEntrySetup();

        setup.addStateRegistryEntry(entrySetup);
    }

    private void makeComplete(StateRegistrySetup setup) {
        final StateRegistryEntrySetup entrySetupA
                = new StateRegistryEntrySetup();
        final StateRegistryEntrySetup entrySetupB
                = new StateRegistryEntrySetup();

        entrySetupA.setRegistryParticle("WEAPON_DAMAGE_TYPE");
        entrySetupA.setRegistryValue("A");
        entrySetupA.setRegistryValueType("STRING");

        entrySetupB.setRegistryParticle("WEAPON_ATTACK_MODE");
        entrySetupB.setRegistryValue("B");
        entrySetupB.setRegistryValueType("STRING");
        entrySetupB.setContextObjectId("objectId");

        setup.addStateRegistryEntry(entrySetupA);
        setup.addStateRegistryEntry(entrySetupB);
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final StateRegistrySetup setup = new StateRegistrySetup();

        this.makePrepare(setup);

        this.factory.newEntity(setup);
    }

    @Test
    public void newEntityCompleteTest() {
        final StateRegistrySetup setup = new StateRegistrySetup();

        this.makeComplete(setup);

        final StateRegistry reg = this.factory.newEntity(setup);

        final String stateVal = reg.getState(
                StateRegistry.StateParticle.WEAPON_DAMAGE_TYPE, null);
        assertEquals("A", stateVal);
    }

    @Test
    public void newEntityWithObjectTest() {
        final StateRegistrySetup setup = new StateRegistrySetup();

        this.makeComplete(setup);

        final StateRegistry reg = this.factory.newEntity(setup);

        final String stateVal = reg.getState(
                StateRegistry.StateParticle.WEAPON_ATTACK_MODE,
                new UniqueId("objectId"));
        assertEquals("B", stateVal);
    }

}
