package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot.Facet;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlotsTest {

    private PersonalizedBodySlots slots;

    public PersonalizedBodySlotsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.slots = new PersonalizedBodySlots();

        final PersonalizedBodySlot slot = new PersonalizedBodySlot();
        slot.setItemId(new UniqueId("item"));
        final BodySlot bodySlot = new BodySlot();
        bodySlot.setIsPrimaryAttackSlot(new AttackAbility(true));
        slot.setBodySlot(bodySlot);

        this.slots.add(slot);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findFirstSimilarOneFacetNonSimilarityTest() {
        final PersonalizedBodySlot bluePrint = new PersonalizedBodySlot();
        final BodySlot bodySlot = new BodySlot();
        bodySlot.setIsPrimaryAttackSlot(new AttackAbility(false));
        bluePrint.setBodySlot(bodySlot);

        assertNull(this.slots.findFirstSimilar(bluePrint,
                new Facet[]{Facet.PRIMARY_ATTACK_SLOT}));
    }

    @Test
    public void findFirstSimilarOneFacetSimilarityTest() {
        final PersonalizedBodySlot bluePrint = new PersonalizedBodySlot();
        bluePrint.setItemId(new UniqueId("item"));

        assertEquals(this.slots.iterator().next(),
                this.slots.findFirstSimilar(bluePrint,
                        new Facet[]{Facet.ITEM}));
    }

}
