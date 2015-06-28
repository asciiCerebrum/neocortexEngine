package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.CharacterClass;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ClassLevel;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Feat;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatBinding;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Proficiency;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.LevelAdvancements;
import org.asciicerebrum.neocortexengine.facades.game.WeaponServiceFacade;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class CorrectProficiencyForFeatEvaluatorTest {

    private CorrectProficiencyForFeatEvaluator evaluator;

    private FeatType featType;

    private WeaponServiceFacade weaponServiceFacade;

    public CorrectProficiencyForFeatEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new CorrectProficiencyForFeatEvaluator();
        this.featType = new FeatType();
        this.weaponServiceFacade = mock(WeaponServiceFacade.class);

        this.evaluator.setFeatType(this.featType);
        this.evaluator.setWeaponServiceFacade(this.weaponServiceFacade);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateNullFeatTypeTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        this.evaluator.setFeatType(null);
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateNoWeaponTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Armor();

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateNormalTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Feat featA = new Feat();
        final FeatBinding featBinding = new Proficiency();
        featA.setFeatBinding(featBinding);
        featA.setFeatType(this.featType);
        final Feat featB = new Feat();
        featB.setFeatBinding(mock(FeatBinding.class));
        featB.setFeatType(this.featType);
        final LevelAdvancement lvlAdvA = new LevelAdvancement();
        lvlAdvA.getFeatAdvancements().addFeat(featA);
        final LevelAdvancement lvlAdvB = new LevelAdvancement();
        lvlAdvB.getFeatAdvancements().addFeat(featB);
        final LevelAdvancements lvlAdvancements = new LevelAdvancements();
        lvlAdvancements.add(lvlAdvA);
        lvlAdvancements.add(lvlAdvB);
        dndCharacter.setLevelAdvancements(lvlAdvancements);

        final ClassLevel clLvl = new ClassLevel();
        final CharacterClass chCl = new CharacterClass();
        clLvl.setCharacterClass(chCl);
        lvlAdvA.setClassLevel(clLvl);
        lvlAdvB.setClassLevel(clLvl);

        final UniqueEntity contextItem = new Weapon();

        when(this.weaponServiceFacade.hasProficiency((Proficiency) featBinding,
                (Weapon) contextItem, dndCharacter)).thenReturn(Boolean.TRUE);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }

    @Test
    public void evaluateNotNormalTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Feat featA = new Feat();
        final FeatBinding featBinding = new Proficiency();
        featA.setFeatBinding(featBinding);
        featA.setFeatType(this.featType);
        final Feat featB = new Feat();
        featB.setFeatBinding(mock(FeatBinding.class));
        featB.setFeatType(this.featType);
        final LevelAdvancement lvlAdvA = new LevelAdvancement();
        lvlAdvA.getFeatAdvancements().addFeat(featA);
        final LevelAdvancement lvlAdvB = new LevelAdvancement();
        lvlAdvB.getFeatAdvancements().addFeat(featB);
        final LevelAdvancements lvlAdvancements = new LevelAdvancements();
        lvlAdvancements.add(lvlAdvA);
        lvlAdvancements.add(lvlAdvB);
        dndCharacter.setLevelAdvancements(lvlAdvancements);

        final ClassLevel clLvl = new ClassLevel();
        final CharacterClass chCl = new CharacterClass();
        clLvl.setCharacterClass(chCl);
        lvlAdvA.setClassLevel(clLvl);
        lvlAdvB.setClassLevel(clLvl);

        final UniqueEntity contextItem = new Weapon();

        when(this.weaponServiceFacade.hasProficiency((Proficiency) featBinding,
                (Weapon) contextItem, dndCharacter)).thenReturn(Boolean.FALSE);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }
}
