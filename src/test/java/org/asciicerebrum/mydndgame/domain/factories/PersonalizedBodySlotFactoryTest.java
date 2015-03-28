package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotType;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlots;
import org.asciicerebrum.mydndgame.domain.setup.PersonalizedBodySlotSetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlotFactoryTest {

    private PersonalizedBodySlotFactory factory;

    private Campaign campaign;

    private ApplicationContext applicationContext;

    private DndCharacter holder;

    private Weapon item;

    public PersonalizedBodySlotFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new PersonalizedBodySlotFactory();
        this.campaign = new Campaign();
        this.applicationContext = mock(ApplicationContext.class,
                withSettings()
                .extraInterfaces(ConfigurableApplicationContext.class));
        final PersonalizedBodySlots pbSlots = new PersonalizedBodySlots();
        final PersonalizedBodySlot pbSlot = new PersonalizedBodySlot();
        final BodySlot slot = new BodySlot();
        final BodySlotType slotType = new BodySlotType();
        slotType.setId(new UniqueId("typeId"));
        slot.setBodySlotType(slotType);
        slot.setIsPrimaryAttackSlot(new AttackAbility(false));
        pbSlot.setBodySlot(slot);
        pbSlots.add(pbSlot);
        this.holder = new DndCharacter();
        this.holder.setUniqueId(new UniqueId("holderId"));
        this.holder.setPersonalizedBodySlots(pbSlots);

        this.item = new Weapon();
        this.item.setUniqueId(new UniqueId("itemId"));

        ApplicationContextProvider ctxProvider
                = new ApplicationContextProvider();
        ctxProvider.setApplicationContext(this.applicationContext);

        when(this.applicationContext.getBean("typeId", BodySlotType.class))
                .thenReturn(slotType);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final PersonalizedBodySlotSetup setup = new PersonalizedBodySlotSetup();
        final Reassignments reassignments = new Reassignments();

        this.factory.newEntity(setup, this.campaign, reassignments);
    }

    private void makeComplete(PersonalizedBodySlotSetup setup) {
        setup.setBodySlotType("typeId");
        setup.setItem("itemId");
        setup.setProperty(SetupProperty.BODY_SLOT_HOLDER, "holderId");
    }

    @Test
    public void newEntityNullHolderTest() {
        final PersonalizedBodySlotSetup setup = new PersonalizedBodySlotSetup();
        this.makeComplete(setup);
        final Reassignments reassignments = new Reassignments();

        final PersonalizedBodySlot result
                = this.factory.newEntity(setup, this.campaign, reassignments);

        assertNull(result);
    }

    @Test
    public void newEntityNullItemTest() {
        final PersonalizedBodySlotSetup setup = new PersonalizedBodySlotSetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);
        this.campaign.registerUniqueEntity(this.holder);

        final PersonalizedBodySlot result
                = this.factory.newEntity(setup, this.campaign, reassignments);

        assertNull(result);
    }

    @Test
    public void newEntityCompleteTest() {
        final PersonalizedBodySlotSetup setup = new PersonalizedBodySlotSetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);
        this.campaign.registerUniqueEntity(this.holder);
        this.campaign.registerUniqueEntity(this.item);

        final PersonalizedBodySlot result
                = this.factory.newEntity(setup, this.campaign, reassignments);

        assertNotNull(result);
    }

    @Test
    public void newEntityCorrectTypeTest() {
        final PersonalizedBodySlotSetup setup = new PersonalizedBodySlotSetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);
        this.campaign.registerUniqueEntity(this.holder);
        this.campaign.registerUniqueEntity(this.item);

        final PersonalizedBodySlot result
                = this.factory.newEntity(setup, this.campaign, reassignments);

        assertEquals("typeId", result.getBodySlotType().getId().getValue());
    }

    @Test
    public void retrieveItemItemFoundTest() {
        final PersonalizedBodySlotSetup setup = new PersonalizedBodySlotSetup();

        this.makeComplete(setup);
        final UniqueEntity entity = new Weapon();
        entity.setUniqueId(new UniqueId("itemId"));
        this.campaign.registerUniqueEntity(entity);

        final UniqueEntity result = this.factory.retrieveItem(
                setup, this.campaign);

        assertEquals(entity, result);
    }

}
