package org.asciicerebrum.mydndgame.parametersetters;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.conditionevaluator.AndListEvaluator;
import org.asciicerebrum.mydndgame.conditionevaluator.CorrectWeaponEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IFeat;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.observers.RemoveBonusObserver;
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
 * @author species8472
 */
public class WeaponForProficiencyFeatSetterTest {

    private WeaponForProficiencyFeatSetter wfpfSetter;

    private IFeat mockFeat;

    private IWeapon mockWeapon;

    private CorrectWeaponEvaluator cwEval;

    private List<IObserver> observers;

    private RemoveBonusObserver rbObserver;

    private AndListEvaluator andEval;

    public WeaponForProficiencyFeatSetterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.wfpfSetter = new WeaponForProficiencyFeatSetter();
        this.mockFeat = mock(IFeat.class);
        this.mockWeapon = mock(IWeapon.class);

        this.observers = new ArrayList<IObserver>();

        when(this.mockFeat.getObservers()).thenReturn(this.observers);

        this.rbObserver = new RemoveBonusObserver();

        this.observers.add(mock(IObserver.class));
        this.observers.add(this.rbObserver);

        this.andEval = new AndListEvaluator();

        this.rbObserver.setConditionEvaluator(this.andEval);

        this.cwEval = new CorrectWeaponEvaluator();
        this.cwEval.setWeapon(null);

        this.andEval.setConditionEvaluators(
                new ArrayList<ConditionEvaluator>() {
                    {
                        this.add(mock(ConditionEvaluator.class));
                        this.add(cwEval);
                    }
                });
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setFeatParameter method, of class WeaponForProficiencyFeatSetter.
     */
    @Test
    public void testSetFeatParameter() {
        this.cwEval.setWeapon(null);
        this.wfpfSetter.setFeatParameter(this.mockFeat, this.mockWeapon);

        assertEquals(this.mockWeapon, this.cwEval.getWeapon());
    }

    /**
     * Test of setFeatParameter method, of class WeaponForProficiencyFeatSetter.
     * Null observer.
     */
    @Test
    public void testSetFeatParameterNullObserver() {
        this.observers.clear();
        this.observers.add(null);

        this.cwEval.setWeapon(null);
        this.wfpfSetter.setFeatParameter(this.mockFeat, this.mockWeapon);

        assertNull(this.cwEval.getWeapon());
    }

    /**
     * Test of setFeatParameter method, of class WeaponForProficiencyFeatSetter.
     * Null evaluator.
     */
    @Test
    public void testSetFeatParameterNullEvaluator() {
        this.rbObserver.setConditionEvaluator(null);

        this.cwEval.setWeapon(null);
        this.wfpfSetter.setFeatParameter(this.mockFeat, this.mockWeapon);

        assertNull(this.cwEval.getWeapon());
    }

    /**
     * Test of setFeatParameter method, of class WeaponForProficiencyFeatSetter.
     * Empty evaluator.
     */
    @Test
    public void testSetFeatParameterEmptyEvaluator() {
        this.andEval.setConditionEvaluators(
                new ArrayList<ConditionEvaluator>());

        this.cwEval.setWeapon(null);
        this.wfpfSetter.setFeatParameter(this.mockFeat, this.mockWeapon);

        assertNull(this.cwEval.getWeapon());
    }

}
