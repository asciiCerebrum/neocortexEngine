package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.valueproviders.AbilityBonusValueProvider;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author species8472
 */
public class AbilityBonusValueProviderTest {

    @Mock
    private ICharacter characterMock;

    private AbilityBonusValueProvider abilityBonusValueProvider;
    private Ability ability;

    public AbilityBonusValueProviderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        this.ability = new Ability();

        when(characterMock.getAbilityMod(this.ability)).thenReturn(2L);

        this.abilityBonusValueProvider = new AbilityBonusValueProvider();
        this.abilityBonusValueProvider.setAbility(this.ability);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDynamicValue method, of class AbilityBonusValueProvider.
     */
    @Test
    public void testGetDynamicValue() {

        Long dynBonusValue = this.abilityBonusValueProvider.getDynamicValue(
                this.characterMock);

        assertEquals(Long.valueOf(2), dynBonusValue);
    }

}
