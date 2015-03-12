package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotType;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot.Facet;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlotTest {

    private PersonalizedBodySlot pbSlot;

    private Weapon item;

    public PersonalizedBodySlotTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.pbSlot = new PersonalizedBodySlot();
        this.item = new Weapon();
        this.item.setUniqueId(new UniqueId("item"));

        this.pbSlot.setItem(this.item);

        final BodySlot slot = new BodySlot();
        final BodySlotType type = new BodySlotType();
        slot.setBodySlotType(type);
        slot.setIsPrimaryAttackSlot(new AttackAbility(true));
        this.pbSlot.setBodySlot(slot);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBonusSourcesSizeTest() {
        assertEquals(1L, Iterators.size(this.pbSlot
                .getBonusSources().iterator()));
    }

    @Test
    public void getBonusSourcesContentTest() {
        assertEquals(this.item, this.pbSlot.getBonusSources()
                .iterator().next());
    }

    @Test
    public void getBonusSourcesEmptyTest() {
        this.pbSlot.setItem(new UniqueEntity() {
        });

        assertEquals(0L, Iterators.size(this.pbSlot
                .getBonusSources().iterator()));
    }

    @Test
    public void containsItemEmptyNullTest() {
        this.pbSlot.setItem(null);
        assertTrue(this.pbSlot.containsItem(null));
    }

    @Test
    public void containsItemNonEmptyNullTest() {
        this.pbSlot.setItem(null);
        final Weapon testItem = new Weapon();
        testItem.setUniqueId(new UniqueId("testitem"));
        assertFalse(this.pbSlot.containsItem(testItem));
    }

    @Test
    public void isSimilarUnsimilarTest() {
        final PersonalizedBodySlot bluePrint = new PersonalizedBodySlot();

        final BodySlot slot = new BodySlot();
        final BodySlotType type = new BodySlotType();
        slot.setBodySlotType(type);
        bluePrint.setBodySlot(slot);

        final Facet[] facets = new Facet[]{
            Facet.BODY_SLOT_TYPE
        };

        assertFalse(this.pbSlot.isSimilar(bluePrint, facets));
    }

    @Test
    public void getBodySlotTypeNullTest() {
        this.pbSlot.setBodySlot(null);
        assertNull(this.pbSlot.getBodySlotType());
    }

    @Test
    public void getIsPrimaryAttackSlotNullTest() {
        this.pbSlot.setBodySlot(null);
        assertNull(this.pbSlot.getIsPrimaryAttackSlot());
    }

    @Test
    public void getIsPrimaryAttackSlotTrueTest() {
        assertTrue(this.pbSlot.getIsPrimaryAttackSlot().isValue());
    }

    @Test
    public void getCounterBodySlotNullTest() {
        this.pbSlot.setBodySlot(null);
        assertNull(this.pbSlot.getCounterBodySlot());
    }

    @Test
    public void isSimilarItemBothNullTest() {
        final PersonalizedBodySlot candidate = new PersonalizedBodySlot();
        this.pbSlot.setItem(null);

        assertTrue(Facet.ITEM.isSimilar(this.pbSlot, candidate));
    }

    @Test
    public void isSimilarItemBothDifferentTest() {
        final PersonalizedBodySlot candidate = new PersonalizedBodySlot();
        candidate.setItem(new Weapon());

        assertFalse(Facet.ITEM.isSimilar(this.pbSlot, candidate));
    }

    @Test
    public void isSimilarItemFirstNullTest() {
        final PersonalizedBodySlot candidate = new PersonalizedBodySlot();
        candidate.setItem(new Weapon());
        this.pbSlot.setItem(null);

        assertFalse(Facet.ITEM.isSimilar(this.pbSlot, candidate));
    }

    @Test
    public void isSimilarItemSecondNullTest() {
        final PersonalizedBodySlot candidate = new PersonalizedBodySlot();

        assertFalse(Facet.ITEM.isSimilar(this.pbSlot, candidate));
    }

}
