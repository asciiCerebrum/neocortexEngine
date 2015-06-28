package org.asciicerebrum.neocortexengine.domain.factories;

import org.asciicerebrum.neocortexengine.domain.game.InventoryItem;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.SpecialAbility;
import org.asciicerebrum.neocortexengine.domain.setup.InventoryItemSetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.infrastructure.ApplicationContextProvider;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author species8472
 */
public class InventoryItemFactoryTest {

    private InventoryItemFactory factory;

    private ApplicationContext applicationContext;

    public InventoryItemFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new InventoryItemFactory() {

            @Override
            protected InventoryItem getConcreteInventoryItem() {
                return new Weapon();
            }

            @Override
            protected void finalizeCreation(InventoryItem inventoryItem) {
            }
        };
        this.applicationContext = mock(ApplicationContext.class,
                withSettings()
                .extraInterfaces(ConfigurableApplicationContext.class));

        ApplicationContextProvider ctxProvider
                = new ApplicationContextProvider();
        ctxProvider.setApplicationContext(this.applicationContext);

        ConfigurableListableBeanFactory beanFactory
                = mock(ConfigurableListableBeanFactory.class);
        when(((ConfigurableApplicationContext) this.applicationContext)
                .getBeanFactory()).thenReturn(beanFactory);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final InventoryItemSetup setup = new InventoryItemSetup() {
        };

        this.factory.newEntity(setup);
    }

    private void makeComplete(InventoryItemSetup setup) {
        setup.setName("item");
        setup.setSizeCategory("medium");
        setup.setId("id");
    }

    @Test
    public void newEntityCompleteTest() {
        final InventoryItemSetup setup = new InventoryItemSetup() {
        };

        this.makeComplete(setup);

        final InventoryItem itemResult
                = this.factory.newEntity(setup);

        assertEquals("id", itemResult.getUniqueId().getValue());
    }

    @Test
    public void newEntityWithAbilitiesTest() {
        final InventoryItemSetup setup = new InventoryItemSetup() {
        };

        this.makeComplete(setup);
        setup.addSpecialAbility("ability");
        setup.addSpecialAbility("ability");
        setup.addSpecialAbility("ability");

        this.factory.newEntity(setup);

        verify(this.applicationContext, times(3))
                .getBean("ability", SpecialAbility.class);
    }

}
