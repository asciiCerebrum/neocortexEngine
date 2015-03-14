package org.asciicerebrum.mydndgame.domain.mechanics.bonus;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTargets;
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
public class BoniTest {

    private Boni boni;

    private Bonus bonusA;

    private Bonus bonusB;

    private BonusTarget targetA;

    public BoniTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.boni = new Boni();
        this.bonusA = new Bonus();
        this.bonusB = new Bonus();
        this.targetA = new BonusTarget() {

            public ObserverHook getAssociatedHook() {
                return null;
            }

            public void setAssociatedHook(ObserverHook associatedHook) {
            }
        };

        this.bonusA.setTarget(this.targetA);
        this.bonusA.setScope(Bonus.BonusScope.SPECIFIC);

        this.bonusB.setTarget(new BonusTarget() {

            public ObserverHook getAssociatedHook() {
                return null;
            }

            public void setAssociatedHook(ObserverHook associatedHook) {
            }
        });
        this.bonusB.setScope(Bonus.BonusScope.ALL);

        this.boni.addBonus(this.bonusA);
        this.boni.addBonus(this.bonusB);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void filterByTargetSizeTest() {
        final Boni result = this.boni.filterByTarget(this.targetA);

        assertEquals(1L, Iterators.size(result.iterator()));
    }

    @Test
    public void filterByTargetObjectTest() {
        final Boni result = this.boni.filterByTarget(this.targetA);

        assertEquals(this.bonusA, result.iterator().next());
    }

    @Test
    public void filterByScopeObjectTest() {
        final Boni result = this.boni.filterByScope(Bonus.BonusScope.SPECIFIC);

        assertEquals(this.bonusA, result.iterator().next());
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
        final Boni result = this.boni.filterByTargets(targets);

        assertEquals(this.bonusA, result.iterator().next());
    }

}
