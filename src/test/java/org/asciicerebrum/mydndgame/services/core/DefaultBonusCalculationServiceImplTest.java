package org.asciicerebrum.mydndgame.services.core;

import org.asciicerebrum.mydndgame.services.context.EntityPoolService;
import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBonus;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.DynamicValueProvider;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.ConditionEvaluator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class DefaultBonusCalculationServiceImplTest {

    private DefaultBonusCalculationServiceImpl bonusCalcService;

    private ObservableService observableService;

    private EntityPoolService entityPoolService;

    public DefaultBonusCalculationServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.bonusCalcService = new DefaultBonusCalculationServiceImpl();
        this.observableService = mock(ObservableService.class);
        this.entityPoolService = mock(EntityPoolService.class);

        this.bonusCalcService.setObservableService(this.observableService);
        this.bonusCalcService.setEntityPoolService(this.entityPoolService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void accumulateBoniSimpleTest() {
        final BonusSource bonusSource = mock(BonusSource.class);
        final UniqueEntity uniqueEnity = new DndCharacter();
        final Bonus bonus = new Bonus();
        final ContextBoni ctxBoni = new ContextBoni();
        final ContextBonus ctxBonus = new ContextBonus(bonus, uniqueEnity);
        ctxBoni.add(ctxBonus);
        when(bonusSource.getBoni((UniqueEntity) anyObject(),
                eq(this.entityPoolService))).thenReturn(ctxBoni);

        final ContextBoni testBoni = this.bonusCalcService.accumulateBoni(
                bonusSource, uniqueEnity);

        assertEquals(bonus, testBoni.iterator().next().getBonus());
    }

    @Test
    public void accumulateBoniComplexTest() {
        final BonusSource bonusSource = mock(BonusSource.class);
        final UniqueEntity uniqueEnity = new DndCharacter();
        final Boni boniA = new Boni();
        boniA.addBonus(new Bonus());
        final Boni boniB = new Boni();
        boniB.addBonus(new Bonus());
        boniB.addBonus(new Bonus());

        final ContextBoni ctxBoni = new ContextBoni();
        ctxBoni.add(boniA, uniqueEnity);
        ctxBoni.add(boniB, uniqueEnity);

        when(bonusSource.getBoni(uniqueEnity, this.entityPoolService))
                .thenReturn(ctxBoni);

        final ContextBoni testBoni = this.bonusCalcService.accumulateBoni(
                bonusSource, uniqueEnity);

        assertEquals(3L, Iterators.size(testBoni.iterator()));
    }

    @Test
    public void getEffectiveValuesEvaluatorFalseTest() {
        final Bonus bonus = new Bonus();
        final ConditionEvaluator conditionEvaluator
                = mock(ConditionEvaluator.class);
        bonus.setConditionEvaluator(conditionEvaluator);

        final UniqueEntity targetEntity = new Weapon();
        final DndCharacter dndCharacter = new DndCharacter();

        when(conditionEvaluator.evaluate(dndCharacter, targetEntity))
                .thenReturn(Boolean.FALSE);

        final ContextBonus ctxBonus = new ContextBonus(bonus, targetEntity);

        final BonusValueTuple testBonusValueTuple
                = this.bonusCalcService.getEffectiveValues(
                        ctxBonus, dndCharacter);

        assertNull(testBonusValueTuple);
    }

    @Test
    public void getEffectiveValuesEvaluatorTrueTest() {
        final Bonus bonus = new Bonus();
        final ConditionEvaluator conditionEvaluator
                = mock(ConditionEvaluator.class);
        bonus.setConditionEvaluator(conditionEvaluator);
        final BonusValueTuple tuple = new BonusValueTuple();
        bonus.setValues(tuple);

        final UniqueEntity targetEntity = new Weapon();
        final DndCharacter dndCharacter = new DndCharacter();

        when(conditionEvaluator.evaluate(dndCharacter, targetEntity))
                .thenReturn(Boolean.TRUE);

        final ContextBonus ctxBonus = new ContextBonus(bonus, targetEntity);

        final BonusValueTuple testBonusValueTuple
                = this.bonusCalcService.getEffectiveValues(
                        ctxBonus, dndCharacter);

        assertEquals(tuple, testBonusValueTuple);
    }

    @Test
    public void getEffectiveValuesWithValuesTest() {
        final Bonus bonus = new Bonus();
        final BonusValueTuple tuple = new BonusValueTuple();
        bonus.setValues(tuple);

        final UniqueEntity targetEntity = new Weapon();
        final DndCharacter dndCharacter = new DndCharacter();

        final ContextBonus ctxBonus = new ContextBonus(bonus, targetEntity);

        final BonusValueTuple testBonusValueTuple
                = this.bonusCalcService.getEffectiveValues(
                        ctxBonus, dndCharacter);

        assertEquals(tuple, testBonusValueTuple);
    }

    @Test
    public void getEffectiveValuesWithValueProviderTest() {
        final Bonus bonus = new Bonus();
        final DynamicValueProvider provider = mock(DynamicValueProvider.class);
        bonus.setDynamicValueProvider(provider);

        final UniqueEntity targetEntity = new Weapon();
        final DndCharacter dndCharacter = new DndCharacter();

        when(provider.getDynamicValue(dndCharacter, targetEntity))
                .thenReturn(new BonusValue(5L));

        final ContextBonus ctxBonus = new ContextBonus(bonus, targetEntity);

        final BonusValueTuple testBonusValueTuple
                = this.bonusCalcService.getEffectiveValues(
                        ctxBonus, dndCharacter);

        assertEquals(5L, testBonusValueTuple
                .getBonusValueByRank(BonusRank.RANK_0).getValue());
    }

    @Test
    public void getEffectiveValuesAllNullTest() {
        final Bonus bonus = new Bonus();
        final UniqueEntity targetEntity = new Weapon();
        final DndCharacter dndCharacter = new DndCharacter();

        final ContextBonus ctxBonus = new ContextBonus(bonus, targetEntity);

        final BonusValueTuple testBonusValueTuple
                = this.bonusCalcService.getEffectiveValues(
                        ctxBonus, dndCharacter);

        assertNull(testBonusValueTuple);
    }

    @Test
    public void accumulateBonusValuesTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final UniqueEntity targetEntity = new Weapon();
        final Boni foundBoni = new Boni();

        final Bonus bonusA = new Bonus();
        final BonusValueTuple valueA = new BonusValueTuple();
        valueA.addBonusValue(BonusRank.RANK_0, new BonusValue(1L));
        bonusA.setValues(valueA);

        final Bonus bonusB = new Bonus();
        final BonusValueTuple valueB = new BonusValueTuple();
        valueB.addBonusValue(BonusRank.RANK_0, new BonusValue(3L));
        bonusB.setValues(valueB);

        foundBoni.addBonus(bonusA);
        foundBoni.addBonus(bonusB);

        final ContextBoni ctxBoni = new ContextBoni(foundBoni, targetEntity);

        final BonusValueTuple testBonusValueTuple
                = this.bonusCalcService.accumulateBonusValues(
                        dndCharacter, ctxBoni);

        assertEquals(4L, testBonusValueTuple
                .getBonusValueByRank(BonusRank.RANK_0).getValue());
    }

}
