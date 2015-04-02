package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.setup.CampaignSetup;
import org.asciicerebrum.mydndgame.domain.setup.CombatRoundSetup;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author species8472
 */
public class CampaignFactoryTest {

    private CampaignFactory factory;

    private ApplicationContext applicationContext;

    private EntityFactory<CombatRound> combatRoundFactory;

    public CampaignFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new CampaignFactory();
        this.combatRoundFactory = mock(EntityFactory.class);
        this.applicationContext = mock(ApplicationContext.class,
                withSettings()
                .extraInterfaces(ConfigurableApplicationContext.class));

        this.factory.setCombatRoundFactory(this.combatRoundFactory);

        ApplicationContextProvider ctxProvider
                = new ApplicationContextProvider();
        ctxProvider.setApplicationContext(this.applicationContext);

        when(this.applicationContext.getBean(Campaign.class))
                .thenReturn(new Campaign());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newEntitySimpleCompleteTest() {
        final CampaignSetup setup = new CampaignSetup();

        final Campaign campaign = this.factory.newEntity(setup);

        assertNotNull(campaign);
    }

    @Test
    public void newEntityWithCombatRoundTest() {
        final CampaignSetup setup = new CampaignSetup();

        final CombatRoundSetup combatRoundSetup = new CombatRoundSetup();
        setup.setCombatRound(combatRoundSetup);
        final CombatRound combatRound = new CombatRound();

        when(this.combatRoundFactory.newEntity(eq(combatRoundSetup)))
                .thenReturn(combatRound);

        final Campaign result = this.factory.newEntity(setup);

        assertEquals(combatRound, result.getCombatRound());
    }

}
