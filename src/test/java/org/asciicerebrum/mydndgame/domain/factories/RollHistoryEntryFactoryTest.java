package org.asciicerebrum.mydndgame.domain.factories;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.events.RollHistoryEntry;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.RollHistoryEntrySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.WorldDateSetup;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author species8472
 */
public class RollHistoryEntryFactoryTest {

    private RollHistoryEntryFactory factory;

    private EntityFactory<WorldDate> worldDateFactory;

    public RollHistoryEntryFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new RollHistoryEntryFactory();
        this.worldDateFactory = mock(EntityFactory.class);

        this.factory.setWorldDateFactory(this.worldDateFactory);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final EntitySetup setup = new RollHistoryEntrySetup();

        this.factory.newEntity(setup);
    }

    private void makeComplete(RollHistoryEntrySetup setup) {
        final WorldDateSetup worldDateSetup = new WorldDateSetup();
        worldDateSetup.setCombatRoundNumber("0");
        worldDateSetup.setCombatRoundPosition("001");

        setup.setSourceCharacterId("sourceId");
        setup.setDiceNumber("1");
        setup.setDiceSides("20");
        setup.setTotalResult("17");
        setup.setDiceActionId("initiative");
        setup.setWorldDate(worldDateSetup);
    }

    @Test
    public void newEntityCompleteTest() {
        final RollHistoryEntrySetup setup = new RollHistoryEntrySetup();

        this.makeComplete(setup);

        final RollHistoryEntry rollHistoryEntry
                = this.factory.newEntity(setup);

        assertEquals(20L, rollHistoryEntry.getDiceSides().getValue());
    }

    @Test
    public void newEntityCompleteWithTargetIdsSizeTest() {
        final RollHistoryEntrySetup setup = new RollHistoryEntrySetup();

        this.makeComplete(setup);
        setup.addTargetCharacterId("target1Id");
        setup.addTargetCharacterId("target2Id");

        final RollHistoryEntry rollHistoryEntry
                = this.factory.newEntity(setup);

        assertEquals(2L, Iterators.size(rollHistoryEntry
                .getTargetDndCharacterIds().iterator()));
    }

    @Test
    public void newEntityCompleteWithTargetIdsNameTest() {
        final RollHistoryEntrySetup setup = new RollHistoryEntrySetup();

        this.makeComplete(setup);
        setup.addTargetCharacterId("target1Id");
        setup.addTargetCharacterId("target2Id");

        final RollHistoryEntry rollHistoryEntry
                = this.factory.newEntity(setup);

        assertEquals("target1Id", Iterators.get(rollHistoryEntry
                .getTargetDndCharacterIds().iterator(), 0).getValue());
    }

}
