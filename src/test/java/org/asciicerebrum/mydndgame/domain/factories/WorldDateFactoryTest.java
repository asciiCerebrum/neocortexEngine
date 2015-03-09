package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.WorldDateSetup;
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
        final Reassignments reassignments = new Reassignments();

        this.factory.newEntity(setup, reassignments);
    }

    private void makeComplete(WorldDateSetup setup) {
        setup.setCombatRoundNumber("1");
        setup.setCombatRoundPosition("1");
    }

    @Test
    public void newEntityCompleteTest() {
        final WorldDateSetup setup = new WorldDateSetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);

        final WorldDate itemResult
                = this.factory.newEntity(setup, reassignments);

        assertEquals("1", itemResult.getCombatRoundPosition().getValue());
    }

}
