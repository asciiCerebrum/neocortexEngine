package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotType;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.setup.PersonalizedBodySlotSetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
public class PersonalizedBodySlotFactoryTest {

    private PersonalizedBodySlotFactory factory;

    private Campaign campaign;

    private ApplicationContext applicationContext;

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

        this.factory.setCampaign(this.campaign);
        this.factory.setContext(this.applicationContext);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final PersonalizedBodySlotSetup setup = new PersonalizedBodySlotSetup();
        final Reassignments reassignments = new Reassignments();

        this.factory.newEntity(setup, reassignments);
    }

    private void makeComplete(PersonalizedBodySlotSetup setup) {
        setup.setBodySlotType("typeId");
        setup.setItem("itemId");
    }

    @Test
    public void newEntityCompleteTest() {
        final PersonalizedBodySlotSetup setup = new PersonalizedBodySlotSetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);

        this.factory.newEntity(setup, reassignments);

        verify(this.applicationContext, times(1))
                .getBean("typeId", BodySlotType.class);
    }

    @Test
    public void newEntityWithPrimaryAttackTest() {
        final PersonalizedBodySlotSetup setup = new PersonalizedBodySlotSetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);
        setup.setIsPrimaryAttackSlot("true");

        final PersonalizedBodySlot result
                = this.factory.newEntity(setup, reassignments);

        assertTrue(result.getIsPrimaryAttackSlot().isValue());
    }

    @Test
    public void assignItemNoItemFoundTest() {
        final PersonalizedBodySlotSetup setup = new PersonalizedBodySlotSetup();
        final PersonalizedBodySlot pSlot = new PersonalizedBodySlot();

        this.makeComplete(setup);

        final boolean result = this.factory.assignItem(setup, pSlot);

        assertTrue(result);
    }

    @Test
    public void assignItemItemFoundTest() {
        final PersonalizedBodySlotSetup setup = new PersonalizedBodySlotSetup();
        final PersonalizedBodySlot pSlot = new PersonalizedBodySlot();

        this.makeComplete(setup);
        final UniqueEntity entity = new Weapon();
        entity.setUniqueId(new UniqueId("itemId"));
        this.campaign.registerUniqueEntity(entity);

        final boolean result = this.factory.assignItem(setup, pSlot);

        assertFalse(result);
    }

}
