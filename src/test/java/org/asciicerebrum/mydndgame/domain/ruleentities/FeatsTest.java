package org.asciicerebrum.mydndgame.domain.ruleentities;

import com.google.common.collect.Iterators;
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
public class FeatsTest {

    private Feats feats;

    public FeatsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.feats = new Feats();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBonusSourcesEmpty() {
        assertEquals(0L, Iterators.size(this.feats
                .getBonusSources().iterator()));
    }

    @Test
    public void getBonusSourcesNonEmpty() {
        this.feats.addFeat(new Feat());
        this.feats.addFeat(new Feat());
        this.feats.addFeat(new Feat());

        assertEquals(3L, Iterators.size(this.feats
                .getBonusSources().iterator()));
    }

}
