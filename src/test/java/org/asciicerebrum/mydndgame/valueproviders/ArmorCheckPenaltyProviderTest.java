package org.asciicerebrum.mydndgame.valueproviders;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmorCategory;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class ArmorCheckPenaltyProviderTest {

    private ArmorCheckPenaltyProvider acpProvider;
    private ISituationContext sitCon;
    private ICharacter character;
    private List<IArmor> wieldedArmor;
    private IArmorCategory armorCategory;

    public ArmorCheckPenaltyProviderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.acpProvider = new ArmorCheckPenaltyProvider();
        this.sitCon = mock(ISituationContext.class);
        this.wieldedArmor = new ArrayList<IArmor>();
        this.armorCategory = mock(IArmorCategory.class);

        IArmor armor1 = mock(IArmor.class);
        IArmor armor2 = mock(IArmor.class);
        IArmor armor3 = mock(IArmor.class);

        when(armor1.getArmorCategory()).thenReturn(mock(IArmorCategory.class));
        when(armor2.getArmorCategory()).thenReturn(this.armorCategory);
        when(armor3.getArmorCategory()).thenReturn(this.armorCategory);

        when(armor2.getArmorCheckPenalty()).thenReturn(-42L);
        when(armor3.getArmorCheckPenalty()).thenReturn(-10L);

        this.wieldedArmor.add(armor1);
        this.wieldedArmor.add(armor2);
        this.wieldedArmor.add(armor3);

        this.character = mock(ICharacter.class);

        when(this.character.getSituationContext()).thenReturn(this.sitCon);
        when(this.character.getWieldedArmor()).thenReturn(this.wieldedArmor);

        this.acpProvider.setArmorCategory(this.armorCategory);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDynamicValue method, of class ArmorCheckPenaltyProvider.
     */
    @Test
    public void testGetDynamicValue() {
        Long dynVal = this.acpProvider.getDynamicValue(this.character);

        assertEquals(Long.valueOf(-42L), dynVal);
    }

    @Test
    public void testGetDynamicValueNullArmorCategory() {
        this.acpProvider.setArmorCategory(null);

        Long dynVal = this.acpProvider.getDynamicValue(this.character);

        assertEquals(Long.valueOf(0L), dynVal);
    }

    @Test
    public void testGetDynamicValueArmorWithNullCategory() {
        when(this.wieldedArmor.get(1).getArmorCategory()).thenReturn(null);

        Long dynVal = this.acpProvider.getDynamicValue(this.character);

        assertEquals(Long.valueOf(-10L), dynVal);
    }

    @Test
    public void testGetDynamicValueArmorWithNullCheckPenalty() {
        when(this.wieldedArmor.get(1).getArmorCheckPenalty()).thenReturn(null);

        Long dynVal = this.acpProvider.getDynamicValue(this.character);

        assertEquals(Long.valueOf(-10L), dynVal);
    }

}
