package org.asciicerebrum.mydndgame.integrationtests.services;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.factories.DndCharacterFactory;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTargets;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters.HarskDwarfFighter2;
import org.asciicerebrum.mydndgame.services.core.BonusCalculationService;
import org.asciicerebrum.mydndgame.testcategories.IntegrationTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
public class DefaultBonusCalculationServiceImplIntegrationTest {

    @Autowired
    private BonusCalculationService bonusCalculationService;

    @Autowired
    private DndCharacterFactory dndCharacterFactory;

    @Autowired
    private ApplicationContext context;

    @Test
    public void calculateBonusValuesTest() {
        final DndCharacter harsk = this.dndCharacterFactory
                .newEntity(HarskDwarfFighter2.getSetup());
        final Ability dex = this.context.getBean("dex", Ability.class);

        final BonusValueTuple tuple
                = this.bonusCalculationService.calculateBonusValues(
                        new BonusSources(harsk),
                        new BonusTargets(dex),
                        harsk,
                        new ObserverSources(harsk),
                        new ObserverHooks(ObserverHook.ABILITY,
                                dex.getAssociatedHook()),
                        harsk);
        // there is no bonus granted for the targets/hooks combination
        assertNull(tuple.getBonusValueByRank(BonusRank.RANK_0));
    }

    @Test
    public void accumulateBoniByTargetsTest() {
        final DndCharacter harsk = this.dndCharacterFactory
                .newEntity(HarskDwarfFighter2.getSetup());
        final Ability dex = this.context.getBean("dex", Ability.class);

        final ContextBoni boni
                = this.bonusCalculationService.accumulateBoniByTargets(
                        new BonusSources(harsk),
                        new BonusTargets(dex),
                        harsk);
        assertEquals(0L, Iterators.size(boni.iterator()));
    }

}
