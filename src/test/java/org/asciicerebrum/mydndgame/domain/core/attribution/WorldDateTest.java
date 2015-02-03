package org.asciicerebrum.mydndgame.domain.core.attribution;

import org.asciicerebrum.mydndgame.domain.mechanics.entities.WorldDate;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class WorldDateTest {

    private WorldDate wd1;

    private WorldDate wd2;

    private WorldDate wd3;

    private WorldDate wd4;

    public WorldDateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.wd1 = new WorldDate();
        wd1.setCombatRoundNumber(new CombatRoundNumber(1l));
        wd1.setCombatRoundPosition(new CombatRoundPosition("1"));
        this.wd2 = new WorldDate();
        wd2.setCombatRoundNumber(new CombatRoundNumber(1l));
        wd2.setCombatRoundPosition(new CombatRoundPosition("12"));
        this.wd3 = new WorldDate();
        wd3.setCombatRoundNumber(new CombatRoundNumber(2l));
        wd3.setCombatRoundPosition(new CombatRoundPosition("2"));
        this.wd4 = new WorldDate();
        wd4.setCombatRoundNumber(new CombatRoundNumber(1l));
        wd4.setCombatRoundPosition(new CombatRoundPosition("1"));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of compareTo method, of class WorldDate.
     */
    @Test
    public void testCompareToLarger() {
        int compare12 = this.wd1.compareTo(this.wd2);
        assertEquals(1, compare12);
    }

    @Test
    public void testCompareToSmaller() {
        int compare23 = this.wd2.compareTo(this.wd3);
        assertEquals(-1, compare23);
    }

    /**
     * Test of equals method, of class WorldDate.
     */
    @Test
    public void testEqualsFalse() {
        boolean eq12 = this.wd1.equals(this.wd2);
        assertFalse(eq12);
    }

    @Test
    public void testEqualsFalseRoundNumber() {
        boolean eq13 = this.wd1.equals(this.wd3);
        assertFalse(eq13);
    }

    @Test
    public void testEqualsTrue() {
        boolean eq14 = this.wd1.equals(this.wd4);
        assertTrue(eq14);
    }

    @Test
    public void testEqualsFalseWrongType() {
        boolean eqString = this.wd1.equals("abc");
        assertFalse(eqString);
    }

    /**
     * Test of hashCode method, of class WorldDate.
     */
    @Test
    public void testHashCodeEq() {
        int hash1 = this.wd1.hashCode();
        int hash4 = this.wd4.hashCode();

        assertEquals(hash1, hash4);
    }

    @Test
    public void testHashCodeNotEq() {
        int hash1 = this.wd1.hashCode();
        int hash2 = this.wd2.hashCode();

        assertNotEquals(hash1, hash2);
    }

    @Test
    public void testHashCodeNotEqNullRoundNumber() {
        this.wd1.setCombatRoundNumber(null);
        int hash1 = this.wd1.hashCode();
        int hash2 = this.wd2.hashCode();

        assertNotEquals(hash1, hash2);
    }

    @Test
    public void testHashCodeNotEqNullRoundPosition() {
        this.wd1.setCombatRoundPosition(null);
        int hash1 = this.wd1.hashCode();
        int hash2 = this.wd2.hashCode();

        assertNotEquals(hash1, hash2);
    }

    @Test
    public void testHashCodeEqNullRoundNumber() {
        this.wd1.setCombatRoundNumber(null);
        this.wd4.setCombatRoundNumber(null);
        int hash1 = this.wd1.hashCode();
        int hash4 = this.wd4.hashCode();

        assertEquals(hash1, hash4);
    }

    @Test
    public void testHashCodeEqNullRoundPosition() {
        this.wd1.setCombatRoundPosition(null);
        this.wd4.setCombatRoundPosition(null);
        int hash1 = this.wd1.hashCode();
        int hash4 = this.wd4.hashCode();

        assertEquals(hash1, hash4);
    }

}
