package org.asciicerebrum.mydndgame.domain.ruleentities;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
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
public class FeatsTest {

    private Feats feats;

    private UniqueEntityResolver resolver;

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
        this.resolver = mock(UniqueEntityResolver.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBonusSourcesEmpty() {
        assertEquals(0L, Iterators.size(this.feats
                .getBonusSources(this.resolver).iterator()));
    }

    @Test
    public void getBonusSourcesNonEmpty() {
        this.feats.addFeat(new Feat());
        this.feats.addFeat(new Feat());
        this.feats.addFeat(new Feat());

        assertEquals(3L, Iterators.size(this.feats
                .getBonusSources(this.resolver).iterator()));
    }

}
