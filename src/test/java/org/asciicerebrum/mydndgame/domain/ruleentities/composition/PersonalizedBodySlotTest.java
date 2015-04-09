package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.AttackAbility;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotType;
import org.asciicerebrum.mydndgame.domain.ruleentities.SpecialAbilities;
import org.asciicerebrum.mydndgame.domain.ruleentities.SpecialAbility;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponPrototype;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlotTest {

    private PersonalizedBodySlot pbSlot;

    private Weapon item;

    private Bonus itemBonus;

    private UniqueEntityResolver resolver;

    private UniqueEntity context;

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
        final SpecialAbilities specAbs = this.item.getSpecialAbilities();
        final SpecialAbility specAb = new SpecialAbility();
        final Boni boni = new Boni();
        this.itemBonus = new Bonus();
        boni.addBonus(this.itemBonus);
        specAb.setBoni(boni);
        specAbs.add(specAb);

        this.pbSlot.setItemId(this.item.getUniqueId());

        final BodySlot slot = new BodySlot();
        final BodySlotType type = new BodySlotType();
        slot.setBodySlotType(type);
        slot.setIsPrimaryAttackSlot(new AttackAbility(true));
        this.pbSlot.setBodySlot(slot);

        this.item.setInventoryItemPrototype(new WeaponPrototype());

        this.resolver = mock(UniqueEntityResolver.class);
        when(this.resolver.resolve(this.item.getUniqueId()))
                .thenReturn(this.item);

        this.context = new Weapon();
        this.context.setUniqueId(new UniqueId("context"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBoniSizeTest() {
        assertEquals(1L, Iterators.size(this.pbSlot
                .getBoni(this.context, this.resolver).iterator()));
    }

    @Test
    public void getBoniContentTest() {
        assertEquals(this.itemBonus, this.pbSlot.getBoni(this.context,
                this.resolver).iterator().next().getBonus());
    }

    @Test
    public void getBoniEmptyTest() {
        this.pbSlot.setItemId(new UniqueId("new"));

        assertEquals(0L, Iterators.size(this.pbSlot
                .getBoni(this.context, this.resolver).iterator()));
    }

    @Test
    public void containsItemEmptyNullTest() {
        this.pbSlot.setItemId(null);
        assertTrue(this.pbSlot.containsItem(null));
    }

    @Test
    public void containsItemNonEmptyNullTest() {
        this.pbSlot.setItemId(null);
        final UniqueId testItem = new UniqueId("testitem");
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
        this.pbSlot.setItemId(null);

        assertTrue(Facet.ITEM.isSimilar(this.pbSlot, candidate));
    }

    @Test
    public void isSimilarItemBothDifferentTest() {
        final PersonalizedBodySlot candidate = new PersonalizedBodySlot();
        candidate.setItemId(new UniqueId(""));

        assertFalse(Facet.ITEM.isSimilar(this.pbSlot, candidate));
    }

    @Test
    public void isSimilarItemFirstNullTest() {
        final PersonalizedBodySlot candidate = new PersonalizedBodySlot();
        candidate.setItemId(new UniqueId(""));
        this.pbSlot.setItemId(null);

        assertFalse(Facet.ITEM.isSimilar(this.pbSlot, candidate));
    }

    @Test
    public void isSimilarItemSecondNullTest() {
        final PersonalizedBodySlot candidate = new PersonalizedBodySlot();

        assertFalse(Facet.ITEM.isSimilar(this.pbSlot, candidate));
    }

}
