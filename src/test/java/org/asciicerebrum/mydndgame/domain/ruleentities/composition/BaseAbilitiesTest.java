package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.AbilityScore;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
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
public class BaseAbilitiesTest {

    private BaseAbilities baseAbilities;

    private UniqueEntityResolver resolver;

    private UniqueEntity context;

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

        final Boni boni = new Boni();
        boni.addBonus(new Bonus());

        final Ability abilityA = new Ability();
        abilityA.setId(new UniqueId("abilityA"));
        abilityA.setBoni(boni);
        final Ability abilityB = new Ability();
        abilityB.setId(new UniqueId("abilityB"));
        abilityB.setBoni(boni);
        final Ability abilityC = new Ability();
        abilityC.setId(new UniqueId("abilityC"));
        abilityC.setBoni(boni);

        final BaseAbilityEntry entryA = new BaseAbilityEntry();
        entryA.setAbility(abilityA);
        entryA.setAbilityValue(new AbilityScore(10L));

        final BaseAbilityEntry entryB = new BaseAbilityEntry();
        entryB.setAbility(abilityB);
        entryB.setAbilityValue(new AbilityScore(10L));

        final BaseAbilityEntry entryC = new BaseAbilityEntry();
        entryC.setAbility(abilityC);
        entryC.setAbilityValue(new AbilityScore(10L));

        this.baseAbilities.addBaseAbilityEntry(entryA);
        this.baseAbilities.addBaseAbilityEntry(entryB);
        this.baseAbilities.addBaseAbilityEntry(entryC);

        this.resolver = mock(UniqueEntityResolver.class);
        this.context = new Weapon();
        this.context.setUniqueId(new UniqueId("context"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBoniTest() {
        assertEquals(3L, Iterators.size(this.baseAbilities
                .getBoni(this.context, this.resolver).iterator()));
    }

}
