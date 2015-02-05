package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class AttackWorkflowTest {

    public AttackWorkflowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of runWorkflow method, of class AttackWorkflow. This is a hit with
     * attack 12 (roll 10 + bonus 2) vs ac 12.
     */
    @Test
    public void testRunWorkflow() {
        fail();
    }

    @Test
    public void testRunWorkflowDamage() {
        fail();
    }

    /**
     * This is a miss with ac 13.
     */
    @Test
    public void testRunWorkflowMiss() {
        fail();
    }

    /**
     * This is a critical hit.
     */
    @Test
    public void testRunWorkflowCriticalHit() {
        fail();
    }

    /**
     * First attack roll is critical, but second is not.
     */
    @Test
    public void testRunWorkflowCriticalMiss() {
        fail();
    }

    /**
     * First attack roll is critical, but second is not.
     */
    @Test
    public void testRunWorkflowCriticalMissDamage() {
        fail();
    }

    @Test
    public void testRunWorkflowAutoFailure() {
        fail();
    }

    /**
     * Attack roll below AC but is an automatic success though!
     */
    @Test
    public void testRunWorkflowAutoSuccess() {
        fail();
    }

    @Test
    public void testRunWorkflowWithAttackCriticalKey() {
        fail();
    }

    @Test
    public void testRunWorkflowWithoutDamageWorkflow() {
        fail();
    }

    @Test
    public void testDetermineCritical() {
        fail();
    }

    @Test
    public void testDetermineCriticalAutoSuccess() {
        fail();
    }

    @Test
    public void testDetermineCriticalNoSuccess() {
        fail();
    }

    @Test
    public void testDetermineCriticalNoSuccessButAutoSuccess() {
        fail();
    }

    //TODO Important!!! Test for secondCritical - it is enough to succeed AC!
    // The second critical has nothing to do with the weapons critical minimum!
    //TODO Important!!! Test all invocations of damageWorkflow, if the correct
    // parameters were transmitted (critical, etc.)
}
