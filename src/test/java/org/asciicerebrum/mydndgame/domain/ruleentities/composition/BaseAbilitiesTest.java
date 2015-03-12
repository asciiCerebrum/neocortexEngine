package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.particles.AbilityScore;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
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
public class BaseAbilitiesTest {

    private BaseAbilities baseAbilities;

    public BaseAbilitiesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.baseAbilities = new BaseAbilities();

        final BaseAbilityEntry entryA = new BaseAbilityEntry();
        entryA.setAbility(new Ability());
        entryA.setAbilityValue(new AbilityScore(10L));

        final BaseAbilityEntry entryB = new BaseAbilityEntry();
        entryB.setAbility(new Ability());
        entryB.setAbilityValue(new AbilityScore(10L));

        final BaseAbilityEntry entryC = new BaseAbilityEntry();
        entryC.setAbility(new Ability());
        entryC.setAbilityValue(new AbilityScore(10L));

        this.baseAbilities.addBaseAbilityEntry(entryA);
        this.baseAbilities.addBaseAbilityEntry(entryB);
        this.baseAbilities.addBaseAbilityEntry(entryC);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBonusSourcesTest() {
        assertEquals(3L, Iterators.size(this.baseAbilities.getBonusSources()
                .iterator()));
    }

}
