package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ILevel;
import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueContext;
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
 * @author Tabea Raab
 */
public class AtkBonusValueProviderTest {

    private AtkBonusValueProvider bonusValueProvider;

    @Mock(extraInterfaces = {BonusValueContext.class})
    private ICharacter dndCharacter;
    
    @Mock
    private ILevel iLevel1;
    
    @Mock
    private ILevel iLevel2;

    public AtkBonusValueProviderTest() {
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
        
        List<ILevel> levels = new ArrayList<ILevel>();
        levels.add(this.iLevel1);
        levels.add(this.iLevel2);
        
        when(this.dndCharacter.getClassLevels()).thenReturn(levels);
        when(this.iLevel1.getBaseAtkBonusValueDeltaByRank(5L)).thenReturn(3L);
        when(this.iLevel2.getBaseAtkBonusValueDeltaByRank(5L)).thenReturn(7L);
        
        this.bonusValueProvider = new AtkBonusValueProvider();
        this.bonusValueProvider.setRank(5L);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDynamicValue method, of class AtkBonusValueProvider.
     */
    @Test
    public void testGetDynamicValue() {

        Long dynVal = this.bonusValueProvider.getDynamicValue(
                (BonusValueContext) this.dndCharacter);

        assertEquals(Long.valueOf(10), dynVal);
    }

}
