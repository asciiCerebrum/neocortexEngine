package org.asciicerebrum.mydndgame.domain.factories;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.ruleentities.ConditionType;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Condition;
import org.asciicerebrum.mydndgame.domain.setup.ConditionSetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.WorldDateSetup;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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
public class ConditionFactoryTest {

    private ConditionFactory factory;

    private Campaign campaign;

    private ApplicationContext applicationContext;

    private EntityFactory<WorldDate> worldDateFactory;

    public ConditionFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new ConditionFactory();
        this.campaign = new Campaign();
        this.applicationContext = mock(ApplicationContext.class,
                withSettings()
                .extraInterfaces(ConfigurableApplicationContext.class));
        this.worldDateFactory = mock(EntityFactory.class);

        ApplicationContextProvider ctxProvider
                = new ApplicationContextProvider();
        ctxProvider.setApplicationContext(this.applicationContext);
        this.factory.setWorldDateFactory(this.worldDateFactory);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final ConditionSetup setup = new ConditionSetup();

        this.factory.newEntity(setup, this.campaign);
    }

    private void makeComplete(ConditionSetup setup) {
        final WorldDateSetup worldDateSetup = new WorldDateSetup();
        worldDateSetup.setCombatRoundNumber("1");
        worldDateSetup.setCombatRoundPosition("1");

        setup.setConditionType("conditionTypeId");
        setup.setStartingDate(worldDateSetup);
        setup.setExpiryDate(worldDateSetup);
    }

    @Test
    public void newEntityCompleteTest() {
        final ConditionSetup setup = new ConditionSetup();

        this.makeComplete(setup);

        this.factory.newEntity(setup, this.campaign);

        verify(this.applicationContext, times(1))
                .getBean("conditionTypeId", ConditionType.class);
    }

    @Test
    public void newEntityWithCauseEntityFoundTest() {
        final ConditionSetup setup = new ConditionSetup();

        this.makeComplete(setup);
        setup.setCauseEntity("causeEntity");

        final UniqueEntity entity = new Weapon();
        entity.setUniqueId(new UniqueId("causeEntity"));
        this.campaign.registerUniqueEntity(entity);

        final Condition result = this.factory.newEntity(setup, this.campaign);

        assertEquals(entity, result.getCauseEntity());
    }

    @Test
    public void newEntityWithCauseEntityNotFoundTest() {
        final ConditionSetup setup = new ConditionSetup();

        this.makeComplete(setup);
        setup.setCauseEntity("causeEntity");

        this.factory.newEntity(setup, this.campaign);

        assertEquals(1L, Iterators.size(this.campaign.reassignmentIterator()));
    }

}
