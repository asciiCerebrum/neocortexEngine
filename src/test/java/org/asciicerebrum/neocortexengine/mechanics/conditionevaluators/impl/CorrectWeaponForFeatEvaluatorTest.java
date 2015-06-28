package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorPrototype;
import org.asciicerebrum.neocortexengine.domain.ruleentities.CharacterClass;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ClassLevel;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Feat;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatBinding;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponPrototype;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.LevelAdvancements;
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
public class CorrectWeaponForFeatEvaluatorTest {

    private CorrectWeaponForFeatEvaluator evaluator;

    private FeatType featType;

    public CorrectWeaponForFeatEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new CorrectWeaponForFeatEvaluator();
        this.featType = new FeatType();
        this.featType.setUniqueId(new UniqueId("featType"));

        this.evaluator.setFeatType(this.featType);
    }

    @After
    public void tearDown() {
    }

    private void setupFull(final DndCharacter dndCharacter,
            final Weapon weapon) {
        final LevelAdvancement lvlAdv = new LevelAdvancement();
        final Feat feat = new Feat();
        final WeaponPrototype weaponProto = new WeaponPrototype();
        final LevelAdvancements lvlAdvs = new LevelAdvancements();
        feat.setFeatType(this.featType);
        feat.setFeatBinding(weaponProto);
        weapon.setInventoryItemPrototype(weaponProto);
        lvlAdv.getFeatAdvancements().addFeat(feat);
        lvlAdvs.add(lvlAdv);

        final ClassLevel clLvl = new ClassLevel();
        final CharacterClass chCl = new CharacterClass();
        clLvl.setCharacterClass(chCl);
        lvlAdv.setClassLevel(clLvl);

        dndCharacter.setLevelAdvancements(lvlAdvs);
    }

    @Test
    public void evaluateNormalTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weapon"));

        this.setupFull(dndCharacter, weapon);

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertTrue(result);
    }

    @Test
    public void evaluateNullWeaponTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final Weapon weapon = null;

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertFalse(result);
    }

    @Test
    public void evaluateNotAWeaponTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final Armor armor = new Armor();
        armor.setUniqueId(new UniqueId("armor"));

        final boolean result = this.evaluator.evaluate(dndCharacter, armor);

        assertFalse(result);
    }

    @Test
    public void evaluateEmptyFeatBindingsTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weapon"));

        this.setupFull(dndCharacter, weapon);
        dndCharacter.getLevelAdvancements().iterator().next()
                .getFeatAdvancements().iterator().next()
                .setFeatType(new FeatType());

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertFalse(result);
    }

    @Test
    public void evaluateWrongFeatBindingsTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weapon"));

        this.setupFull(dndCharacter, weapon);
        dndCharacter.getLevelAdvancements().iterator().next()
                .getFeatAdvancements().iterator().next()
                .setFeatBinding(new ArmorPrototype());

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertFalse(result);
    }

    @Test
    public void evaluateALLFeatBindingsTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weapon"));

        this.setupFull(dndCharacter, weapon);
        dndCharacter.getLevelAdvancements().iterator().next()
                .getFeatAdvancements().iterator().next()
                .setFeatBinding(FeatBinding.GenericBinding.ALL);

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertTrue(result);
    }

    @Test
    public void evaluateWrongPrototypeTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("character"));
        final Weapon weapon = new Weapon();
        weapon.setUniqueId(new UniqueId("weapon"));

        this.setupFull(dndCharacter, weapon);
        dndCharacter.getLevelAdvancements().iterator().next()
                .getFeatAdvancements().iterator().next()
                .setFeatBinding(new WeaponPrototype());

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertFalse(result);
    }

}
