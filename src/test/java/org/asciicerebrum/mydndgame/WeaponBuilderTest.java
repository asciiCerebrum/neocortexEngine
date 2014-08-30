package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;
import org.asciicerebrum.mydndgame.interfaces.observing.ObservableDelegate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class WeaponBuilderTest {

    private WeaponBuilder weaponBuilder;

    private WeaponSetup setup;

    private Weapon weapon;

    private ApplicationContext context;

    private WeaponSpecialAbility weaponSpecialAbility;

    private IObserver observer;

    private ObservableDelegate observableDelegate;

    public WeaponBuilderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.weapon = new Weapon();
        this.weaponSpecialAbility = new WeaponSpecialAbility();

        final String name = "someName";
        final String specAbName = "someSpecialAbility";

        this.context = mock(ApplicationContext.class);
        this.observer = mock(IObserver.class);
        this.observableDelegate = mock(ObservableDelegate.class);
        this.weapon.setObservableDelegate(this.observableDelegate);

        when(this.context.getBean(name, Weapon.class)).thenReturn(this.weapon);
        when(this.context.getBean(specAbName, WeaponSpecialAbility.class))
                .thenReturn(this.weaponSpecialAbility);
        when(this.observer.getHook()).thenReturn(ObserverHook.ATTACK);

        this.weaponSpecialAbility.getObservers().add(this.observer);
        this.setup = new WeaponSetup();

        this.setup.setId("someId");
        this.setup.setName(name);
        this.setup.getSpecialAbilities().add(specAbName);

        this.weaponBuilder = new WeaponBuilder(this.setup, context);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of build method, of class WeaponBuilder.
     */
    @Test
    public void testBuild() {
        IWeapon localWeapon = this.weaponBuilder.build();

        assertNotNull(localWeapon);
    }

    @Test
    public void testBuildCorrectId() {
        IWeapon localWeapon = this.weaponBuilder.build();

        assertEquals("someId", localWeapon.getId());
    }

    @Test
    public void testBuildCorrectSpecialAbilitiesCount() {
        IWeapon localWeapon = this.weaponBuilder.build();

        assertEquals(1, localWeapon.getSpecialAbilities().size());
    }

    @Test
    public void testBuildCorrectSpecialAbilitiesObject() {
        IWeapon localWeapon = this.weaponBuilder.build();

        assertEquals(this.weaponSpecialAbility,
                localWeapon.getSpecialAbilities().get(0));
    }

    @Test
    public void testBuildCorrectSpecialAbilitiesObserver() {
        this.weaponBuilder.build();

        verify(this.observableDelegate).registerListener(
                ObserverHook.ATTACK, this.observer,
                this.weapon.getObserverMap());
    }

}
