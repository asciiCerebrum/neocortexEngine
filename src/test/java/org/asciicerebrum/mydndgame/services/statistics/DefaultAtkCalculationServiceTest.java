package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.ruleentities.ClassLevel;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponPrototype;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancements;
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
public class DefaultAtkCalculationServiceTest {

    private DefaultAtkCalculationService service;

    private BonusCalculationService bonusService;

    private SituationContextService sitConService;

    private DiceAction attackAction;

    public DefaultAtkCalculationServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.service = new DefaultAtkCalculationService();
        this.bonusService = mock(BonusCalculationService.class);
        this.sitConService = mock(SituationContextService.class);
        this.attackAction = new DiceAction();

        this.service.setBonusService(this.bonusService);
        this.service.setSituationContextService(this.sitConService);
        this.service.setAttackAction(this.attackAction);

        final BonusValueTuple atkBonus
                = new BonusValueTuple(new BonusValue(3L));
        final WeaponCategory itemAttackMode
                = new WeaponCategory();

        when(this.bonusService.calculateBonusValues(
                (BonusSources) anyObject(),
                (BonusTargets) anyObject(),
                (Weapon) anyObject(),
                (ObserverSources) anyObject(),
                (ObserverHooks) anyObject(),
                (DndCharacter) anyObject())).thenReturn(atkBonus);
        when(this.sitConService.getItemAttackMode(
                (UniqueId) anyObject(),
                (DndCharacter) anyObject())).thenReturn(itemAttackMode);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void calcAtkBoniTest() {
        final Weapon weapon = new Weapon();
        final DndCharacter dndCharacter = new DndCharacter();
        final LevelAdvancements lvlAdvs = new LevelAdvancements();
        final LevelAdvancement lvlAdv = new LevelAdvancement();
        final ClassLevel clLvl = new ClassLevel();
        clLvl.setBaseAtkBoni(new BonusValueTuple(new BonusValue(2L)));
        lvlAdv.setClassLevel(clLvl);
        lvlAdvs.add(lvlAdv);
        dndCharacter.setLevelAdvancements(lvlAdvs);

        final BonusValueTuple result
                = this.service.calcAtkBoni(weapon, dndCharacter);

        assertEquals(5L, result.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

    @Test
    public void calcAtkBoniNullAttackModeFromContextTest() {
        final Weapon weapon = new Weapon();
        final DndCharacter dndCharacter = new DndCharacter();
        final LevelAdvancements lvlAdvs = new LevelAdvancements();
        final LevelAdvancement lvlAdv = new LevelAdvancement();
        final ClassLevel clLvl = new ClassLevel();
        clLvl.setBaseAtkBoni(new BonusValueTuple(new BonusValue(2L)));
        lvlAdv.setClassLevel(clLvl);
        lvlAdvs.add(lvlAdv);
        dndCharacter.setLevelAdvancements(lvlAdvs);

        when(this.sitConService.getItemAttackMode(
                (UniqueId) anyObject(),
                (DndCharacter) anyObject())).thenReturn(null);

        final WeaponPrototype weaponProto = new WeaponPrototype();
        weapon.setInventoryItemPrototype(weaponProto);
        final WeaponCategory defaultAttackMode = new WeaponCategory();
        weaponProto.setDefaultAttackMode(defaultAttackMode);

        final BonusValueTuple result
                = this.service.calcAtkBoni(weapon, dndCharacter);

        assertEquals(5L, result.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

}
