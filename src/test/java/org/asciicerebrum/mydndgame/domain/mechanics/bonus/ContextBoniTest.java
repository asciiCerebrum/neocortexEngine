package org.asciicerebrum.mydndgame.domain.mechanics.bonus;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
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
}
