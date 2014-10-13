package org.asciicerebrum.mydndgame;

import java.util.Set;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorldDate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author species8472
 */
public class CombatRoundTest {

    private CombatRound combatRound;

    private ICharacter char2;

    public CombatRoundTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.combatRound = new CombatRound();

        ICharacter char1 = mock(ICharacter.class);
        this.char2 = mock(ICharacter.class);
        ICharacter char3 = mock(ICharacter.class);

        this.combatRound.addParticipant(char1, "023008");
        this.combatRound.addParticipant(this.char2, "025012");
        this.combatRound.addParticipant(char3, "018002");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMoveToNextPositionFromStart() {
        this.combatRound.moveToNextPosition();
        assertEquals("025012", this.combatRound.getCurrentDate()
                .getCombatRoundPosition());
    }

    @Test
    public void testMoveToNextPositionFromEnd() {
        IWorldDate worldDate = new WorldDate();
        worldDate.setCombatRoundPosition("018002");
        worldDate.setCombatRoundNumber(0L);
        this.combatRound.setCurrentDate(worldDate);
        this.combatRound.moveToNextPosition();
        assertEquals("025012", this.combatRound.getCurrentDate()
                .getCombatRoundPosition());
    }

    @Test
    public void testMoveToNextPositionFromMiddle() {
        IWorldDate worldDate = new WorldDate();
        worldDate.setCombatRoundPosition("023008");
        worldDate.setCombatRoundNumber(0L);
        this.combatRound.setCurrentDate(worldDate);

        this.combatRound.moveToNextPosition();
        assertEquals("018002", this.combatRound.getCurrentDate()
                .getCombatRoundPosition());
    }

    @Test
    public void testGetCurrentPositionFromBlank() {
        String curPos = this.combatRound.getCurrentDate()
                .getCombatRoundPosition();
        assertEquals("025012", curPos);
    }

    @Test
    public void testGetCurrentPositionNormal() {
        IWorldDate worldDate = new WorldDate();
        worldDate.setCombatRoundPosition("023008");
        worldDate.setCombatRoundNumber(0L);
        this.combatRound.setCurrentDate(worldDate);
        String curPos = this.combatRound.getCurrentDate()
                .getCombatRoundPosition();
        assertEquals("023008", curPos);
    }

    @Test
    public void testGetParticipantForPosition() {
        Set<ICharacter> participants
                = this.combatRound.getParticipantsForPosition("025012");
        assertEquals(this.char2, participants.iterator().next());
    }

    @Test
    public void testGetParticipantForPositionSize() {
        Set<ICharacter> participants
                = this.combatRound.getParticipantsForPosition("025012");
        assertEquals(1, participants.size());
    }

    @Test
    public void testGetParticipantForPositionBlankInputSize() {
        Set<ICharacter> participants
                = this.combatRound.getParticipantsForPosition(null);
        assertEquals(0, participants.size());
    }

    @Test
    public void testGetParticipantForPositionUnknownInputSize() {
        Set<ICharacter> participants
                = this.combatRound.getParticipantsForPosition("99999");
        assertEquals(0, participants.size());
    }

    @Test
    public void testGetParticipantForPositionInvalidParticipantSize() {
        ICharacter charInvalid = mock(ICharacter.class);
        this.combatRound.addParticipant(charInvalid, null);

        Set<ICharacter> participants
                = this.combatRound.getParticipantsForPosition("025012");
        assertEquals(1, participants.size());
    }

    @Test
    public void testGetPositionForParticipant() {
        String posParticipant
                = this.combatRound.getPositionForParticipant(this.char2);

        assertEquals("025012", posParticipant);
    }

    @Test
    public void testGetParticipantsSize() {
        Set<ICharacter> participants = this.combatRound.getParticipants();

        assertEquals(3, participants.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testGetCurrentParticipant() {
        this.combatRound.addParticipant(mock(ICharacter.class), "curPos");
        this.combatRound.addParticipant(mock(ICharacter.class), "curPos");

        this.combatRound.getCurrentDate().setCombatRoundPosition("curPos");
        this.combatRound.getCurrentParticipant();
    }

}
