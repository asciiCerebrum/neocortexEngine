package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.AdvancementNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.ruleentities.CharacterClass;
import org.asciicerebrum.mydndgame.domain.ruleentities.ClassLevel;
import org.asciicerebrum.mydndgame.domain.ruleentities.Feat;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatType;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author species8472
 */
public class LevelAdvancementsTest {

    private LevelAdvancements levelAdvancements;

    private Feat refFeat;
    
    private Ability refAbility;

    private LevelAdvancement lvlAdvB;

    private UniqueEntityResolver resolver;
    
    private UniqueEntity context;

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
        
        this.refFeat = new Feat();
        final Feat otherFeat = new Feat();
        
        final Boni boni = new Boni();
        boni.addBonus(new Bonus());
        
        this.refFeat.setBoni(boni);
        otherFeat.setBoni(boni);
        
        final ClassLevel clLvl = new ClassLevel();
        final CharacterClass chCl = new CharacterClass();
        clLvl.setCharacterClass(chCl);        
        
        lvlAdvA.getFeatAdvancements().addFeat(otherFeat);
        lvlAdvA.setClassLevel(clLvl);
        this.lvlAdvB.getFeatAdvancements().addFeat(this.refFeat);
        this.lvlAdvB.setAbilityAdvancement(this.refAbility);
        this.lvlAdvB.setClassLevel(clLvl);
        lvlAdvC.getFeatAdvancements().addFeat(this.refFeat);
        lvlAdvC.setAbilityAdvancement(this.refAbility);

        lvlAdvA.setAdvNumber(AdvancementNumber.ADV_NO_0);
        this.lvlAdvB.setAdvNumber(AdvancementNumber.ADV_NO_1);
        lvlAdvC.setAdvNumber(AdvancementNumber.ADV_NO_2);
        lvlAdvC.setClassLevel(clLvl);

        this.levelAdvancements.add(lvlAdvA);
        this.levelAdvancements.add(this.lvlAdvB);
        this.levelAdvancements.add(lvlAdvC);

        this.resolver = mock(UniqueEntityResolver.class);
        this.context = new Weapon();
        this.context.setUniqueId(new UniqueId("context"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBoniTest() {
        assertEquals(3L, Iterators.size(this.levelAdvancements
                .getBoni(this.context, this.resolver).iterator()));
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
