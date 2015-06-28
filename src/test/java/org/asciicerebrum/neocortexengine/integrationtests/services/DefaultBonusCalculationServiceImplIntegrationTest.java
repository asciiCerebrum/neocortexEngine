package org.asciicerebrum.neocortexengine.integrationtests.services;

import com.google.common.collect.Iterators;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.factories.DndCharacterFactory;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusTargets;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHooks;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;
import org.asciicerebrum.neocortexengine.integrationtests.pool.dndCharacters.HarskDwarfFighter2;
import org.asciicerebrum.neocortexengine.services.core.BonusCalculationService;
import org.asciicerebrum.neocortexengine.testcategories.IntegrationTest;
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
