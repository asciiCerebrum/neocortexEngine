package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacterSetup;
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
public class AddBonusObserverTest {

    private AddBonusObserver abObserver;

    private List<IBonus> bonusList;

    private ISituationContext sitCon;

    private ICharacterSetup setup;

    private final String regKey = "someRegKey";

    public AddBonusObserverTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.abObserver = new AddBonusObserver();
        this.bonusList = new ArrayList<IBonus>();
        this.sitCon = new SituationContext();

        this.setup = new CharacterSetup();
        this.setup.getStateRegistry().put(this.regKey, 42L);

        ICharacter character = mock(ICharacter.class);
        when(character.getSetup()).thenReturn(this.setup);

        this.sitCon.setCharacter(character);
        this.abObserver.setRegistryKey(this.regKey);
        this.abObserver.setBonusTarget(mock(BonusTarget.class));
        this.abObserver.setBonusType(new BonusType());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of trigger method, of class AddBonusObserver.
     */
    @Test
    public void testTriggerSize() {
        List<IBonus> boni = (List<IBonus>) this.abObserver
                .trigger(this.bonusList, this.sitCon);

        assertEquals(1, boni.size());
    }

    @Test
    public void testTriggerValue() {
        List<IBonus> boni = (List<IBonus>) this.abObserver
                .trigger(this.bonusList, this.sitCon);

        assertEquals(Long.valueOf(42), boni.get(0).getValue());
    }

    @Test
    public void testTriggerNullValueSize() {
        this.setup.getStateRegistry().put(this.regKey, null);

        List<IBonus> boni = (List<IBonus>) this.abObserver
                .trigger(this.bonusList, this.sitCon);

        assertEquals(0, boni.size());
    }

    @Test
    public void testTriggerZeroValueSize() {
        this.setup.getStateRegistry().put(this.regKey, 0L);

        List<IBonus> boni = (List<IBonus>) this.abObserver
                .trigger(this.bonusList, this.sitCon);

        assertEquals(0, boni.size());
    }

}
