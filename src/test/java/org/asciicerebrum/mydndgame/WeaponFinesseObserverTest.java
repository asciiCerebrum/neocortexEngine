package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacterSetup;
import org.asciicerebrum.mydndgame.interfaces.entities.IEncumbrance;
import org.asciicerebrum.mydndgame.interfaces.entities.IRace;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.ISizeCategory;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class WeaponFinesseObserverTest {

    private WeaponFinesseObserver wfObserver;

    private List<IBonus> bonusList;

    private ISituationContext sitcon;

    private IBonus standardBonus;

    private IBonus replacementBonus;

    private IWeapon weapon;

    private List<IWeapon> validWeapons;

    private ICharacterSetup setup;

    public WeaponFinesseObserverTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.sitcon = new SituationContext();
        this.bonusList = new ArrayList<IBonus>();
        this.standardBonus = new Bonus();
        this.replacementBonus = new Bonus();
        this.validWeapons = new ArrayList<IWeapon>();

        IBodySlotType bsType = new BodySlotType();
        BodySlot bs = new BodySlot();
        this.weapon = new Weapon();
        bs.setItem(this.weapon);

        IRace race = new Race();

        ISizeCategory raceSize = new SizeCategory();
        IEncumbrance encumbrance = new Encumbrance();

        race.setSize(raceSize);
        this.weapon.setSize(raceSize);
        this.weapon.setEncumbrance(encumbrance);
        this.weapon.setId("someWeaponId");
        this.weapon.setName("someWeaponName");

        IWeapon weaponA = new Weapon();
        weaponA.setName("weaponA");

        IWeapon weaponB = new Weapon();
        weaponB.setName("weaponB");

        IWeapon weaponC = new Weapon();
        weaponC.setName("someWeaponName");

        this.validWeapons.add(weaponA);
        this.validWeapons.add(weaponB);
        this.validWeapons.add(weaponC);

        this.setup = new CharacterSetup();

        ICharacter character = mock(ICharacter.class);
        when(character.getBodySlotByType(bsType)).thenReturn(bs);
        when(character.getRace()).thenReturn(race);
        when(character.getSetup()).thenReturn(this.setup);

        this.sitcon.setCharacter(character);
        this.sitcon.setBodySlotType(bsType);

        this.bonusList.add(new Bonus()); // some unaffected pseudo bonus
        this.bonusList.add(this.standardBonus);        

        IBonus referenceBonus = mock(IBonus.class);
        when(referenceBonus.resembles(this.standardBonus))
                .thenReturn(Boolean.TRUE);

        this.wfObserver = new WeaponFinesseObserver();
        this.wfObserver.setValidEncumbrance(encumbrance);
        this.wfObserver.setRegistryKey("someRegKey");
        this.wfObserver.setRemoveBonus(referenceBonus);
        this.wfObserver.setReplacementBonus(this.replacementBonus);
        this.wfObserver.setValidWeaponPrototypes(this.validWeapons);

        this.setup.getStateRegistry().put(
                this.wfObserver.getRegistryKey() + "."
                + this.weapon.getId(), "true");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of trigger method, of class WeaponFinesseObserver. Valid by
     * encumbrance.
     */
    @Test
    public void testTriggerBonusListSize() {
        List<IBonus> triggeredBoni
                = (List<IBonus>) this.wfObserver
                .trigger(this.bonusList, this.sitcon);
        assertEquals(2, triggeredBoni.size());
    }

    /**
     * Test of trigger method, of class WeaponFinesseObserver. Valid by
     * encumbrance.
     */
    @Test
    public void testTriggerBonusListElement() {
        List<IBonus> triggeredBoni
                = (List<IBonus>) this.wfObserver
                .trigger(this.bonusList, this.sitcon);
        assertEquals(this.replacementBonus, triggeredBoni.get(1));
    }

    /**
     * Test of trigger method, of class WeaponFinesseObserver. Invalid by wrong
     * size.
     */
    @Test
    public void testTriggerBonusListSizeWrongSizeCategory() {

        ISizeCategory wrongSize = new SizeCategory();
        this.weapon.setSize(wrongSize);

        List<IBonus> triggeredBoni
                = (List<IBonus>) this.wfObserver
                .trigger(this.bonusList, this.sitcon);
        assertEquals(2, triggeredBoni.size());
    }

    /**
     * Test of trigger method, of class WeaponFinesseObserver. Invalid by wrong
     * size.
     */
    @Test
    public void testTriggerBonusListElementEqualWrongSizeCategory() {

        ISizeCategory wrongSize = new SizeCategory();
        this.weapon.setSize(wrongSize);

        List<IBonus> triggeredBoni
                = (List<IBonus>) this.wfObserver
                .trigger(this.bonusList, this.sitcon);
        assertEquals(this.standardBonus, triggeredBoni.get(1));
    }

    /**
     * Test of trigger method, of class WeaponFinesseObserver. Invalid by wrong
     * size.
     */
    @Test
    public void testTriggerBonusListElementNotEqualWrongSizeCategory() {

        ISizeCategory wrongSize = new SizeCategory();
        this.weapon.setSize(wrongSize);

        List<IBonus> triggeredBoni
                = (List<IBonus>) this.wfObserver
                .trigger(this.bonusList, this.sitcon);
        assertNotEquals(this.replacementBonus, triggeredBoni.get(1));
    }

    /**
     * Test of trigger method, of class WeaponFinesseObserver. Valid by weapon
     * prototype.
     */
    @Test
    public void testTriggerBonusListElementWrongEncumbranceValidWeapon() {

        IEncumbrance wrongEncumbrance = new Encumbrance();
        this.weapon.setEncumbrance(wrongEncumbrance);

        List<IBonus> triggeredBoni
                = (List<IBonus>) this.wfObserver
                .trigger(this.bonusList, this.sitcon);
        assertEquals(this.replacementBonus, triggeredBoni.get(1));
    }

    /**
     * Test of trigger method, of class WeaponFinesseObserver. Invalid at all.
     */
    @Test
    public void testTriggerBonusListElementInvalid() {

        IEncumbrance wrongEncumbrance = new Encumbrance();
        this.weapon.setEncumbrance(wrongEncumbrance);
        this.weapon.setName("wrongWeapon");

        List<IBonus> triggeredBoni
                = (List<IBonus>) this.wfObserver
                .trigger(this.bonusList, this.sitcon);
        assertEquals(this.standardBonus, triggeredBoni.get(1));
    }

    /**
     * Test of trigger method, of class WeaponFinesseObserver. Finesse unused.
     */
    @Test
    public void testTriggerBonusListElementFinesseUnused() {

        this.setup.getStateRegistry().put(
                this.wfObserver.getRegistryKey() + "."
                + this.weapon.getId(), "false");

        List<IBonus> triggeredBoni
                = (List<IBonus>) this.wfObserver
                .trigger(this.bonusList, this.sitcon);
        assertEquals(this.standardBonus, triggeredBoni.get(1));
    }

}
