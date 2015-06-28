package org.asciicerebrum.neocortexengine.domain.game;

import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorCategory;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorPrototype;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Proficiency;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class ArmorsTest {

    private Armors armors;

    public ArmorsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.armors = new Armors();

        final Armor armorA = new Armor();
        armorA.setInventoryItemPrototype(new ArmorPrototype());
        armorA.getArmorPrototype().setArmorCategory(new ArmorCategory());
        armorA.getArmorPrototype().setProficiency(new Proficiency());
        final Armor armorB = new Armor();
        armorB.setInventoryItemPrototype(new ArmorPrototype());
        armorB.getArmorPrototype().setArmorCategory(new ArmorCategory());
        armorB.getArmorPrototype().setProficiency(new Proficiency());

        this.armors.add(armorA);
        this.armors.add(armorB);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void containsArmorCategoryFailTest() {
        assertFalse(this.armors.containsArmorCategory(new ArmorCategory()));
    }

    @Test
    public void containsProficiencyFailTest() {
        assertFalse(this.armors.containsProficiency(new Proficiency()));
    }

}
