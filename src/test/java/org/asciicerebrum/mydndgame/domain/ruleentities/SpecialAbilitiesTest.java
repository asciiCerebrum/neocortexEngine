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
public class SpecialAbilitiesTest {

    private SpecialAbilities specialAbilities;

    private UniqueEntityResolver resolver;

    private UniqueEntity context;

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
        this.context = new Weapon();
        this.context.setUniqueId(new UniqueId("context"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBoniEmptyTest() {
        assertEquals(0L, Iterators.size(this.specialAbilities
                .getBoni(this.context, this.resolver).iterator()));
    }

    @Test
    public void getBoniNonEmptyTest() {
        final SpecialAbility specialAbility = new SpecialAbility();
        final Boni boni = new Boni();
        boni.addBonus(new Bonus());
        specialAbility.setBoni(boni);
        this.specialAbilities.add(specialAbility);
        this.specialAbilities.add(specialAbility);

        assertEquals(2L, Iterators.size(this.specialAbilities
                .getBoni(this.context, this.resolver).iterator()));
    }

}
