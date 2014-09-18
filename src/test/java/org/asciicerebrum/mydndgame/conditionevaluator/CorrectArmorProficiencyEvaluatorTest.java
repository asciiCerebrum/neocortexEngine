package org.asciicerebrum.mydndgame.conditionevaluator;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IProficiency;
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
public class CorrectArmorProficiencyEvaluatorTest {

    private CorrectArmorProficiencyEvaluator capEvaluator;

    private ISituationContext sitCon;

    private IProficiency proficiency;

    private List<IArmor> wieldedArmor;

    public CorrectArmorProficiencyEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.capEvaluator = new CorrectArmorProficiencyEvaluator();
        this.sitCon = mock(ISituationContext.class);
        this.proficiency = mock(IProficiency.class);

        ICharacter character = mock(ICharacter.class);
        this.wieldedArmor = new ArrayList<IArmor>();

        IArmor armor1 = mock(IArmor.class);
        IArmor armor2 = mock(IArmor.class);

        when(armor1.getProficiency()).thenReturn(mock(IProficiency.class));
        when(armor2.getProficiency()).thenReturn(this.proficiency);

        this.wieldedArmor.add(armor1);
        this.wieldedArmor.add(armor2);

        when(this.sitCon.getCharacter()).thenReturn(character);
        when(character.getWieldedArmor()).thenReturn(this.wieldedArmor);

        this.capEvaluator.setProficiency(this.proficiency);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class CorrectArmorProficiencyEvaluator.
     */
    @Test
    public void testEvaluate() {
        Boolean evalResult = this.capEvaluator.evaluate(this.sitCon);

        assertTrue(evalResult);
    }

    @Test
    public void testEvaluateEmptyList() {
        this.wieldedArmor.clear();

        Boolean evalResult = this.capEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateNullProficiency() {
        this.capEvaluator.setProficiency(null);

        Boolean evalResult = this.capEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateNoMatch() {
        when(this.wieldedArmor.get(1).getProficiency())
                .thenReturn(mock(IProficiency.class));

        Boolean evalResult = this.capEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateNoMatchNullProficiency() {
        when(this.wieldedArmor.get(1).getProficiency())
                .thenReturn(null);

        Boolean evalResult = this.capEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

}
