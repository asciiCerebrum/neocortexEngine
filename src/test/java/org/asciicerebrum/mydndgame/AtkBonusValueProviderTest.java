package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.valueproviders.AtkBonusValueProvider;
import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ILevel;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Tabea Raab
 */
public class AtkBonusValueProviderTest {

    private AtkBonusValueProvider bonusValueProvider;

    @Mock
    private ICharacter characterMock;

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

        ISituationContext sitCon = mock(ISituationContext.class);

        when(this.characterMock.getSituationContext()).thenReturn(sitCon);
        when(characterMock.getClassLevels()).thenReturn(levels);
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

        Long dynVal = this.bonusValueProvider
                .getDynamicValue(this.characterMock);

        assertEquals(Long.valueOf(10), dynVal);
    }

}
