package org.asciicerebrum.mydndgame.domain.ruleentities;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
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
public class ArmorPrototypeTest {

    private ArmorPrototype proto;

    public ArmorPrototypeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.proto = new ArmorPrototype();

        final SpecialAbilities specAbs = new SpecialAbilities();
        final SpecialAbility specAb = new SpecialAbility();
        final Boni boni = new Boni();
        final Bonus bonus = new Bonus();
        boni.addBonus(bonus);
        specAb.setBoni(boni);
        specAbs.add(specAb);

        this.proto.setSpecialAbilities(specAbs);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBoniNullArmorCategoryTest() {
        final UniqueEntity context = new Weapon();
        final UniqueEntityResolver resolver = mock(UniqueEntityResolver.class);

        final ContextBoni result = this.proto.getBoni(context, resolver);
        assertEquals(1L, Iterators.size(result.iterator()));
    }

    @Test
    public void getBoniArmorCategoryTest() {
        final ArmorCategory cat = new ArmorCategory();
        final Boni catBoni = new Boni();
        final Bonus catBonus = new Bonus();
        catBoni.addBonus(catBonus);
        cat.setBoni(catBoni);

        this.proto.setArmorCategory(cat);

        final UniqueEntity context = new Weapon();
        final UniqueEntityResolver resolver = mock(UniqueEntityResolver.class);

        final ContextBoni result = this.proto.getBoni(context, resolver);
        assertEquals(2L, Iterators.size(result.iterator()));
    }

}
