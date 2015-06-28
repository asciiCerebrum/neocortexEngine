package org.asciicerebrum.neocortexengine.mechanics.observertriggers;

import org.asciicerebrum.neocortexengine.domain.core.particles.BooleanParticle;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.StateRegistry;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Encumbrance;
import org.asciicerebrum.neocortexengine.domain.ruleentities.SizeCategory;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponPrototype;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponPrototypes;
import org.asciicerebrum.neocortexengine.facades.game.CharacterServiceFacade;
import org.asciicerebrum.neocortexengine.facades.game.WeaponServiceFacade;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
public class WeaponFinesseObserverTriggerTest {

    private WeaponFinesseObserverTrigger weaponFinesseObserverTrigger;

    private CharacterServiceFacade characterServiceFacade;

    private StateRegistry.StateParticle stateParticle;

    private Bonus removeBonus;

    private Bonus replacementBonus;

    private SituationContextService situationContextService;

    private Encumbrance validEncumbrance;

    private WeaponPrototypes validWeaponPrototypes;

    private WeaponServiceFacade weaponServiceFacade;

    private WeaponPrototype protoB;

    public WeaponFinesseObserverTriggerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.weaponFinesseObserverTrigger = new WeaponFinesseObserverTrigger();
        this.characterServiceFacade = mock(CharacterServiceFacade.class);
        this.stateParticle = StateRegistry.StateParticle.ACTIVE_ITEM;
        this.removeBonus = new Bonus();
        this.replacementBonus = new Bonus();
        this.situationContextService = mock(SituationContextService.class);
        this.validEncumbrance = new Encumbrance();
        this.validWeaponPrototypes = new WeaponPrototypes();

        final WeaponPrototype protoA = new WeaponPrototype();
        this.protoB = new WeaponPrototype();
        this.validWeaponPrototypes.add(protoA);
        this.validWeaponPrototypes.add(this.protoB);

        this.weaponServiceFacade = mock(WeaponServiceFacade.class);

        this.weaponFinesseObserverTrigger.setCharacterServiceFacade(
                this.characterServiceFacade);
        this.weaponFinesseObserverTrigger.setRegistryKey(this.stateParticle);
        this.weaponFinesseObserverTrigger.setRemoveBonus(this.removeBonus);
        this.weaponFinesseObserverTrigger.setReplacementBonus(
                this.replacementBonus);
        this.weaponFinesseObserverTrigger.setSituationContextService(
                this.situationContextService);
        this.weaponFinesseObserverTrigger.setValidEncumbrance(
                this.validEncumbrance);
        this.weaponFinesseObserverTrigger.setValidWeaponPrototypes(
                this.validWeaponPrototypes);
        this.weaponFinesseObserverTrigger.setWeaponServiceFacade(
                this.weaponServiceFacade);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void determineSituationContextValidityWrongSizeTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Weapon usedWeapon = new Weapon();
        final SizeCategory sizeWeapon = new SizeCategory();
        sizeWeapon.setUniqueId(new UniqueId("weaponSize"));
        final SizeCategory sizeCharacter = new SizeCategory();
        sizeCharacter.setUniqueId(new UniqueId("characterSize"));

        when(this.weaponServiceFacade.getSize(usedWeapon, dndCharacter))
                .thenReturn(sizeWeapon);
        when(this.characterServiceFacade.getSize(dndCharacter))
                .thenReturn(sizeCharacter);

        final boolean sitConValidity
                = this.weaponFinesseObserverTrigger
                .determineSituationContextValidity(dndCharacter, usedWeapon);

