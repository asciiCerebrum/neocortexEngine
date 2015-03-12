package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.particles.AdvancementNumber;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatType;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class LevelAdvancementsTest {

    private LevelAdvancements levelAdvancements;

    private Ability refAbility;

    private LevelAdvancement lvlAdvB;

    public LevelAdvancementsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.levelAdvancements = new LevelAdvancements();

        final LevelAdvancement lvlAdvA = new LevelAdvancement();
        this.lvlAdvB = new LevelAdvancement();
        final LevelAdvancement lvlAdvC = new LevelAdvancement();

        this.refAbility = new Ability();

        this.lvlAdvB.setAbilityAdvancement(this.refAbility);
        lvlAdvC.setAbilityAdvancement(this.refAbility);

        lvlAdvA.setAdvNumber(AdvancementNumber.ADV_NO_0);
        this.lvlAdvB.setAdvNumber(AdvancementNumber.ADV_NO_1);
        lvlAdvC.setAdvNumber(AdvancementNumber.ADV_NO_2);

        this.levelAdvancements.add(lvlAdvA);
        this.levelAdvancements.add(this.lvlAdvB);
        this.levelAdvancements.add(lvlAdvC);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBonusSourcesTest() {
        assertEquals(3L, Iterators.size(this.levelAdvancements
                .getBonusSources().iterator()));
    }

    @Test
    public void countAbilityWithReferenceTest() {
        assertEquals(2L, this.levelAdvancements.countAbility(this.refAbility)
                .getValue());
    }

    @Test
    public void countAbilityWithNewTest() {
        assertEquals(0L, this.levelAdvancements.countAbility(new Ability())
                .getValue());
    }

    @Test
    public void getLevelAdvancementByNumberTest() {
        assertEquals(this.lvlAdvB, this.levelAdvancements
                .getLevelAdvancementByNumber(AdvancementNumber.ADV_NO_1));
    }

    @Test
    public void getLevelAdvancementByNumberNullTest() {
        assertNull(this.levelAdvancements
                .getLevelAdvancementByNumber(AdvancementNumber.ADV_NO_3));
    }

    @Test
    public void getFeatBindingsByFeatTypeEmptyTest() {
        assertEquals(0L, Iterators.size(this.levelAdvancements
                .getFeatBindingsByFeatType(new FeatType()).iterator()));
    }

}
