package org.asciicerebrum.mydndgame;

import java.util.Set;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
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
        assertEquals("025012", this.combatRound.getCurrentPosition());
    }

    @Test
    public void testMoveToNextPositionFromEnd() {
        this.combatRound.setCurrentPosition("018002");
        this.combatRound.moveToNextPosition();
        assertEquals("025012", this.combatRound.getCurrentPosition());
    }

    @Test
    public void testMoveToNextPositionFromMiddle() {
        this.combatRound.setCurrentPosition("023008");
        this.combatRound.moveToNextPosition();
        assertEquals("018002", this.combatRound.getCurrentPosition());
    }

    @Test
    public void testGetCurrentPositionFromBlank() {
        String curPos = this.combatRound.getCurrentPosition();
        assertEquals("025012", curPos);
    }

    @Test
    public void testGetCurrentPositionNormal() {
        this.combatRound.setCurrentPosition("023008");
        String curPos = this.combatRound.getCurrentPosition();
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

        this.combatRound.setCurrentPosition("curPos");
        this.combatRound.getCurrentParticipant();
    }

}
