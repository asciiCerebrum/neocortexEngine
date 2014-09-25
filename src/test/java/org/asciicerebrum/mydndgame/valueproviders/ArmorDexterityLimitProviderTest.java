package org.asciicerebrum.mydndgame.valueproviders;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
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
public class ArmorDexterityLimitProviderTest {

    private ArmorDexterityLimitProvider adlProvider;

    private ISituationContext sitCon;

    private ICharacter character;

    private List<IArmor> wieldedArmor;

    public ArmorDexterityLimitProviderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.adlProvider = new ArmorDexterityLimitProvider();
        this.sitCon = mock(ISituationContext.class);

        this.character = mock(ICharacter.class);
        this.wieldedArmor = new ArrayList<IArmor>();

        IArmor armor1 = mock(IArmor.class);
        IArmor armor2 = mock(IArmor.class);
        IArmor armor3 = mock(IArmor.class);

        when(armor1.getMaxDexBonus()).thenReturn(4L);
        when(armor2.getMaxDexBonus()).thenReturn(2L);
        when(armor3.getMaxDexBonus()).thenReturn(3L);

        this.wieldedArmor.add(armor1);
        this.wieldedArmor.add(armor2);
        this.wieldedArmor.add(armor3);

        when(this.character.getSituationContext()).thenReturn(this.sitCon);
        when(this.character.getWieldedArmor()).thenReturn(wieldedArmor);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDynamicValue method, of class ArmorDexterityLimitProvider.
     */
    @Test
    public void testGetDynamicValue() {

        Long minMaxDex = this.adlProvider.getDynamicValue(this.character);

        assertEquals(Long.valueOf(2L), minMaxDex);
    }

    @Test
    public void testGetDynamicValueEmptyList() {
        this.wieldedArmor.clear();

        Long minMaxDex = this.adlProvider.getDynamicValue(this.character);

        assertNull(minMaxDex);
    }

    @Test
    public void testGetDynamicValueNullMaxDex() {
        when(this.wieldedArmor.get(1).getMaxDexBonus()).thenReturn(null);

        Long minMaxDex = this.adlProvider.getDynamicValue(this.character);

        assertEquals(Long.valueOf(3L), minMaxDex);
    }

}
