package org.asciicerebrum.mydndgame.mechanics.observertriggers;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.DoubleParticle;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusType;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBonus;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;
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
public class BonusValueModificationObserverTriggerTest {

    private BonusValueModificationObserverTrigger trigger;

    private BonusCalculationService bonusCalcService;

    private DoubleParticle modValue;

    private Bonus referenceBonus;

    private BonusType bonusType;

    private BonusTarget bonusTarget;

    public BonusValueModificationObserverTriggerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.trigger = new BonusValueModificationObserverTrigger();
        this.bonusCalcService = mock(BonusCalculationService.class);
        this.modValue = new DoubleParticle(2D);
        this.referenceBonus = new Bonus();
        this.bonusType = new BonusType();
        this.bonusTarget = mock(BonusTarget.class);
        this.referenceBonus.setBonusType(this.bonusType);
        this.referenceBonus.setTarget(this.bonusTarget);

        this.trigger.setBonusService(this.bonusCalcService);
        this.trigger.setModValue(this.modValue);
        this.trigger.setOperation(DoubleParticle.Operation.MULTIPLICATION);
        this.trigger.setReferenceBonus(this.referenceBonus);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void triggerNoRefBonusTest() {
        final ContextBoni ctxBoni = new ContextBoni();
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        this.trigger.setReferenceBonus(null);
        Object testResult = this.trigger.trigger(
                ctxBoni, dndCharacter, contextItem);
        assertEquals(ctxBoni, testResult);
    }

    @Test
    public void triggerNormalOperationTest() {
        final ContextBoni boni = new ContextBoni();
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();
        final Bonus bonusA = new Bonus();
        final Bonus bonusB = new Bonus();
        bonusA.setBonusType(new BonusType());
        bonusA.setTarget(mock(BonusTarget.class));
        bonusB.setBonusType(this.bonusType);
        bonusB.setTarget(this.bonusTarget);
        final ContextBonus ctxBonusA = new ContextBonus(bonusA, contextItem);
        final ContextBonus ctxBonusB = new ContextBonus(bonusB, contextItem);
        boni.add(ctxBonusA);
        boni.add(ctxBonusB);

        final BonusValueTuple valueB = new BonusValueTuple();
        valueB.addBonusValue(BonusRank.RANK_0, new BonusValue(2L));
        when(this.bonusCalcService.getEffectiveValues(ctxBonusB,
                (DndCharacter) dndCharacter)).thenReturn(valueB);

        this.trigger.trigger(
                boni, dndCharacter, contextItem);
        assertEquals(4L, bonusB.getValues()
                .getBonusValueByRank(BonusRank.RANK_0).getValue());
    }

}
