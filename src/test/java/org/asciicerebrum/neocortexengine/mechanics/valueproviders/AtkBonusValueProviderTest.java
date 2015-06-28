package org.asciicerebrum.neocortexengine.mechanics.valueproviders;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.services.statistics.AtkCalculationService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Tabea Raab
 */
public class AtkBonusValueProviderTest {

    private AtkBonusValueProvider provider;

    private AtkCalculationService atkCalculationService;

    public AtkBonusValueProviderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.provider = new AtkBonusValueProvider();
        this.atkCalculationService = mock(AtkCalculationService.class);

        this.provider.setAtkCalcService(this.atkCalculationService);
        this.provider.setRank(BonusRank.RANK_0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getDynamicValueCorrectTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();
        final BonusValue atkBonus = new BonusValue(10L);
        final BonusValueTuple atkTuple = new BonusValueTuple();
        atkTuple.addBonusValue(BonusRank.RANK_0, atkBonus);

        when(this.atkCalculationService.calcAtkBoni(
                (Weapon) contextItem, (DndCharacter) dndCharacter))
                .thenReturn(atkTuple);

        final BonusValue result = this.provider.getDynamicValue(
                dndCharacter, contextItem);
        assertEquals(atkBonus, result);
    }

    @Test
    public void getDynamicValueNoWeaponTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Armor();
        final BonusValue atkBonus = new BonusValue(10L);
        final BonusValueTuple atkTuple = new BonusValueTuple();
        atkTuple.addBonusValue(BonusRank.RANK_0, atkBonus);

        final BonusValue result = this.provider.getDynamicValue(
                dndCharacter, contextItem);
        assertNull(result);
    }
}
