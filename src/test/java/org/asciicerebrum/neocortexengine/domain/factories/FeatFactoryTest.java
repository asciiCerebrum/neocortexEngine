package org.asciicerebrum.neocortexengine.domain.factories;

import org.asciicerebrum.neocortexengine.domain.ruleentities.Feat;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatBinding;
import org.asciicerebrum.neocortexengine.domain.setup.FeatSetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.infrastructure.ApplicationContextProvider;
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

        ApplicationContextProvider ctxProvider
                = new ApplicationContextProvider();
        ctxProvider.setApplicationContext(this.applicationContext);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final FeatSetup setup = new FeatSetup();

        this.factory.newEntity(setup);
    }

    private void makeComplete(FeatSetup setup) {
        setup.setFeatType("featTypeId");
    }

    @Test
    public void newEntityCompleteTest() {
        final FeatSetup setup = new FeatSetup();
        this.makeComplete(setup);

        this.factory.newEntity(setup);

        verify(this.applicationContext, times(1))
                .getBean("featTypeId", Feat.class);
    }

    @Test
    public void newEntityWithFeatBindingTest() {
        final FeatSetup setup = new FeatSetup();

        this.makeComplete(setup);
        setup.setFeatBinding("featBindingId");

        this.factory.newEntity(setup);

        verify(this.applicationContext, times(1))
                .getBean("featBindingId", FeatBinding.class);
    }

    @Test
    public void newEntityWithoutFeatBindingTest() {
        final FeatSetup setup = new FeatSetup();

        this.makeComplete(setup);

        this.factory.newEntity(setup);

        verify(this.applicationContext, times(0))
                .getBean(anyString(), eq(FeatBinding.class));
    }

}
