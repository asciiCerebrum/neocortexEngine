package org.asciicerebrum.neocortexengine.facades.game;

import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DamageType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DamageTypes;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponPrototype;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class DefaultWeaponServiceFacadeTest {

    private DefaultWeaponServiceFacade facade;

    private SituationContextService situationContextService;

    public DefaultWeaponServiceFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.facade = new DefaultWeaponServiceFacade();
        this.situationContextService = mock(SituationContextService.class);

        this.facade.setSituationContextService(this.situationContextService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getDamageTypeDefaultTest() {
        final DamageType defaultDamageType = new DamageType();
        defaultDamageType.setUniqueId(new UniqueId("defaultDamageType"));
        final DamageTypes defaultDamageTypes = new DamageTypes();
        defaultDamageTypes.addDamageType(defaultDamageType);
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weapon"));
        final WeaponPrototype weaponProto = new WeaponPrototype();
        weaponProto.setDefaultDamageTypes(defaultDamageTypes);
        weapon.setInventoryItemPrototype(weaponProto);
        final DndCharacter dndCharacter = new DndCharacter();

        final DamageType damageType = this.facade.getDamageType(weapon,
                dndCharacter);

        assertEquals(defaultDamageType, damageType);
    }

    @Test
    public void getDamageTypeFromSituationContextTest() {
        final DamageType contextDamageType = new DamageType();
        contextDamageType.setUniqueId(new UniqueId("contextDamageType"));
        final DndCharacter dndCharacter = new DndCharacter();

        when(this.situationContextService.getItemDamageType(
                new UniqueId("weapon"), dndCharacter))
                .thenReturn(contextDamageType);

        final DamageType defaultDamageType = new DamageType();
        defaultDamageType.setUniqueId(new UniqueId("defaultDamageType"));
        final DamageTypes defaultDamageTypes = new DamageTypes();
        defaultDamageTypes.addDamageType(defaultDamageType);
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weapon"));
        final WeaponPrototype weaponProto = new WeaponPrototype();
        weaponProto.setDefaultDamageTypes(defaultDamageTypes);
        weapon.setInventoryItemPrototype(weaponProto);

        final DamageType damageType = this.facade.getDamageType(weapon,
                dndCharacter);

        assertEquals(contextDamageType, damageType);
    }

}
