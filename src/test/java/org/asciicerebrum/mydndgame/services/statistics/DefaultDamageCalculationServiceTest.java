package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponPrototype;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class DefaultDamageCalculationServiceTest {

    private DefaultDamageCalculationService service;

    private BonusCalculationService bonusService;

    private SituationContextService situationContextService;

    private DiceAction damageAction;

    public DefaultDamageCalculationServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.service = new DefaultDamageCalculationService();
        this.bonusService = mock(BonusCalculationService.class);
        this.situationContextService = mock(SituationContextService.class);
        this.damageAction = new DiceAction();

        this.service.setBonusService(this.bonusService);
        this.service.setSituationContextService(this.situationContextService);
        this.service.setDamageAction(this.damageAction);

        final BonusValueTuple damageBonus
                = new BonusValueTuple(new BonusValue(3L));
        final WeaponCategory itemAttackMode
                = new WeaponCategory();

        when(this.bonusService.calculateBonusValues(
                (BonusSources) anyObject(),
                (BonusTargets) anyObject(),
                (Weapon) anyObject(),
                (ObserverSources) anyObject(),
                (ObserverHooks) anyObject(),
                (DndCharacter) anyObject())).thenReturn(damageBonus);
        when(this.situationContextService.getItemAttackMode(
                (UniqueId) anyObject(),
                (DndCharacter) anyObject())).thenReturn(itemAttackMode);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void calcDamageBonusNormalTest() {
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weapon"));
        final WeaponPrototype proto = new WeaponPrototype();
        proto.setDefaultAttackMode(new WeaponCategory());
        weapon.setInventoryItemPrototype(proto);
        final DndCharacter dndCharacter = new DndCharacter();

        final BonusValue result
                = this.service.calcDamageBonus(weapon, dndCharacter);

        assertEquals(3L, result.getValue());
    }

    @Test
    public void calcDamageBonusNullAttackModeFromContextTest() {
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weapon"));
        final WeaponPrototype proto = new WeaponPrototype();
        proto.setDefaultAttackMode(new WeaponCategory());
        weapon.setInventoryItemPrototype(proto);
        final DndCharacter dndCharacter = new DndCharacter();

        when(this.situationContextService.getItemAttackMode(
                (UniqueId) anyObject(),
                (DndCharacter) anyObject())).thenReturn(null);

        final BonusValue result
                = this.service.calcDamageBonus(weapon, dndCharacter);

        assertEquals(3L, result.getValue());
    }

}
