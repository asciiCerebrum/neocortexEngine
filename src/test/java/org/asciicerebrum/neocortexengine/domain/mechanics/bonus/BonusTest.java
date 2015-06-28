package org.asciicerebrum.neocortexengine.domain.mechanics.bonus;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.core.particles.LongParticle;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusTarget;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusType;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.Bonus.ResemblanceFacet;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class BonusTest {

    private Bonus bonus;

    public BonusTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.bonus = new Bonus();

        final BonusValueTuple values = new BonusValueTuple();
        values.addBonusValue(BonusRank.RANK_2, new BonusValue(13L));

        this.bonus.setValues(values);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void resemblesNullFacetsTest() {
        final Bonus testBonus = new Bonus();
        final BonusValueTuple testValues = new BonusValueTuple();
        testValues.addBonusValue(BonusRank.RANK_2, new BonusValue(13L));
        testBonus.setValues(testValues);

        final boolean result = this.bonus.resembles(testBonus,
                (ResemblanceFacet[]) null);

        assertTrue(result);
    }

    @Test
    public void resemblesEmptyFacetsTest() {
        final Bonus testBonus = new Bonus();
        final BonusValueTuple testValues = new BonusValueTuple();
        testValues.addBonusValue(BonusRank.RANK_2, new BonusValue(13L));
        testBonus.setValues(testValues);

        final boolean result = this.bonus.resembles(testBonus,
                new ResemblanceFacet[]{});

        assertTrue(result);
    }

    @Test
    public void resemblesValueFalseTest() {
        final Bonus testBonus = new Bonus();
        final BonusValueTuple testValues = new BonusValueTuple();
        testValues.addBonusValue(BonusRank.RANK_1, new BonusValue(13L));
        testBonus.setValues(testValues);

        final boolean result = this.bonus.resembles(testBonus,
                new ResemblanceFacet[]{ResemblanceFacet.VALUE});

        assertFalse(result);
    }

    @Test
    public void resemblesBonusTypeFalseTest() {
        final Bonus testBonus = new Bonus();
        testBonus.setBonusType(new BonusType());

        this.bonus.setBonusType(new BonusType());

        final boolean result = this.bonus.resembles(testBonus,
                new ResemblanceFacet[]{ResemblanceFacet.BONUS_TYPE});

        assertFalse(result);
    }

    @Test
    public void resemblesBonusTypeTrueTest() {
        final Bonus testBonus = new Bonus();
        testBonus.setBonusType(new BonusType());

        this.bonus.setBonusType(testBonus.getBonusType());

        final boolean result = this.bonus.resembles(testBonus,
                new ResemblanceFacet[]{ResemblanceFacet.BONUS_TYPE});

        assertTrue(result);
    }

    @Test
    public void resemblesBonusTargetFalseTest() {
        final Bonus testBonus = new Bonus();
        testBonus.setTarget(new BonusTarget() {

            public ObserverHook getAssociatedHook() {
                return null;
            }

            public void setAssociatedHook(ObserverHook associatedHook) {
            }
        });

        this.bonus.setTarget(new BonusTarget() {

            public ObserverHook getAssociatedHook() {
                return null;
            }

            public void setAssociatedHook(ObserverHook associatedHook) {
            }
        });

        final boolean result = this.bonus.resembles(testBonus,
                new ResemblanceFacet[]{ResemblanceFacet.TARGET});

        assertFalse(result);
    }

    @Test
    public void resemblesValueProviderFalseTest() {
        final Bonus testBonus = new Bonus();
        testBonus.setDynamicValueProvider(new DynamicValueProvider() {

            public LongParticle getDynamicValue(ICharacter dndCharacter,
                    UniqueEntity contextItem) {
                return null;
            }
        });

        this.bonus.setDynamicValueProvider(new DynamicValueProvider() {

            public LongParticle getDynamicValue(ICharacter dndCharacter,
                    UniqueEntity contextItem) {
                return null;
            }
        });

        final boolean result = this.bonus.resembles(testBonus,
                new ResemblanceFacet[]{ResemblanceFacet.DYNAMIC_VALUE_PROVIDER});

        assertFalse(result);
    }

}
