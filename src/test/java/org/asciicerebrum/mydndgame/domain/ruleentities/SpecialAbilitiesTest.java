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
public class SpecialAbilitiesTest {

    private SpecialAbilities specialAbilities;

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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBonusSourcesEmptyTest() {
        assertEquals(0L, Iterators.size(this.specialAbilities
                .getBonusSources().iterator()));
    }

    @Test
    public void getBonusSourcesNonEmptyTest() {
        this.specialAbilities.add(new SpecialAbility());
        this.specialAbilities.add(new SpecialAbility());

        assertEquals(2L, Iterators.size(this.specialAbilities
                .getBonusSources().iterator()));
    }

}
