package org.asciicerebrum.mydndgame;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
public class CharacterSetupTest {

    private CharacterSetup chSetup;

    private ApplicationContext appContext;

    public CharacterSetupTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.appContext = mock(ApplicationContext.class);
        when(this.appContext.getBean("someBeanId", Object.class))
                .thenReturn("someObject");

        this.chSetup = new CharacterSetup(this.appContext);
        this.chSetup.getStateRegistry().put("someKey", "someBeanId");
        this.chSetup.getStateRegistry().put("someNumericKey", 42L);
        this.chSetup.getStateRegistry().put("someBlankKey", null);
        this.chSetup.getStateRegistry().put("someEmptyKey", "");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getStateRegistryBeanForKey method, of class CharacterSetup.
     */
    @Test
    public void testGetStateRegistryBeanForKey() {

        Object bean = this.chSetup.getStateRegistryBeanForKey("someKey",
                Object.class);

        assertEquals("someObject", (String) bean);
    }

    @Test
    public void testGetStateRegistryBeanForKeyNotInMap() {

        Object bean = this.chSetup.getStateRegistryBeanForKey("someOtherKey",
                Object.class);

        assertNull(bean);
    }

    @Test
    public void testGetStateRegistryBeanForKeyNotABeanId() {

        Object bean = this.chSetup.getStateRegistryBeanForKey("someNumericKey",
                Object.class);

        assertNull(bean);
    }

    @Test
    public void testGetStateRegistryBeanForKeyBlankString() {

        Object bean = this.chSetup.getStateRegistryBeanForKey("someBlankKey",
                Object.class);

        assertNull(bean);
    }

    @Test
    public void testGetStateRegistryBeanForKeyEmptyString() {

        Object bean = this.chSetup.getStateRegistryBeanForKey("someEmptyKey",
                Object.class);

        assertNull(bean);
    }

    @Test
    public void testGetStateRegistryBeanForKeyWithOverly() {
        this.chSetup.fakeAttribute("someOverlayKey", "overlayValue");
        when(this.appContext.getBean("overlayValue", Object.class))
                .thenReturn("someOverlayObject");
        Object object = this.chSetup.getStateRegistryBeanForKey(
                "someOverlayKey", Object.class);

        assertEquals("someOverlayObject", (String) object);
    }

}
