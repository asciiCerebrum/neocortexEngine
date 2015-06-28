package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponPrototype;
import org.asciicerebrum.neocortexengine.facades.game.WeaponServiceFacade;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
public class CorrectWeaponAttackModeEvaluatorTest {
    
    private CorrectWeaponAttackModeEvaluator evaluator;
    
    private SituationContextService situationContextService;
    
    private WeaponServiceFacade weaponServiceFacade;
    
    private WeaponCategory refAttackMode;
    
    public CorrectWeaponAttackModeEvaluatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.evaluator = new CorrectWeaponAttackModeEvaluator();
        this.situationContextService = mock(SituationContextService.class);
        this.weaponServiceFacade = mock(WeaponServiceFacade.class);
        this.refAttackMode = new WeaponCategory();
        
        this.evaluator.setSituationContextService(this.situationContextService);
        this.evaluator.setWeaponServiceFacade(this.weaponServiceFacade);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void evaluateCorrectTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();
        contextItem.setUniqueId(new UniqueId("contextItem"));
        
        when(this.situationContextService.getItemAttackMode(
                contextItem.getUniqueId(), (DndCharacter) dndCharacter))
                .thenReturn(this.refAttackMode);
        when(this.weaponServiceFacade.isAttackModeCompatible(
                this.refAttackMode, (Weapon) contextItem,
                (DndCharacter) dndCharacter)).thenReturn(Boolean.TRUE);
        
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }
    
    @Test
    public void evaluateNullAttackModeTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final Weapon contextItem = new Weapon();
        final WeaponPrototype proto = new WeaponPrototype();
        contextItem.setUniqueId(new UniqueId("contextItem"));
        contextItem.setInventoryItemPrototype(proto);
        
        when(this.situationContextService.getItemAttackMode(
                contextItem.getUniqueId(), (DndCharacter) dndCharacter))
                .thenReturn(null);
        
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }
    
    @Test
    public void evaluateNullItemTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = null;
        
        when(this.situationContextService.getItemAttackMode(
                (UniqueId) anyObject(), (DndCharacter) eq(dndCharacter)))
                .thenReturn(null);
        
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }
    
    @Test
    public void evaluateAllWrongTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Armor();
        contextItem.setUniqueId(new UniqueId("contextItem"));
        
        when(this.situationContextService.getItemAttackMode(
                contextItem.getUniqueId(), (DndCharacter) dndCharacter))
                .thenReturn(null);
        
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }
    
    @Test
    public void evaluateNoWeaponTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Armor();
        contextItem.setUniqueId(new UniqueId("contextItem"));
        
        when(this.situationContextService.getItemAttackMode(
                contextItem.getUniqueId(), (DndCharacter) dndCharacter))
                .thenReturn(this.refAttackMode);
        
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }
}
