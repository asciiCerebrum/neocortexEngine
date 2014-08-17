package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class InventoryItemTest {

    private IInventoryItem inventoryItem;

    private IInventoryItem refItem;

    public InventoryItemTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.inventoryItem = new InventoryItem() {
        };

        this.inventoryItem.setName("someName");

        this.refItem = mock(IInventoryItem.class);
        when(this.refItem.getName()).thenReturn("someName");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of resembles method, of class InventoryItem.
     */
    @Test
    public void testResembles() {
        Boolean resembles = this.inventoryItem.resembles(this.refItem);

        assertTrue(resembles);
    }

    @Test
    public void testResemblesFalse() {
        when(this.refItem.getName()).thenReturn("someOtherName");

        Boolean resembles = this.inventoryItem.resembles(this.refItem);

        assertFalse(resembles);
    }

    @Test
    public void testResemblesNullItem() {
        Boolean resembles = this.inventoryItem.resembles(null);

        assertFalse(resembles);
    }

}
