package org.asciicerebrum.neocortexengine.integrationtests.services;

import com.google.common.collect.Iterators;
import org.asciicerebrum.neocortexengine.domain.factories.DndCharacterFactory;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHooks;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;
import org.asciicerebrum.neocortexengine.integrationtests.pool.dndCharacters.HarskDwarfFighter2;
import org.asciicerebrum.neocortexengine.services.core.ObservableService;
import org.asciicerebrum.neocortexengine.testcategories.IntegrationTest;
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
