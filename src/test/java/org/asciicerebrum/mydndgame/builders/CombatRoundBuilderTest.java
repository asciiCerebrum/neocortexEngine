package org.asciicerebrum.mydndgame.builders;

import java.util.HashMap;
import java.util.Map;
import org.asciicerebrum.mydndgame.CombatRoundSetup;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class CombatRoundBuilderTest {

    private CombatRoundBuilder crBuilder;

    private CombatRoundSetup crSetup;

    private ApplicationContext context;

    private Map<String, String> positionMap;

    private ICharacter char2;

    public CombatRoundBuilderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.context = mock(ApplicationContext.class);
        this.crSetup = new CombatRoundSetup();
        this.crBuilder = new CombatRoundBuilder(this.crSetup, this.context);

        this.positionMap = new HashMap<String, String>();
        this.positionMap.put("char1", "pos1");
        this.positionMap.put("char2", "pos2");
        this.positionMap.put("char3", "pos3");

        this.crSetup.setCurrentPosition("pos2");
        this.crSetup.setParticipantIdPositionMap(this.positionMap);

        ICharacter char1 = mock(ICharacter.class);
        this.char2 = mock(ICharacter.class);
        ICharacter char3 = mock(ICharacter.class);

        when(this.context.getBean("char1", ICharacter.class)).thenReturn(char1);
        when(this.context.getBean("char2", ICharacter.class)).thenReturn(this.char2);
        when(this.context.getBean("char3", ICharacter.class)).thenReturn(char3);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of build method, of class CombatRoundBuilder.
     */
    @Test
    public void testBuildGroupSize() {
        ICombatRound combatRound = this.crBuilder.build();
        assertEquals(3, combatRound.getParticipants().size());
    }

    @Test
    public void testBuildPosition() {
        ICombatRound combatRound = this.crBuilder.build();
        assertEquals("pos2", combatRound.getCurrentDate()
                .getCombatRoundPosition());
    }

    @Test
    public void testBuildPositionCharacter() {
        ICombatRound combatRound = this.crBuilder.build();
        assertEquals(this.char2, combatRound.getCurrentParticipant());
    }

}
