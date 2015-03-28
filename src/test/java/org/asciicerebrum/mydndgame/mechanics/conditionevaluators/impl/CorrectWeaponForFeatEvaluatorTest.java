package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.ArmorPrototype;
import org.asciicerebrum.mydndgame.domain.ruleentities.Feat;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatBinding;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatType;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponPrototype;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancements;
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
        lvlAdv.setFeatAdvancement(feat);
        lvlAdvs.add(lvlAdv);

        dndCharacter.setLevelAdvancements(lvlAdvs);
    }

    @Test
    public void evaluateNormalTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Weapon weapon = new Weapon();

        this.setupFull(dndCharacter, weapon);

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertTrue(result);
    }

    @Test
    public void evaluateNullWeaponTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Weapon weapon = null;

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertFalse(result);
    }

    @Test
    public void evaluateNotAWeaponTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Armor armor = new Armor();

        final boolean result = this.evaluator.evaluate(dndCharacter, armor);

        assertFalse(result);
    }

    @Test
    public void evaluateEmptyFeatBindingsTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Weapon weapon = new Weapon();

        this.setupFull(dndCharacter, weapon);
        dndCharacter.getLevelAdvancements().iterator().next()
                .getFeatAdvancement().setFeatType(new FeatType());

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertFalse(result);
    }

    @Test
    public void evaluateWrongFeatBindingsTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Weapon weapon = new Weapon();

        this.setupFull(dndCharacter, weapon);
        dndCharacter.getLevelAdvancements().iterator().next()
                .getFeatAdvancement().setFeatBinding(new ArmorPrototype());

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertFalse(result);
    }

    @Test
    public void evaluateALLFeatBindingsTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Weapon weapon = new Weapon();

        this.setupFull(dndCharacter, weapon);
        dndCharacter.getLevelAdvancements().iterator().next()
                .getFeatAdvancement().setFeatBinding(
                        FeatBinding.GenericBinding.ALL);

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertTrue(result);
    }

    @Test
    public void evaluateWrongPrototypeTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Weapon weapon = new Weapon();

        this.setupFull(dndCharacter, weapon);
        dndCharacter.getLevelAdvancements().iterator().next()
                .getFeatAdvancement().setFeatBinding(new WeaponPrototype());

        final boolean result = this.evaluator.evaluate(dndCharacter, weapon);

        assertFalse(result);
    }

}
