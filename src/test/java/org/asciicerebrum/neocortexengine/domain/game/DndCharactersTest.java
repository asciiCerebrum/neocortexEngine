package org.asciicerebrum.neocortexengine.domain.game;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class DndCharactersTest {

    private DndCharacters dndCharacters;

    public DndCharactersTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.dndCharacters = new DndCharacters();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hasMultipleEntriesTrueTest() {
        this.dndCharacters.addDndCharacter(new DndCharacter());
        this.dndCharacters.addDndCharacter(new DndCharacter());

        assertTrue(this.dndCharacters.hasMultipleEntries());
    }

    @Test
    public void hasMultipleEntriesFalseOneTest() {
        this.dndCharacters.addDndCharacter(new DndCharacter());

        assertFalse(this.dndCharacters.hasMultipleEntries());
    }

    @Test
    public void hasMultipleEntriesFalseZeroTest() {
        assertFalse(this.dndCharacters.hasMultipleEntries());
    }
}