        assertFalse(sitConValidity);
    }

    @Test
    public void determineSituationContextValidityRightEncumbranceTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Weapon usedWeapon = new Weapon();
        final SizeCategory size = new SizeCategory();
        size.setUniqueId(new UniqueId("validsize"));

        when(this.weaponServiceFacade.getSize(usedWeapon, dndCharacter))
                .thenReturn(size);
        when(this.characterServiceFacade.getSize(dndCharacter))
                .thenReturn(size);
        when(this.weaponServiceFacade.hasEncumbrance(this.validEncumbrance,
                usedWeapon, dndCharacter)).thenReturn(Boolean.TRUE);

        final boolean sitConValidity
                = this.weaponFinesseObserverTrigger
                .determineSituationContextValidity(dndCharacter, usedWeapon);

        assertTrue(sitConValidity);
    }

    @Test
    public void determineSituationContextValidityRightPrototypeTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Weapon usedWeapon = new Weapon();
        usedWeapon.setInventoryItemPrototype(this.protoB);
        final SizeCategory size = new SizeCategory();
        size.setUniqueId(new UniqueId("validsize"));

        when(this.weaponServiceFacade.getSize(usedWeapon, dndCharacter))
                .thenReturn(size);
        when(this.characterServiceFacade.getSize(dndCharacter))
                .thenReturn(size);
        when(this.weaponServiceFacade.hasEncumbrance(this.validEncumbrance,
                usedWeapon, dndCharacter)).thenReturn(Boolean.FALSE);

        final boolean sitConValidity
                = this.weaponFinesseObserverTrigger
                .determineSituationContextValidity(dndCharacter, usedWeapon);

        assertTrue(sitConValidity);
    }

    @Test
    public void determineSituationContextValidityAllWrongTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Weapon usedWeapon = new Weapon();
        usedWeapon.setInventoryItemPrototype(new WeaponPrototype());
        final SizeCategory size = new SizeCategory();
        size.setUniqueId(new UniqueId("validsize"));

        when(this.weaponServiceFacade.getSize(usedWeapon, dndCharacter))
                .thenReturn(size);
        when(this.characterServiceFacade.getSize(dndCharacter))
                .thenReturn(size);
        when(this.weaponServiceFacade.hasEncumbrance(this.validEncumbrance,
                usedWeapon, dndCharacter)).thenReturn(Boolean.FALSE);

        final boolean sitConValidity
                = this.weaponFinesseObserverTrigger
                .determineSituationContextValidity(dndCharacter, usedWeapon);

        assertFalse(sitConValidity);
    }

    @Test
    public void triggerNoWeaponTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final ContextBoni ctxBoni = new ContextBoni();
        final Armor armor = new Armor();

        final Object triggerResult
                = this.weaponFinesseObserverTrigger.trigger(
                        ctxBoni, dndCharacter, armor);
        assertEquals(ctxBoni, triggerResult);
    }

    @Test
    public void triggerNoFinesseTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final ContextBoni ctxBoni = new ContextBoni();
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weapon"));

        final BooleanParticle booleanParticle = new BooleanParticle(false);

        when(this.situationContextService.getFlagForKey(
                this.stateParticle, dndCharacter, weapon.getUniqueId()))
                .thenReturn(booleanParticle);

        final SizeCategory size = new SizeCategory();
        size.setUniqueId(new UniqueId("validsize"));

        when(this.weaponServiceFacade.getSize(weapon, dndCharacter))
                .thenReturn(size);
        when(this.characterServiceFacade.getSize(dndCharacter))
                .thenReturn(size);
        when(this.weaponServiceFacade.hasEncumbrance(this.validEncumbrance,
                weapon, dndCharacter)).thenReturn(Boolean.TRUE);

        final Object triggerResult
                = this.weaponFinesseObserverTrigger.trigger(
                        ctxBoni, dndCharacter, weapon);
        assertEquals(ctxBoni, triggerResult);
    }

    @Test
    public void triggerNoValidityTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final ContextBoni ctxBoni = new ContextBoni();
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weapon"));
        weapon.setInventoryItemPrototype(new WeaponPrototype());

        final BooleanParticle booleanParticle = new BooleanParticle(true);

        when(this.situationContextService.getFlagForKey(
                this.stateParticle, dndCharacter, weapon.getUniqueId()))
                .thenReturn(booleanParticle);

        final SizeCategory size = new SizeCategory();
        size.setUniqueId(new UniqueId("validsize"));

        when(this.weaponServiceFacade.getSize(weapon, dndCharacter))
                .thenReturn(size);
        when(this.characterServiceFacade.getSize(dndCharacter))
                .thenReturn(size);
        when(this.weaponServiceFacade.hasEncumbrance(this.validEncumbrance,
                weapon, dndCharacter)).thenReturn(Boolean.FALSE);

        final Object triggerResult
                = this.weaponFinesseObserverTrigger.trigger(
                        ctxBoni, dndCharacter, weapon);
        assertEquals(ctxBoni, triggerResult);
    }

}
