package org.asciicerebrum.neocortexengine.domain.factories;

import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.domain.setup.WorldDateSetup;
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
public class WorldDateFactoryTest {

    private WorldDateFactory factory;

    public WorldDateFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new WorldDateFactory();
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final WorldDateSetup setup = new WorldDateSetup();

        this.factory.newEntity(setup);
    }

    private void makeComplete(WorldDateSetup setup) {
        setup.setCombatRoundNumber("1");
        setup.setCombatRoundPosition("1");
    }

    @Test
    public void newEntityCompleteTest() {
        final WorldDateSetup setup = new WorldDateSetup();

        this.makeComplete(setup);

        final WorldDate itemResult = this.factory.newEntity(setup);

        assertEquals("1", itemResult.getCombatRoundPosition().getValue());
    }

}
