package org.asciicerebrum.neocortexengine.domain.setup;

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
public class LevelAdvancementSetupTest {

    private LevelAdvancementSetup setup;

    public LevelAdvancementSetupTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.setup = new LevelAdvancementSetup();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addFeatAdvancementTwiceTest() {
        final EntitySetup feat1 = new FeatSetup();
        final EntitySetup feat2 = new FeatSetup();

        this.setup.addFeatAdvancement(feat1);
        this.setup.addFeatAdvancement(feat2);

        assertEquals(2L, this.setup.getPropertySetups(
                SetupProperty.FEAT_ADVANCEMENTS).size());
    }

}
