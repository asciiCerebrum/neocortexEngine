package org.asciicerebrum.neocortexengine.mechanics.managers;

import com.google.common.collect.Iterators;
import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceNumber;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceRoll;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceSides;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueIds;
import org.asciicerebrum.neocortexengine.domain.events.RollHistoryEntry;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Dice;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.RollResult;
import org.asciicerebrum.neocortexengine.mechanics.eventlisteners.RollHistoryListener;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class DefaultRollResultManagerTest {
    
    private DefaultRollResultManager manager;
    
    private DiceRollManager diceRollManager;
    
    private RollHistoryListener listenerA;
    
    private RollHistoryListener listenerB;
    
    private List<RollHistoryListener> listeners;
    
    public DefaultRollResultManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.manager = new DefaultRollResultManager();
        this.diceRollManager = mock(DiceRollManager.class);
        this.listenerA = mock(RollHistoryListener.class);
        this.listenerB = mock(RollHistoryListener.class);
        this.listeners = new ArrayList<RollHistoryListener>();
        
        this.listeners.add(this.listenerA);
        this.listeners.add(this.listenerB);
        
        this.manager.setDiceRollManager(this.diceRollManager);
        this.manager.setRollHistoryListeners(this.listeners);
        
        final DiceRoll diceRoll = new DiceRoll(10L);
        when(this.diceRollManager.rollDice((DiceAction) anyObject()))
                .thenReturn(diceRoll);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void retrieveRollResultCalcTotalTest() {
        final BonusValue bonusValue = new BonusValue(1L);
        final DiceAction diceAction = new DiceAction();
        diceAction.setId(new UniqueId("diceActionId"));
        diceAction.setDiceNumber(new DiceNumber(1L));
        final Dice diceType = new Dice();
        diceType.setSides(new DiceSides(20L));
        diceAction.setDiceType(diceType);
        final UniqueEntity contextEntity = new Weapon();
        final DndCharacter sourceCharacter = new DndCharacter();
        sourceCharacter.setUniqueId(new UniqueId("sourcheCharacterId"));
        final UniqueIds targetIds = new UniqueIds();
        final WorldDate worldDate = new WorldDate();
        final Campaign campaign = new Campaign();
        
        final RollResult result = this.manager.retrieveRollResult(bonusValue,
                diceAction, contextEntity, sourceCharacter, targetIds,
                worldDate, campaign);
        
        assertEquals(11L, result.calcTotalResult().getValue());
    }
    
    @Test
    public void retrieveRollResultAddToCampaignTest() {
        final BonusValue bonusValue = new BonusValue(1L);
        final DiceAction diceAction = new DiceAction();
        diceAction.setId(new UniqueId("diceActionId"));
        diceAction.setDiceNumber(new DiceNumber(1L));
        final Dice diceType = new Dice();
        diceType.setSides(new DiceSides(20L));
        diceAction.setDiceType(diceType);
        final UniqueEntity contextEntity = new Weapon();
        final DndCharacter sourceCharacter = new DndCharacter();
        sourceCharacter.setUniqueId(new UniqueId("sourcheCharacterId"));
        final UniqueIds targetIds = new UniqueIds();
        final WorldDate worldDate = new WorldDate();
        final Campaign campaign = new Campaign();
        
        this.manager.retrieveRollResult(bonusValue,
                diceAction, contextEntity, sourceCharacter, targetIds,
                worldDate, campaign);
        
        assertEquals(1L, Iterators.size(campaign.getRollHistory().iterator()));
    }
    
    @Test
    public void retrieveRollResultInvokeListenerTest() {
        final BonusValue bonusValue = new BonusValue(1L);
        final DiceAction diceAction = new DiceAction();
        diceAction.setId(new UniqueId("diceActionId"));
        diceAction.setDiceNumber(new DiceNumber(1L));
        final Dice diceType = new Dice();
        diceType.setSides(new DiceSides(20L));
        diceAction.setDiceType(diceType);
        final UniqueEntity contextEntity = new Weapon();
        final DndCharacter sourceCharacter = new DndCharacter();
        sourceCharacter.setUniqueId(new UniqueId("sourcheCharacterId"));
        final UniqueIds targetIds = new UniqueIds();
        final WorldDate worldDate = new WorldDate();
        final Campaign campaign = new Campaign();
        
        this.manager.retrieveRollResult(bonusValue,
                diceAction, contextEntity, sourceCharacter, targetIds,
                worldDate, campaign);
        
        verify(this.listenerB, times(1))
                .broadcast((RollHistoryEntry) anyObject());
    }
    
    @Test
    public void retrieveRollResultNoListenersTest() {
        final BonusValue bonusValue = new BonusValue(1L);
        final DiceAction diceAction = new DiceAction();
        diceAction.setId(new UniqueId("diceActionId"));
        diceAction.setDiceNumber(new DiceNumber(1L));
        final Dice diceType = new Dice();
        diceType.setSides(new DiceSides(20L));
        diceAction.setDiceType(diceType);
        final UniqueEntity contextEntity = new Weapon();
        final DndCharacter sourceCharacter = new DndCharacter();
        sourceCharacter.setUniqueId(new UniqueId("sourcheCharacterId"));
        final UniqueIds targetIds = new UniqueIds();
        final WorldDate worldDate = new WorldDate();
        final Campaign campaign = new Campaign();
        
        this.manager.setRollHistoryListeners(null);
        
        this.manager.retrieveRollResult(bonusValue,
                diceAction, contextEntity, sourceCharacter, targetIds,
                worldDate, campaign);
        
        verify(this.listenerB, times(0))
                .broadcast((RollHistoryEntry) anyObject());
    }
    
    @Test
    public void retrieveRollResultWithContextTest() {
        final BonusValue bonusValue = new BonusValue(1L);
        final DiceAction diceAction = new DiceAction();
        diceAction.setId(new UniqueId("diceActionId"));
        diceAction.setDiceNumber(new DiceNumber(1L));
        final Dice diceType = new Dice();
        diceType.setSides(new DiceSides(20L));
        diceAction.setDiceType(diceType);
        final UniqueEntity contextEntity = new Weapon();
        contextEntity.setUniqueId(new UniqueId("weaponId"));
        final DndCharacter sourceCharacter = new DndCharacter();
        sourceCharacter.setUniqueId(new UniqueId("sourcheCharacterId"));
        final UniqueIds targetIds = new UniqueIds();
        final WorldDate worldDate = new WorldDate();
        final Campaign campaign = new Campaign();
        
        this.manager.retrieveRollResult(bonusValue,
                diceAction, contextEntity, sourceCharacter, targetIds,
                worldDate, campaign);
        
        assertNotNull(campaign.getRollHistory().iterator().next()
                .getContextEntityId());
    }
    
    @Test
    public void retrieveRollResultNoContextTest() {
        final BonusValue bonusValue = new BonusValue(1L);
        final DiceAction diceAction = new DiceAction();
        diceAction.setId(new UniqueId("diceActionId"));
        diceAction.setDiceNumber(new DiceNumber(1L));
        final Dice diceType = new Dice();
        diceType.setSides(new DiceSides(20L));
        diceAction.setDiceType(diceType);
        final UniqueEntity contextEntity = null;
        final DndCharacter sourceCharacter = new DndCharacter();
        sourceCharacter.setUniqueId(new UniqueId("sourcheCharacterId"));
        final UniqueIds targetIds = new UniqueIds();
        final WorldDate worldDate = new WorldDate();
        final Campaign campaign = new Campaign();
        
        this.manager.retrieveRollResult(bonusValue,
                diceAction, contextEntity, sourceCharacter, targetIds,
                worldDate, campaign);
        
        assertNull(campaign.getRollHistory().iterator().next()
                .getContextEntityId());
    }
    
}
