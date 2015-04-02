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
public class SpecialAbilitiesTest {

    private SpecialAbilities specialAbilities;

    private UniqueEntityResolver resolver;

    public SpecialAbilitiesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.specialAbilities = new SpecialAbilities();
        this.resolver = mock(UniqueEntityResolver.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBonusSourcesEmptyTest() {
        assertEquals(0L, Iterators.size(this.specialAbilities
                .getBonusSources(this.resolver).iterator()));
    }

    @Test
    public void getBonusSourcesNonEmptyTest() {
        this.specialAbilities.add(new SpecialAbility());
        this.specialAbilities.add(new SpecialAbility());

        assertEquals(2L, Iterators.size(this.specialAbilities
                .getBonusSources(this.resolver).iterator()));
    }

}
