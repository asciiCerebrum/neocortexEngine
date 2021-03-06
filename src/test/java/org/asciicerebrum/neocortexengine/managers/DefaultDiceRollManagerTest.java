package org.asciicerebrum.neocortexengine.managers;

import org.asciicerebrum.neocortexengine.mechanics.managers.DefaultDiceRollManager;
import java.security.SecureRandom;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceNumber;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceRoll;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceSides;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Dice;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class DefaultDiceRollManagerTest {

    private DefaultDiceRollManager drManager;

    private SecureRandom rand;

    private DiceAction diceAction;

    public DefaultDiceRollManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.drManager = new DefaultDiceRollManager();
        this.rand = mock(SecureRandom.class);
        this.diceAction = new DiceAction();
        this.drManager.setRandom(this.rand);

        Dice diceType = new Dice();
        diceType.setSides(new DiceSides(20L));
        this.diceAction.setDiceNumber(new DiceNumber(2));
        this.diceAction.setDiceType(diceType);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of rollDice method, of class DiceRollManager.
     */
    @Test
    public void testRollDice() {
        when(this.rand.nextDouble()).thenReturn(0.5d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(22l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMinimum() {
        when(this.rand.nextDouble()).thenReturn(0.0d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(2l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMaximum() {
        when(this.rand.nextDouble()).thenReturn(1.0d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        // 42 is ok, because 1.0 is never generated by secureRandom. It is
        // exclusive!
        assertEquals(42l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMinimum010() {
        when(this.rand.nextDouble()).thenReturn(0.1d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(6l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMinimum020() {
        when(this.rand.nextDouble()).thenReturn(0.2d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(10l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMinimum005() {
        when(this.rand.nextDouble()).thenReturn(0.05d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(4l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMinimum015() {
        when(this.rand.nextDouble()).thenReturn(0.15d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(8l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMinimum012() {
        when(this.rand.nextDouble()).thenReturn(0.12d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(6l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMinimum002() {
        when(this.rand.nextDouble()).thenReturn(0.02d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(2l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMaximum09499() {
        when(this.rand.nextDouble()).thenReturn(0.9499d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(38l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMaximum095() {
        when(this.rand.nextDouble()).thenReturn(0.95d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(40l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMaximum09501() {
        when(this.rand.nextDouble()).thenReturn(0.9501d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(40l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMaximum098() {
        when(this.rand.nextDouble()).thenReturn(0.98d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(40l, rollResult.getValue());
    }

    @Test
    public void testRollDiceMaximum0995() {
        when(this.rand.nextDouble()).thenReturn(0.995d);

        DiceRoll rollResult = this.drManager.rollDice(this.diceAction);

        assertEquals(40l, rollResult.getValue());
    }

}
