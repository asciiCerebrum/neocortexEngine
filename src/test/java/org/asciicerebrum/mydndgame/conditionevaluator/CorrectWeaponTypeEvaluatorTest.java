package org.asciicerebrum.mydndgame.conditionevaluator;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponType;
import org.asciicerebrum.mydndgame.interfaces.entities.Slotable;
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
public class CorrectWeaponTypeEvaluatorTest {

    private CorrectWeaponTypeEvaluator cwtEvaluator;

    private IWeaponType weaponType;

    private ISituationContext sitCon;

    private Slotable bodySlotMock;

    private IWeapon weaponMock;

    public CorrectWeaponTypeEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        this.cwtEvaluator = new CorrectWeaponTypeEvaluator();

        this.weaponType = mock(IWeaponType.class);

        this.sitCon = mock(ISituationContext.class);
        ICharacter characterMock = mock(ICharacter.class);
        IBodySlotType bodySlotTypeMock = mock(IBodySlotType.class);
        this.bodySlotMock = mock(Slotable.class);
        this.weaponMock = mock(IWeapon.class);

        List<IWeaponType> weaponTypes = new ArrayList<IWeaponType>();
        weaponTypes.add(this.weaponType);

        when(this.sitCon.getBodySlotType()).thenReturn(bodySlotTypeMock);
        when(this.sitCon.getCharacter()).thenReturn(characterMock);
        when(characterMock.getBodySlotByType(bodySlotTypeMock))
                .thenReturn(this.bodySlotMock);
        when(this.bodySlotMock.getItem()).thenReturn(this.weaponMock);
        when(this.weaponMock.getWeaponTypes()).thenReturn(weaponTypes);

        this.cwtEvaluator.setWeaponType(this.weaponType);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class CorrectWeaponTypeEvaluator.
     */
    @Test
    public void testEvaluate() {
        Boolean evalResult = this.cwtEvaluator.evaluate(this.sitCon);

        assertTrue(evalResult);
    }

    @Test
    public void testEvaluateNoReferenceWeaponType() {
        this.cwtEvaluator.setWeaponType(null);
        Boolean evalResult = this.cwtEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateNoItemInSlot() {
        when(this.bodySlotMock.getItem()).thenReturn(null);
        Boolean evalResult = this.cwtEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateItemNotWeapon() {
        when(this.bodySlotMock.getItem()).thenReturn(mock(IInventoryItem.class));
        Boolean evalResult = this.cwtEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateWeaponNotContained() {
        List<IWeaponType> otherWeaponTypes = new ArrayList<IWeaponType>();
        otherWeaponTypes.add(mock(IWeaponType.class));
        when(this.weaponMock.getWeaponTypes()).thenReturn(otherWeaponTypes);
        Boolean evalResult = this.cwtEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

}
