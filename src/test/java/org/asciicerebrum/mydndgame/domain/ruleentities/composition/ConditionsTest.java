package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.mydndgame.domain.ruleentities.ConditionType;
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
public class ConditionsTest {

    private Conditions conditions;

    private UniqueEntityResolver resolver;

    private UniqueEntity context;

    public ConditionsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.conditions = new Conditions();

        final Boni boni = new Boni();
        boni.addBonus(new Bonus());
        final ConditionType condType = new ConditionType();
        condType.setBoni(boni);

        final Condition condA = new Condition();
        condA.setConditionType(condType);
        final Condition condB = new Condition();
        condB.setConditionType(condType);
        final Condition condC = new Condition();
        condC.setConditionType(condType);

        this.conditions.add(condA);
        this.conditions.add(condB);
        this.conditions.add(condC);

        this.resolver = mock(UniqueEntityResolver.class);
        this.context = new Weapon();
        this.context.setUniqueId(new UniqueId("context"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBoniTest() {
        final ContextBoni result = this.conditions
                .getBoni(this.context, this.resolver);

        assertEquals(3L, Iterators.size(result.iterator()));
    }

}
