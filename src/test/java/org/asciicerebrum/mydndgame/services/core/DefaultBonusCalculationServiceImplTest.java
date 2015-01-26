package org.asciicerebrum.mydndgame.services.core;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.asciicerebrum.mydndgame.logappender.RecordingAppender;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author species8472
 */
public class DefaultBonusCalculationServiceImplTest {

    public DefaultBonusCalculationServiceImplTest() {
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

    private void configureLog() {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.removeAllAppenders();
        rootLogger.setLevel(Level.ALL);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout(
                "%d [%t] %-5p %c{1} - %m%n")));
        rootLogger.addAppender(RecordingAppender.appender(new PatternLayout(
                "%-5p - %m%n")));
    }

    /**
     * Test of filterBonusListByTarget method, of class BonusCalculationService.
     * Test of the correct result list size.
     */
    @Test
    public void testFilterBonusListByTarget() {
        fail();
    }

    /**
     * Test of filterBonusListByTarget method, of class BonusCalculationService.
     * Test of the correct filter result.
     */
    @Test
    public void testFilterBonusListByTargetCorrectFilterResult() {
        fail();
    }

    /**
     * Traverse a list-based bonus granter object. Check size of result list.
     */
    @Test
    public void testTraverseBoniByTargetList() {
        fail();
    }

    /**
     * Traverse a map-based bonus granter object. Check size of result list.
     */
    @Test
    public void testTraverseBoniByTargetMap() {
        fail();
    }

    /**
     * Traverse a single object-based bonus granter object. Check size of result
     * list.
     */
    @Test
    public void testTraverseBoniByTargetObject() {
        fail();
    }

    /**
     * Traverse a single object-based bonus granter object. Check content of
     * result list for correct target.
     */
    @Test
    public void testTraverseBoniByTargetObjectContent() {
        fail();
    }

    private boolean logContains(String expected) {
        String actual[] = RecordingAppender.messages();
        for (String log : actual) {
            if (log.contains(expected)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Traverse a single object-based bonus granter object. Bonus granter object
     * has no getter for the annotated field. So an exception should be thrown.
     */
    @Test
    public void testTraverseBoniByTargetNoSuchMethodException() {
        fail();
    }

    /**
     * Granted boni are static and not dynamic.
     */
    @Test
    public void testRetrieveEffectiveBonusValueByTarget() {
        fail();
    }

    /**
     * Granted boni are dynamic.
     */
    @Test
    public void testRetrieveEffectiveBonusValueByTargetDynamic() {
        fail();
    }

    @Test
    public void testAccumulateBonusValue() {
        fail();
    }

    @Test
    public void testAccumulateBonusValueWithNullValue() {
        fail();
    }
}
