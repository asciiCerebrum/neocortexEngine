package org.asciicerebrum.mydndgame.domain.mechanics.bonus;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.Stackability;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusType;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class ContextBoniTest {

    private ContextBoni ctxBoni;

    private BonusTarget targetA;

    private ContextBonus ctxBonusA;

    private ContextBonus ctxBonusB;

    private UniqueEntity contextA;

    public ContextBoniTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.ctxBoni = new ContextBoni();

        this.targetA = new BonusTarget() {

            public ObserverHook getAssociatedHook() {
                return null;
            }

            public void setAssociatedHook(ObserverHook associatedHook) {
            }
        };

        this.contextA = new Weapon();
        this.contextA.setUniqueId(new UniqueId("contextA"));

        final Bonus bonusA = new Bonus();
        bonusA.setTarget(this.targetA);

        this.ctxBonusA = new ContextBonus(bonusA, this.contextA);

        final Bonus bonusB = new Bonus();
        bonusB.setTarget(new BonusTarget() {

            public ObserverHook getAssociatedHook() {
                return null;
            }

            public void setAssociatedHook(ObserverHook associatedHook) {
            }
        });
        final UniqueEntity contextB = new Weapon();
        contextB.setUniqueId(new UniqueId("contextB"));
        this.ctxBonusB = new ContextBonus(bonusB, contextB);

        this.ctxBoni.add(this.ctxBonusA);
        this.ctxBoni.add(this.ctxBonusB);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void filterByTargetSizeTest() {
        final ContextBoni result = this.ctxBoni.filterByTarget(this.targetA);

        assertEquals(1L, Iterators.size(result.iterator()));
    }

    @Test
    public void filterByTargetObjectTest() {
        final ContextBoni result = this.ctxBoni.filterByTarget(this.targetA);

        assertEquals(this.ctxBonusA, result.iterator().next());
    }

    @Test
    public void filterByScopeObjectTest() {
        final ContextBoni result = this.ctxBoni.filterByScope(this.contextA);

        assertEquals(this.ctxBonusA, result.iterator().next());
    }

    @Test
    public void filterByScopeObjectALLScopeTest() {
        final ContextBoni result = this.ctxBoni.filterByScope(new Weapon());

        assertEquals(2L, Iterators.size(result.iterator()));
    }

    @Test
    public void filterByScopeObjectSPECIFICScopeTest() {
        this.ctxBonusA.getBonus().setScope(Bonus.BonusScope.SPECIFIC);
        this.ctxBonusB.getBonus().setScope(Bonus.BonusScope.SPECIFIC);
        final ContextBoni result = this.ctxBoni.filterByScope(new Weapon());

        assertEquals(0L, Iterators.size(result.iterator()));
    }

    @Test
    public void filterByTargetsObjectTest() {
        final BonusTargets targets = new BonusTargets(this.targetA,
                new BonusTarget() {

                    public ObserverHook getAssociatedHook() {
                        return null;
                    }

                    public void setAssociatedHook(ObserverHook associatedHook) {
                    }
                });
        final ContextBoni result = this.ctxBoni.filterByTargets(targets);

        assertEquals(this.ctxBonusA, result.iterator().next());
    }

    @Test
    public void filterByStackabilitySameTypeStackableSizeTest() {
        final BonusType bonusType = new BonusType();
        bonusType.setDoesStack(new Stackability(true));
        this.ctxBonusA.getBonus().setBonusType(bonusType);
        this.ctxBonusB.getBonus().setBonusType(bonusType);

        final ContextBoni result = this.ctxBoni.filterByStackability();

        assertEquals(2L, Iterators.size(result.iterator()));
    }

    @Test
    public void filterByStackabilitySameTypeNotStackableSizeTest() {
        final BonusValueTuple bonusVal10
                = new BonusValueTuple(new BonusValue(10));
        final BonusValueTuple bonusVal12
                = new BonusValueTuple(new BonusValue(12));

        final BonusType bonusType = new BonusType();
        bonusType.setDoesStack(new Stackability(false));
        this.ctxBonusA.getBonus().setBonusType(bonusType);
        this.ctxBonusA.getBonus().setValues(bonusVal10);
        this.ctxBonusB.getBonus().setBonusType(bonusType);
        this.ctxBonusB.getBonus().setValues(bonusVal12);

        final ContextBoni result = this.ctxBoni.filterByStackability();

        assertEquals(1L, Iterators.size(result.iterator()));
    }

    @Test
    public void filterByStackabilitySameTypeNotStackableObjectTest() {
        final BonusValueTuple bonusVal10
                = new BonusValueTuple(new BonusValue(10));
        final BonusValueTuple bonusVal12
                = new BonusValueTuple(new BonusValue(12));

        final BonusType bonusType = new BonusType();
        bonusType.setDoesStack(new Stackability(false));
        this.ctxBonusA.getBonus().setBonusType(bonusType);
        this.ctxBonusA.getBonus().setValues(bonusVal10);
        this.ctxBonusB.getBonus().setBonusType(bonusType);
        this.ctxBonusB.getBonus().setValues(bonusVal12);

        final ContextBoni result = this.ctxBoni.filterByStackability();

        assertEquals(this.ctxBonusB, result.iterator().next());
    }

    @Test
    public void filterByStackabilityDifferentTypeSizeTest() {
        final BonusType bonusTypeA = new BonusType();
        final BonusType bonusTypeB = new BonusType();
        this.ctxBonusA.getBonus().setBonusType(bonusTypeA);
        this.ctxBonusB.getBonus().setBonusType(bonusTypeB);

        final ContextBoni result = this.ctxBoni.filterByStackability();

        assertEquals(2L, Iterators.size(result.iterator()));
    }

    @Test
    public void filterByStackabilitySameTypeNotStackableOtherObjectTest() {
        final BonusValueTuple bonusVal10
                = new BonusValueTuple(new BonusValue(12));
        final BonusValueTuple bonusVal12
                = new BonusValueTuple(new BonusValue(10));

        final BonusType bonusType = new BonusType();
        bonusType.setDoesStack(new Stackability(false));
        this.ctxBonusA.getBonus().setBonusType(bonusType);
        this.ctxBonusA.getBonus().setValues(bonusVal10);
        this.ctxBonusB.getBonus().setBonusType(bonusType);
        this.ctxBonusB.getBonus().setValues(bonusVal12);

        final ContextBoni result = this.ctxBoni.filterByStackability();

        assertEquals(this.ctxBonusA, result.iterator().next());
    }

}
