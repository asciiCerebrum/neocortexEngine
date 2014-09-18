package org.asciicerebrum.mydndgame.conditionevaluator;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmorCategory;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
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
public class CorrectArmorCategoryWearingEvaluatorTest {

    private CorrectArmorCategoryWearingEvaluator cacwEvaluator;
    private ISituationContext sitCon;
    private List<IArmor> wieldedArmor;

    public CorrectArmorCategoryWearingEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.cacwEvaluator = new CorrectArmorCategoryWearingEvaluator();
        this.sitCon = mock(ISituationContext.class);

        ICharacter character = mock(ICharacter.class);
        this.wieldedArmor = new ArrayList<IArmor>();
        IArmorCategory armorCategory = mock(IArmorCategory.class);

        IArmor armor1 = mock(IArmor.class);
        IArmor armor2 = mock(IArmor.class);

        when(armor1.getArmorCategory()).thenReturn(mock(IArmorCategory.class));
        when(armor2.getArmorCategory()).thenReturn(armorCategory);

        this.wieldedArmor.add(armor1);
        this.wieldedArmor.add(armor2);

        when(this.sitCon.getCharacter()).thenReturn(character);
        when(character.getWieldedArmor()).thenReturn(this.wieldedArmor);

        this.cacwEvaluator.setArmorCategory(armorCategory);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class CorrectArmorCategoryWearingEvaluator.
     */
    @Test
    public void testEvaluate() {
        Boolean evalValue = this.cacwEvaluator.evaluate(this.sitCon);

        assertTrue(evalValue);
    }

    @Test
    public void testEvaluateNullCategory() {
        this.cacwEvaluator.setArmorCategory(null);
        Boolean evalValue = this.cacwEvaluator.evaluate(this.sitCon);

        assertFalse(evalValue);
    }

    @Test
    public void testEvaluateArmorWithNullCategory() {
        when(this.wieldedArmor.get(1).getArmorCategory()).thenReturn(null);
        Boolean evalValue = this.cacwEvaluator.evaluate(this.sitCon);

        assertFalse(evalValue);
    }

}
