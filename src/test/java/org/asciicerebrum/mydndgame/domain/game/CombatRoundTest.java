package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
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
public class CombatRoundTest {

    private CombatRound combatRound;

    private UniqueId charA;

    private UniqueId charB;

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

        final WorldDate currentDate = new WorldDate();
        currentDate.setCombatRoundNumber(new CombatRoundNumber(10L));
        currentDate.setCombatRoundPosition(new CombatRoundPosition("1"));
        this.combatRound.setCurrentDate(currentDate);

        this.charA = new UniqueId("charA");

        this.charB = new UniqueId("charB");

        this.combatRound.addParticipant(this.charA,
                new CombatRoundPosition("1"));
        this.combatRound.addParticipant(this.charB,
                new CombatRoundPosition("2"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void moveToNextPositionTest() {
        this.combatRound.moveToNextPosition();

        assertEquals("2", this.combatRound.getCurrentDate()
                .getCombatRoundPosition().getValue());
    }

    @Test
    public void moveToNextPositionWithRoundNumberSwitchPositionTest() {
        this.combatRound.getCurrentDate().setCombatRoundPosition(
                new CombatRoundPosition("2"));
        this.combatRound.moveToNextPosition();

        assertEquals("1", this.combatRound.getCurrentDate()
                .getCombatRoundPosition().getValue());
    }

    @Test
    public void moveToNextPositionWithRoundNumberSwitchRoundNumberTest() {
        this.combatRound.getCurrentDate().setCombatRoundPosition(
                new CombatRoundPosition("2"));
        this.combatRound.moveToNextPosition();

        assertEquals(11L, this.combatRound.getCurrentDate()
                .getCombatRoundNumber().getValue());
    }

    @Test
    public void getNextParticipationDateUnknownTest() {
        assertNull(this.combatRound.getNextParticipationDate(
                new UniqueId("unknown")));
    }

    @Test
    public void getNextParticipationDateKnownTest() {
        assertEquals("2", this.combatRound.getNextParticipationDate(
                this.charB).getCombatRoundPosition().getValue());
    }

    @Test
    public void getNextParticipationDateKnownInPastTest() {
        this.combatRound.getCurrentDate().setCombatRoundPosition(
                new CombatRoundPosition("2"));
        assertEquals(11L, this.combatRound.getNextParticipationDate(
                this.charA).getCombatRoundNumber().getValue());
    }
}
