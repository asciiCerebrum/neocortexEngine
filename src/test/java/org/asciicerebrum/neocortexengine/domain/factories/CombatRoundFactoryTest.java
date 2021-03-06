package org.asciicerebrum.neocortexengine.domain.factories;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.neocortexengine.domain.game.CombatRound;
import org.asciicerebrum.neocortexengine.domain.game.CombatRoundEntry;
import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import org.asciicerebrum.neocortexengine.domain.setup.CombatRoundEntrySetup;
import org.asciicerebrum.neocortexengine.domain.setup.CombatRoundSetup;
import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;
import org.asciicerebrum.neocortexengine.domain.setup.WorldDateSetup;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class CombatRoundFactoryTest {

    private CombatRoundFactory factory;

    private EntityFactory<CombatRoundEntry> combatRoundEntryFactory;

    private EntityFactory<WorldDate> worldDateFactory;

    public CombatRoundFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new CombatRoundFactory();
        this.combatRoundEntryFactory = mock(EntityFactory.class);
        this.worldDateFactory = mock(EntityFactory.class);

        this.factory.setCombatRoundEntryFactory(this.combatRoundEntryFactory);
        this.factory.setWorldDateFactory(this.worldDateFactory);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newEntityCompleteTest() {
        final CombatRoundSetup setup = new CombatRoundSetup();

        final CombatRound result = this.factory.newEntity(setup);

        assertNotNull(result);
    }

    @Test
    public void newEntityWithEntriesTest() {
        final CombatRoundSetup setup = new CombatRoundSetup();

        final List<EntitySetup> combatRoundEntries
                = new ArrayList<EntitySetup>();
        final EntitySetup entryA = new CombatRoundEntrySetup();
        combatRoundEntries.add(entryA);
        combatRoundEntries.add(entryA);
        setup.setCombatRoundEntries(combatRoundEntries);

        when(this.combatRoundEntryFactory.newEntity(entryA))
                .thenReturn(new CombatRoundEntry());

        this.factory.newEntity(setup);

        verify(this.combatRoundEntryFactory, times(2)).newEntity(entryA);
    }

    @Test
    public void newEntityWithDateTest() {
        final CombatRoundSetup setup = new CombatRoundSetup();

        final EntitySetup dateSetup = new WorldDateSetup();
        setup.setCurrentDate(dateSetup);

        this.factory.newEntity(setup);

        verify(this.worldDateFactory, times(1)).newEntity(dateSetup);
    }

}
