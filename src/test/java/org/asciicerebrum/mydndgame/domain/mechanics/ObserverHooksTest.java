package org.asciicerebrum.mydndgame.domain.mechanics;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class ObserverHooksTest {

    private ObserverHooks hooks;

    public ObserverHooksTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.hooks = new ObserverHooks(ObserverHook.ABILITY, ObserverHook.AC);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void toStringTest() {
        final String result = this.hooks.toString();

        assertEquals("ABILITY AC", result);
    }

}
