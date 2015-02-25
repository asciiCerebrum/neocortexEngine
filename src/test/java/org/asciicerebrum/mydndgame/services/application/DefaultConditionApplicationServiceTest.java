package org.asciicerebrum.mydndgame.services.application;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Condition;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Conditions;
import org.asciicerebrum.mydndgame.services.core.ObservableService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class DefaultConditionApplicationServiceTest {

    private DefaultConditionApplicationService service;

    private ObservableService observableService;

    public DefaultConditionApplicationServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.service = new DefaultConditionApplicationService();
        this.observableService = mock(ObservableService.class);

        this.service.setObservableService(this.observableService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void applyConditionNormalSizeTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Conditions conditions = new Conditions();
        final Condition condA = new Condition();
        final Condition condB = new Condition();
        conditions.add(condA);
        conditions.add(condB);

        when(this.observableService.triggerObservers(
                eq(condA), eq(dndCharacter),
                (ObserverSources) anyObject(),
                (ObserverHooks) anyObject(), eq(dndCharacter)))
                .thenReturn(condA);

        this.service.applyCondition(dndCharacter, conditions);
        assertEquals(1L, Iterators.size(dndCharacter
                .getConditions().iterator()));
    }

    @Test
    public void applyConditionNormalObjectTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Conditions conditions = new Conditions();
        final Condition condA = new Condition();
        final Condition condB = new Condition();
        conditions.add(condA);
        conditions.add(condB);

        when(this.observableService.triggerObservers(
                eq(condA), eq(dndCharacter),
                (ObserverSources) anyObject(),
                (ObserverHooks) anyObject(), eq(dndCharacter)))
                .thenReturn(condA);

        this.service.applyCondition(dndCharacter, conditions);
        assertEquals(condA, dndCharacter.getConditions().iterator().next());
    }

    @Test
    public void removeExpiredConditionsObjectTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Condition condA = new Condition();
        final WorldDate dateA = new WorldDate();
        dateA.setCombatRoundNumber(new CombatRoundNumber(1L));
        dateA.setCombatRoundPosition(new CombatRoundPosition("1"));
        condA.setExpiryDate(dateA);
        final Condition condB = new Condition();
        final WorldDate dateB = new WorldDate();
        dateB.setCombatRoundNumber(new CombatRoundNumber(3L));
        dateB.setCombatRoundPosition(new CombatRoundPosition("4"));
        condB.setExpiryDate(dateB);
        dndCharacter.addCondition(condB);

        final WorldDate currentDate = new WorldDate();
        currentDate.setCombatRoundNumber(new CombatRoundNumber(2L));
        currentDate.setCombatRoundPosition(new CombatRoundPosition("0"));

        this.service.removeExpiredConditions(dndCharacter, currentDate);
        assertEquals(condB, dndCharacter.getConditions().iterator().next());
    }

    @Test
    public void removeExpiredConditionsCountTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Condition condA = new Condition();
        final WorldDate dateA = new WorldDate();
        dateA.setCombatRoundNumber(new CombatRoundNumber(1L));
        dateA.setCombatRoundPosition(new CombatRoundPosition("1"));
        condA.setExpiryDate(dateA);
        final Condition condB = new Condition();
        final WorldDate dateB = new WorldDate();
        dateB.setCombatRoundNumber(new CombatRoundNumber(3L));
        dateB.setCombatRoundPosition(new CombatRoundPosition("4"));
        condB.setExpiryDate(dateB);
        dndCharacter.addCondition(condA);
        dndCharacter.addCondition(condB);

        final WorldDate currentDate = new WorldDate();
        currentDate.setCombatRoundNumber(new CombatRoundNumber(2L));
        currentDate.setCombatRoundPosition(new CombatRoundPosition("0"));

        this.service.removeExpiredConditions(dndCharacter, currentDate);
        assertEquals(1L, Iterators.size(dndCharacter
                .getConditions().iterator()));
    }
}
