package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacterSetup;
import org.asciicerebrum.mydndgame.interfaces.entities.IDamageType;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.Slotable;
import org.asciicerebrum.mydndgame.interfaces.entities.StateRegistryKey;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class SituationContextServiceTest {

    private SituationContextService sitConService;

    private ICharacter character;

    private ICharacterSetup setup;

    public SituationContextServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.sitConService = new SituationContextService();
        this.character = mock(ICharacter.class);
        this.setup = mock(ICharacterSetup.class);

        this.sitConService.setCharacter(this.character);
        when(this.character.getSetup()).thenReturn(this.setup);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAttackMode method, of class SituationContextService.
     */
    @Test
    public void testGetAttackMode() {
        this.sitConService.getAttackMode();

        verify(this.setup, times(1)).getStateRegistryBeanForKey(
                StateRegistryKey.ACTIVE_ATTACK_MODE.toString(),
                WeaponCategory.class);
    }

    /**
     * Test of getDamageType method, of class SituationContextService.
     */
    @Test
    public void testGetDamageTypeNullSetup() {
        IDamageType damageType = this.sitConService.getDamageType();

        assertNull(damageType);
    }

    @Test
    public void testGetDamageTypeNoItem() {
        BodySlotType bsType = mock(BodySlotType.class);
        Slotable bs = mock(Slotable.class);
        when(this.setup.getStateRegistryBeanForKey(
                StateRegistryKey.ACTIVE_BODY_SLOT_TYPE.toString(),
                BodySlotType.class)).thenReturn(bsType);
        when(this.character.getBodySlotByType(bsType)).thenReturn(bs);
        when(bs.getItem()).thenReturn(null);

        IDamageType damageType = this.sitConService.getDamageType();

        assertNull(damageType);
    }

    @Test
    public void testGetDamageTypeItemNoWeapon() {
        BodySlotType bsType = mock(BodySlotType.class);
        Slotable bs = mock(Slotable.class);
        IInventoryItem item = mock(IInventoryItem.class);
        when(this.setup.getStateRegistryBeanForKey(
                StateRegistryKey.ACTIVE_BODY_SLOT_TYPE.toString(),
                BodySlotType.class)).thenReturn(bsType);
        when(this.character.getBodySlotByType(bsType)).thenReturn(bs);
        when(bs.getItem()).thenReturn(item);

        IDamageType damageType = this.sitConService.getDamageType();

        assertNull(damageType);
    }

    @Test
    public void testGetDamageTypeWeaponWithDamage() {
        BodySlotType bsType = mock(BodySlotType.class);
        Slotable bs = mock(Slotable.class);
        IWeapon weapon = mock(IWeapon.class);
        DamageType damageType = mock(DamageType.class);
        when(this.setup.getStateRegistryBeanForKey(
                StateRegistryKey.ACTIVE_BODY_SLOT_TYPE.toString(),
                BodySlotType.class)).thenReturn(bsType);
        when(this.character.getBodySlotByType(bsType)).thenReturn(bs);
        when(bs.getItem()).thenReturn(weapon);
        when(weapon.getId()).thenReturn("id");
        when(this.setup.getStateRegistryBeanForKey(
                StateRegistryKey.WEAPON_DAMAGE_TYPE_PREFIX.toString() + "id",
                DamageType.class)).thenReturn(damageType);

        IDamageType resultDamageType = this.sitConService.getDamageType();

        assertEquals(damageType, resultDamageType);
    }

    @Test
    public void testGetDamageTypeWeaponNoDamage() {
        BodySlotType bsType = mock(BodySlotType.class);
        Slotable bs = mock(Slotable.class);
        IWeapon weapon = mock(IWeapon.class);
        DamageType defaultDamageType = mock(DamageType.class);
        when(this.setup.getStateRegistryBeanForKey(
                StateRegistryKey.ACTIVE_BODY_SLOT_TYPE.toString(),
                BodySlotType.class)).thenReturn(bsType);
        when(this.character.getBodySlotByType(bsType)).thenReturn(bs);
        when(bs.getItem()).thenReturn(weapon);
        when(weapon.getId()).thenReturn("id");
        when(this.setup.getStateRegistryBeanForKey(
                StateRegistryKey.WEAPON_DAMAGE_TYPE_PREFIX.toString() + ".id",
                DamageType.class)).thenReturn(null);
        when(weapon.getDefaultDamageType()).thenReturn(defaultDamageType);

        IDamageType resultDamageType = this.sitConService.getDamageType();

        assertEquals(defaultDamageType, resultDamageType);
    }

}
