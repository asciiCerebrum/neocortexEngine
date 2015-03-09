package org.asciicerebrum.mydndgame.domain.factories;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.StateRegistrySetup;
import org.asciicerebrum.mydndgame.domain.setup.StateRegistrySetup.StateRegistryEntrySetup;
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

    private Campaign campaign;

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
        this.campaign = new Campaign();
        this.applicationContext = mock(ApplicationContext.class,
                withSettings()
                .extraInterfaces(ConfigurableApplicationContext.class));

        this.factory.setCampaign(this.campaign);
        this.factory.setContext(this.applicationContext);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newEntitySimpleCompleteTest() {
        final StateRegistrySetup setup = new StateRegistrySetup();
        final Reassignments reassignments = new Reassignments();

        final StateRegistry result
                = this.factory.newEntity(setup, reassignments);

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
        final Reassignments reassignments = new Reassignments();

        this.makePrepare(setup);

        this.factory.newEntity(setup, reassignments);
    }

    @Test
    public void newEntityCompleteTest() {
        final StateRegistrySetup setup = new StateRegistrySetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);

        final StateRegistry reg = this.factory.newEntity(setup, reassignments);

        final String stateVal = reg.getState(
                StateRegistry.StateParticle.WEAPON_DAMAGE_TYPE, null);
        assertEquals("A", stateVal);
    }

    @Test
    public void newEntityWithObjectTest() {
        final StateRegistrySetup setup = new StateRegistrySetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("objectId"));
        this.campaign.registerUniqueEntity(weapon);

        final StateRegistry reg = this.factory.newEntity(setup, reassignments);

        final String stateVal = reg.getState(
                StateRegistry.StateParticle.WEAPON_ATTACK_MODE, weapon);
        assertEquals("B", stateVal);
    }

    @Test
    public void newEntityWithoutObjectTest() {
        final StateRegistrySetup setup = new StateRegistrySetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);

        final StateRegistry reg = this.factory.newEntity(setup, reassignments);

        assertEquals(1L, Iterators.size(reassignments.getIterator()));
    }

}
