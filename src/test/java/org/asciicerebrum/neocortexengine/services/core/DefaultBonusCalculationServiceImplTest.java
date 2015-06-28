package org.asciicerebrum.neocortexengine.services.core;

import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;
import com.google.common.collect.Iterators;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.core.particles.Stackability;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusType;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.Boni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBonus;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.DynamicValueProvider;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
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

    private ContextBoni prepareCtxBoni() {
        final ContextBoni ctxBoni = new ContextBoni();

        final Bonus bonusA = new Bonus();
        final UniqueEntity contextA = new Weapon();
        contextA.setUniqueId(new UniqueId("contextA"));
        final ContextBonus ctxBonusA = new ContextBonus(bonusA, contextA);

        final Bonus bonusB = new Bonus();
        final UniqueEntity contextB = new Weapon();
        contextB.setUniqueId(new UniqueId("contextB"));
        final ContextBonus ctxBonusB = new ContextBonus(bonusB, contextB);

        ctxBoni.add(ctxBonusA);
        ctxBoni.add(ctxBonusB);

        return ctxBoni;
    }

    @Test
    public void filterByStackabilitySameTypeStackableSizeTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final ContextBoni ctxBoni = this.prepareCtxBoni();

        final BonusType bonusType = new BonusType();
        bonusType.setDoesStack(new Stackability(true));
        Iterators.get(ctxBoni.iterator(), 0).getBonus().setBonusType(bonusType);
        Iterators.get(ctxBoni.iterator(), 1).getBonus().setBonusType(bonusType);

        final ContextBoni result = this.bonusCalcService
                .filterContextBoniByStackability(ctxBoni, dndCharacter);

        assertEquals(2L, Iterators.size(result.iterator()));
    }

    @Test
    public void filterByStackabilitySameTypeNotStackableSizeTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final ContextBoni ctxBoni = this.prepareCtxBoni();

        final BonusValueTuple bonusVal10
                = new BonusValueTuple(new BonusValue(10));
        final BonusValueTuple bonusVal12
                = new BonusValueTuple(new BonusValue(12));

        final BonusType bonusType = new BonusType();
        bonusType.setDoesStack(new Stackability(false));
        Iterators.get(ctxBoni.iterator(), 0).getBonus().setBonusType(bonusType);
        Iterators.get(ctxBoni.iterator(), 0).getBonus().setValues(bonusVal10);
        Iterators.get(ctxBoni.iterator(), 1).getBonus().setBonusType(bonusType);
        Iterators.get(ctxBoni.iterator(), 1).getBonus().setValues(bonusVal12);

        final ContextBoni result = this.bonusCalcService
                .filterContextBoniByStackability(ctxBoni, dndCharacter);

        assertEquals(1L, Iterators.size(result.iterator()));
    }

    @Test
    public void filterByStackabilitySameTypeNotStackableObjectTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final ContextBoni ctxBoni = this.prepareCtxBoni();

        final BonusValueTuple bonusVal10
                = new BonusValueTuple(new BonusValue(10));
        final BonusValueTuple bonusVal12
                = new BonusValueTuple(new BonusValue(12));

        final BonusType bonusType = new BonusType();
        bonusType.setDoesStack(new Stackability(false));
        Iterators.get(ctxBoni.iterator(), 0).getBonus().setBonusType(bonusType);
        Iterators.get(ctxBoni.iterator(), 0).getBonus().setValues(bonusVal10);
        Iterators.get(ctxBoni.iterator(), 1).getBonus().setBonusType(bonusType);
        Iterators.get(ctxBoni.iterator(), 1).getBonus().setValues(bonusVal12);

        final ContextBoni result = this.bonusCalcService
                .filterContextBoniByStackability(ctxBoni, dndCharacter);

        assertEquals(Iterators.get(ctxBoni.iterator(), 1),
                result.iterator().next());
    }

    @Test
    public void filterByStackabilityDifferentTypeSizeTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final ContextBoni ctxBoni = this.prepareCtxBoni();

        final BonusType bonusTypeA = new BonusType();
        final BonusType bonusTypeB = new BonusType();
        Iterators.get(ctxBoni.iterator(), 0).getBonus()
                .setBonusType(bonusTypeA);
        Iterators.get(ctxBoni.iterator(), 1).getBonus()
                .setBonusType(bonusTypeB);

        final ContextBoni result = this.bonusCalcService
                .filterContextBoniByStackability(ctxBoni, dndCharacter);

        assertEquals(2L, Iterators.size(result.iterator()));
    }

    @Test
    public void filterByStackabilitySameTypeNotStackableOtherObjectTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final ContextBoni ctxBoni = this.prepareCtxBoni();

        final BonusValueTuple bonusVal10
                = new BonusValueTuple(new BonusValue(12));
        final BonusValueTuple bonusVal12
                = new BonusValueTuple(new BonusValue(10));

        final BonusType bonusType = new BonusType();
        bonusType.setDoesStack(new Stackability(false));
        Iterators.get(ctxBoni.iterator(), 0).getBonus().setBonusType(bonusType);
        Iterators.get(ctxBoni.iterator(), 0).getBonus().setValues(bonusVal10);
        Iterators.get(ctxBoni.iterator(), 1).getBonus().setBonusType(bonusType);
        Iterators.get(ctxBoni.iterator(), 1).getBonus().setValues(bonusVal12);

        final ContextBoni result = this.bonusCalcService
                .filterContextBoniByStackability(ctxBoni, dndCharacter);

        assertEquals(Iterators.get(ctxBoni.iterator(), 0),
                result.iterator().next());
    }

}
