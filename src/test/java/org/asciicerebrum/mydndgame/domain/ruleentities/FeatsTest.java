package org.asciicerebrum.mydndgame.domain.ruleentities;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
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

    private UniqueEntity context;

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
        this.context = new Weapon();
        this.context.setUniqueId(new UniqueId("weapon"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBoniEmpty() {
        assertEquals(0L, Iterators.size(this.feats
                .getBoni(this.context, this.resolver).iterator()));
    }

    @Test
    public void getBoniNonEmpty() {
        final Feat feat = new Feat();
        final Boni boni = new Boni();
        boni.addBonus(new Bonus());
        feat.setBoni(boni);
        this.feats.addFeat(feat);
        this.feats.addFeat(feat);
        this.feats.addFeat(feat);

        assertEquals(3L, Iterators.size(this.feats
                .getBoni(this.context, this.resolver).iterator()));
    }

}
