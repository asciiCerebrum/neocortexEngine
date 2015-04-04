package org.asciicerebrum.mydndgame.integrationtests.services;

import org.asciicerebrum.mydndgame.domain.core.particles.AbilityScore;
import org.asciicerebrum.mydndgame.domain.factories.DndCharacterFactory;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters.HarskDwarfFighter2;
import org.asciicerebrum.mydndgame.services.statistics.AbilityCalculationService;
import org.asciicerebrum.mydndgame.testcategories.IntegrationTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author species8472
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class DefaultAbilityCalculationServiceIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AbilityCalculationService abilityCalculationService;

    @Autowired
    private DndCharacterFactory dndCharacterFactory;

    @Test
    public void calcCurrentAbilityScoreTest() {
        final DndCharacter harsk = this.dndCharacterFactory
                .newEntity(HarskDwarfFighter2.getSetup());
        final Ability dex = context.getBean("dex", Ability.class);

        final AbilityScore result
                = this.abilityCalculationService
                .calcCurrentAbilityScore(harsk, dex);

        // lvl 2 fighter harsk has dex 15
        assertEquals(15L, result.getValue());
    }

}
