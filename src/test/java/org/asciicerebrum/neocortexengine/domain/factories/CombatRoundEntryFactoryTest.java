package org.asciicerebrum.neocortexengine.domain.factories;

import org.asciicerebrum.neocortexengine.domain.game.CombatRoundEntry;
import org.asciicerebrum.neocortexengine.domain.setup.CombatRoundEntrySetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
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
public class CombatRoundEntryFactoryTest {

    private CombatRoundEntryFactory factory;

    public CombatRoundEntryFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new CombatRoundEntryFactory();
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final CombatRoundEntrySetup setup = new CombatRoundEntrySetup();

        this.factory.newEntity(setup);
    }

    private void makeComplete(CombatRoundEntrySetup setup) {
        setup.setParticipantId("participantId");
        setup.setRoundPosition("posA");
    }

    @Test
    public void newEntityCompleteTest() {
        final CombatRoundEntrySetup setup = new CombatRoundEntrySetup();

        this.makeComplete(setup);

        final CombatRoundEntry result
                = this.factory.newEntity(setup);

        assertEquals("posA", result.getCombatRoundPosition().getValue());
    }

    @Test
    public void newEntityWithParticipantTest() {
        final CombatRoundEntrySetup setup = new CombatRoundEntrySetup();

        this.makeComplete(setup);

        final CombatRoundEntry result
                = this.factory.newEntity(setup);

        assertEquals("participantId", result.getParticipantId().getValue());
    }

}
