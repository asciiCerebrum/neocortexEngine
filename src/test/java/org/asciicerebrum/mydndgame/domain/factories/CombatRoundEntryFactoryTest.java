package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.CombatRoundEntry;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.setup.CombatRoundEntrySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
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

    private Campaign campaign;

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
        this.campaign = new Campaign();

        this.factory.setCampaign(this.campaign);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final CombatRoundEntrySetup setup = new CombatRoundEntrySetup();
        final Reassignments reassignments = new Reassignments();

        this.factory.newEntity(setup, reassignments);
    }

    private void makeComplete(CombatRoundEntrySetup setup) {
        setup.setParticipantId("participantId");
        setup.setRoundPosition("posA");
    }

    @Test
    public void newEntityCompleteTest() {
        final CombatRoundEntrySetup setup = new CombatRoundEntrySetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);

        final CombatRoundEntry result
                = this.factory.newEntity(setup, reassignments);

        assertEquals("posA", result.getCombatRoundPosition().getValue());
    }

    @Test
    public void newEntityWithParticipantTest() {
        final CombatRoundEntrySetup setup = new CombatRoundEntrySetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);
        final DndCharacter participant = new DndCharacter();
        participant.setUniqueId(new UniqueId("participantId"));
        this.campaign.registerUniqueEntity(participant);

        final CombatRoundEntry result
                = this.factory.newEntity(setup, reassignments);

        assertEquals(participant, result.getParticipant());
    }

}
