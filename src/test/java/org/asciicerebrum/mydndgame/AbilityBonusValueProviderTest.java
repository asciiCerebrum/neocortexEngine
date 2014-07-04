package org.asciicerebrum.mydndgame;

import java.util.HashMap;
import java.util.Map;
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

    private static final Long ABILITY_SCORE = 10L;

    @Mock
    private ICharacter dndCharacter;

    private AbilityBonusValueProvider abilityBonusValueProvider;
    private Ability ability;
    private Map<Ability, Long> abilityMap;

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
        this.abilityMap = new HashMap<Ability, Long>();
        this.abilityMap.put(this.ability, ABILITY_SCORE);

        when(this.dndCharacter.getAbilityMap()).thenReturn(this.abilityMap);

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
                this.dndCharacter);

        assertEquals(Long.valueOf(0), dynBonusValue);
    }

    /**
     * Test of getDynamicValue method, of class AbilityBonusValueProvider.
     * Negative bonus value.
     */
    @Test
    public void testGetDynamicValueNegative() {

        this.abilityMap.put(this.ability, 6L);
        Long dynBonusValue = this.abilityBonusValueProvider.getDynamicValue(
                this.dndCharacter);

        assertEquals(Long.valueOf(-2), dynBonusValue);
    }

    /**
     * Test of getDynamicValue method, of class AbilityBonusValueProvider.
     * Positive bonus value.
     */
    @Test
    public void testGetDynamicValuePositive() {

        this.abilityMap.put(this.ability, 14L);
        Long dynBonusValue = this.abilityBonusValueProvider.getDynamicValue(
                this.dndCharacter);

        assertEquals(Long.valueOf(2), dynBonusValue);
    }

}
