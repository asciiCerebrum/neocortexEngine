package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.ruleentities.FeatBinding;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatType;
import org.asciicerebrum.mydndgame.domain.setup.FeatSetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author species8472
 */
public class FeatFactoryTest {

    private FeatFactory factory;

    private ApplicationContext applicationContext;

    public FeatFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new FeatFactory();
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
        final FeatSetup setup = new FeatSetup();
        final Reassignments reassignments = new Reassignments();

        this.factory.newEntity(setup, reassignments);
    }

    private void makeComplete(FeatSetup setup) {
        setup.setFeatType("featTypeId");
    }

    @Test
    public void newEntityCompleteTest() {
        final FeatSetup setup = new FeatSetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);

        this.factory.newEntity(setup, reassignments);

        verify(this.applicationContext, times(1))
                .getBean("featTypeId", FeatType.class);
    }

    @Test
    public void newEntityWithFeatBindingTest() {
        final FeatSetup setup = new FeatSetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);
        setup.setFeatBinding("featBindingId");

        this.factory.newEntity(setup, reassignments);

        verify(this.applicationContext, times(1))
                .getBean("featBindingId", FeatBinding.class);
    }

    @Test
    public void newEntityWithoutFeatBindingTest() {
        final FeatSetup setup = new FeatSetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);

        this.factory.newEntity(setup, reassignments);

        verify(this.applicationContext, times(0))
                .getBean(anyString(), eq(FeatBinding.class));
    }

}
