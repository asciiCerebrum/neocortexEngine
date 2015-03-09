package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.ruleentities.composition.BaseAbilityEntry;
import org.asciicerebrum.mydndgame.domain.setup.BaseAbilityEntrySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author species8472
 */
public class BaseAbilityEntryFactoryTest {

    private BaseAbilityEntryFactory factory;

    private ApplicationContext applicationContext;

    public BaseAbilityEntryFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new BaseAbilityEntryFactory();
        this.applicationContext = mock(ApplicationContext.class,
                withSettings()
                .extraInterfaces(ConfigurableApplicationContext.class));

        this.factory.setContext(this.applicationContext);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final BaseAbilityEntrySetup setup = new BaseAbilityEntrySetup();
        final Reassignments reassignments = new Reassignments();

        this.factory.newEntity(setup, reassignments);
    }

    private void makeComplete(BaseAbilityEntrySetup setup) {
        setup.setAbility("abilityId");
        setup.setAbilityValue("15");
    }

    @Test
    public void newEntityCompleteTest() {
        final BaseAbilityEntrySetup setup = new BaseAbilityEntrySetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);

        final BaseAbilityEntry itemResult
                = this.factory.newEntity(setup, reassignments);

        assertEquals(15L, itemResult.getAbilityValue().getValue());
    }

}
