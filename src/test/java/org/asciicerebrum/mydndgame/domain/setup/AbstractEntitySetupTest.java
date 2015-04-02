package org.asciicerebrum.mydndgame.domain.setup;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class AbstractEntitySetupTest {

    private AbstractEntitySetup setup;

    private AbstractEntitySetup subSetup;

    public AbstractEntitySetupTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.setup = new AbstractEntitySetup() {

            public boolean isSetupComplete() {
                return true;
            }
        };

        this.subSetup = new AbstractEntitySetup() {

            public boolean isSetupComplete() {
                return true;
            }
        };

    }

    @After
    public void tearDown() {
    }

    @Test
    public void checkRequiredSinglePropertiesTrueTest() {
        final SetupProperty[] requiredProps = {
            SetupProperty.ABILITY_ADVANCEMENT};
        this.setup.setProperty(SetupProperty.ABILITY_ADVANCEMENT, "test");
        final boolean testResult
                = this.setup.checkRequiredSingleProperties(requiredProps);
        assertTrue(testResult);
    }

    @Test
    public void checkRequiredSinglePropertiesFalseTest() {
        final SetupProperty[] requiredProps = {
            SetupProperty.ADVANCEMENT_NUMBER};
        this.setup.setProperty(SetupProperty.ABILITY_ADVANCEMENT, "test");
        final boolean testResult
                = this.setup.checkRequiredSingleProperties(requiredProps);
        assertFalse(testResult);
    }

    @Test
    public void checkRequiredListPropertiesNonEmptyTest() {
        final SetupProperty[] requiredProps = {
            SetupProperty.ADVANCEMENT_NUMBER};
        final List<String> listProperties = new ArrayList<String>();
        this.setup.getListProperties().put(SetupProperty.ADVANCEMENT_NUMBER,
                listProperties);
        listProperties.add("test");
        final boolean testResult
                = this.setup.checkRequiredListProperties(requiredProps);
        assertTrue(testResult);
    }

    @Test
    public void checkRequiredListPropertiesEmptyTest() {
        final SetupProperty[] requiredProps = {
            SetupProperty.ADVANCEMENT_NUMBER};
        final List<String> listProperties = new ArrayList<String>();
        this.setup.getListProperties().put(SetupProperty.ADVANCEMENT_NUMBER,
                listProperties);
        final boolean testResult
                = this.setup.checkRequiredListProperties(requiredProps);
        assertFalse(testResult);
    }

    @Test
    public void checkRequiredListPropertiesNullTest() {
        final SetupProperty[] requiredProps = {
            SetupProperty.ADVANCEMENT_NUMBER};
        final List<String> listProperties = null;
        this.setup.getListProperties().put(SetupProperty.ADVANCEMENT_NUMBER,
                listProperties);
        final boolean testResult
                = this.setup.checkRequiredListProperties(requiredProps);
        assertFalse(testResult);
    }

    @Test
    public void checkRequiredSingleSetupTrueTest() {
        final SetupProperty[] requiredProps = {
            SetupProperty.ADVANCEMENT_NUMBER};
        this.setup.getSingleSetup().put(SetupProperty.ADVANCEMENT_NUMBER,
                this.subSetup);
        final boolean testResult
                = this.setup.checkRequiredSingleSetup(requiredProps);
        assertTrue(testResult);
    }

    @Test
    public void checkRequiredSingleSetupNullTest() {
        final SetupProperty[] requiredProps = {
            SetupProperty.ADVANCEMENT_NUMBER};
        this.setup.getSingleSetup().put(SetupProperty.ADVANCEMENT_NUMBER,
                null);
        final boolean testResult
                = this.setup.checkRequiredSingleSetup(requiredProps);
        assertFalse(testResult);
    }

}
