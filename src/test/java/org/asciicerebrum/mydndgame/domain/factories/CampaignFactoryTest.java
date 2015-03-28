package org.asciicerebrum.mydndgame.domain.factories;

import java.util.HashMap;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.setup.ArmorSetup;
import org.asciicerebrum.mydndgame.domain.setup.CampaignSetup;
import org.asciicerebrum.mydndgame.domain.setup.CharacterSetup;
import org.asciicerebrum.mydndgame.domain.setup.CombatRoundSetup;
import org.asciicerebrum.mydndgame.domain.setup.InventoryItemSetup;
import org.asciicerebrum.mydndgame.domain.setup.WeaponSetup;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    private EntityFactory<DndCharacter> characterFactory;

    private EntityFactory<InventoryItem> inventoryItemFactory;

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
        this.characterFactory = mock(EntityFactory.class);
        this.inventoryItemFactory = mock(EntityFactory.class);
        this.combatRoundFactory = mock(EntityFactory.class);
        this.applicationContext = mock(ApplicationContext.class,
                withSettings()
                .extraInterfaces(ConfigurableApplicationContext.class));

        this.factory.setCharacterFactory(this.characterFactory);
        this.factory.setCombatRoundFactory(this.combatRoundFactory);
        this.factory.setInventoryItemFactories(
                new HashMap<Class, EntityFactory<InventoryItem>>() {
                    {
                        this.put(WeaponSetup.class, inventoryItemFactory);
                        this.put(ArmorSetup.class, inventoryItemFactory);
                        this.put(InventoryItemSetup.class,
                                inventoryItemFactory);
                    }
                ;
        }
        );
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

        final Campaign campaign = this.factory.newEntity(setup, null);

        assertNotNull(campaign);
    }

    @Test
    public void newEntityWithInventoryItemsTest() {
        final CampaignSetup setup = new CampaignSetup();

        final InventoryItemSetup itemSetup = new WeaponSetup();
        setup.addInventoryItem(itemSetup);
        setup.addInventoryItem(itemSetup);
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weaponId"));

        when(this.inventoryItemFactory.newEntity(eq(itemSetup),
                (Campaign) anyObject())).thenReturn(weapon);

        this.factory.newEntity(setup, null);

        verify(this.inventoryItemFactory, times(2))
                .newEntity(eq(itemSetup), (Campaign) anyObject());
    }

    @Test
    public void newEntityWithParticipantsTest() {
        final CampaignSetup setup = new CampaignSetup();

        final CharacterSetup characterSetup = new CharacterSetup();
        setup.addDndCharacter(characterSetup);
        setup.addDndCharacter(characterSetup);
        setup.addDndCharacter(characterSetup);
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("characterId"));

        when(this.characterFactory.newEntity(eq(characterSetup),
                (Campaign) anyObject())).thenReturn(dndCharacter);

        this.factory.newEntity(setup, null);

        verify(this.characterFactory, times(3))
                .newEntity(eq(characterSetup), (Campaign) anyObject());
    }

    @Test
    public void newEntityWithCombatRoundTest() {
        final CampaignSetup setup = new CampaignSetup();

        final CombatRoundSetup combatRoundSetup = new CombatRoundSetup();
        setup.setCombatRound(combatRoundSetup);
        final CombatRound combatRound = new CombatRound();

        when(this.combatRoundFactory.newEntity(eq(combatRoundSetup),
                (Campaign) anyObject())).thenReturn(combatRound);

        final Campaign result = this.factory.newEntity(setup, null);

        assertEquals(combatRound, result.getCombatRound());
    }

}
