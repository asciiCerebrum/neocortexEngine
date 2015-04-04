package org.asciicerebrum.mydndgame.integrationtests.services;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.factories.DndCharacterFactory;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters.HarskDwarfFighter2;
import org.asciicerebrum.mydndgame.services.core.ObservableService;
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
public class DefaultObservableServiceIntegrationTest {
    
    @Autowired
    private ObservableService observableService;
    
    @Autowired
    private DndCharacterFactory dndCharacterFactory;
    
    @Autowired
    private ApplicationContext context;
    
    @Test
    public void accumulateObserversByHooksTest() {
        final DndCharacter harsk = this.dndCharacterFactory
                .newEntity(HarskDwarfFighter2.getSetup());
        final Ability dex = this.context.getBean("dex", Ability.class);
        
        final Observers observers
                = this.observableService.accumulateObserversByHooks(
                        new ObserverSources(harsk),
                        new ObserverHooks(ObserverHook.ABILITY,
                                dex.getAssociatedHook()),
                        harsk);
        assertEquals(0L, Iterators.size(observers.iterator()));
    }
    
}
