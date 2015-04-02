package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.ruleentities.Feat;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.FeatSetup;
import org.asciicerebrum.mydndgame.domain.setup.LevelAdvancementSetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class LevelAdvancementFactoryTest {

    private LevelAdvancementFactory factory;

    private ApplicationContext applicationContext;

    private EntityFactory<Feat> featFactory;

    public LevelAdvancementFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new LevelAdvancementFactory();
        this.applicationContext = mock(ApplicationContext.class);
        this.featFactory = mock(EntityFactory.class);

        ApplicationContextProvider ctxProvider
                = new ApplicationContextProvider();
        ctxProvider.setApplicationContext(this.applicationContext);
        this.factory.setFeatFactory(this.featFactory);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final EntitySetup setup = new LevelAdvancementSetup();

        this.factory.newEntity(setup);
    }

    private void makeComplete(LevelAdvancementSetup setup) {
        setup.setAdvancementNumber("2");
        setup.setClassLevel("2");
        setup.setHpAdvancement("12");
    }

    @Test
    public void newEntityCompleteTest() {
        final LevelAdvancementSetup setup = new LevelAdvancementSetup();

        this.makeComplete(setup);

        final LevelAdvancement lvlAdvResult
                = this.factory.newEntity(setup);

        assertEquals(2L, lvlAdvResult.getAdvNumber().getValue());
    }

    @Test
    public void newEntityWithoutAbilityTest() {
        final LevelAdvancementSetup setup = new LevelAdvancementSetup();

        this.makeComplete(setup);

        this.factory.newEntity(setup);

        verify(this.applicationContext, times(0))
                .getBean(anyString(), eq(Ability.class));
    }

    @Test
    public void newEntityWithAbilityTest() {
        final LevelAdvancementSetup setup = new LevelAdvancementSetup();

        this.makeComplete(setup);
        setup.setAbilityAdvancement("abilityId");

        this.factory.newEntity(setup);

        verify(this.applicationContext, times(1))
                .getBean("abilityId", Ability.class);
    }

    @Test
    public void newEntityWithFeatTest() {
        final LevelAdvancementSetup setup = new LevelAdvancementSetup();

        this.makeComplete(setup);
        final EntitySetup featSetup = new FeatSetup();
        setup.setFeatAdvancement(featSetup);

        this.factory.newEntity(setup);

        verify(this.featFactory, times(1)).newEntity(featSetup);
    }

}
