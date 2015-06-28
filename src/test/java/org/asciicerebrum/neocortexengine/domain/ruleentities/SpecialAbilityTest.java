package org.asciicerebrum.neocortexengine.domain.ruleentities;

import com.google.common.collect.Iterators;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.UniqueEntityResolver;
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
public class SpecialAbilityTest {

    private SpecialAbility specialAbility;

    public SpecialAbilityTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.specialAbility = new SpecialAbility();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBoniNullSubAbilitiesTest() {
        final UniqueEntity context = new Weapon();
        final UniqueEntityResolver resolver = mock(UniqueEntityResolver.class);

        final ContextBoni ctxBoni
                = this.specialAbility.getBoni(context, resolver);
        assertEquals(0L, Iterators.size(ctxBoni.iterator()));
    }

}
