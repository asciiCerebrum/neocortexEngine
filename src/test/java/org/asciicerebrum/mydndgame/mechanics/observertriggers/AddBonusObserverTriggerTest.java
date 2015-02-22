package org.asciicerebrum.mydndgame.mechanics.observertriggers;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusType;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class AddBonusObserverTriggerTest {

    private AddBonusObserverTrigger trigger;

    private Bonus addBonus;

    private BonusTarget bonusTarget;

    private BonusType bonusType;

    private SituationContextService sitConService;

    public AddBonusObserverTriggerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.trigger = new AddBonusObserverTrigger();
        this.addBonus = new Bonus();
        this.bonusTarget = mock(BonusTarget.class);
        this.bonusType = new BonusType();
        this.sitConService = mock(SituationContextService.class);

        this.trigger.setAddBonus(this.addBonus);
        this.trigger.setBonusTarget(this.bonusTarget);
        this.trigger.setBonusType(this.bonusType);
        this.trigger.setRegistryStateKey(
                StateRegistry.StateParticle.ACTIVE_ITEM);
        this.trigger.setSituationContextService(this.sitConService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void triggerGiveSimpleBonusTest() {
        final Boni boni = new Boni();
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity weapon = new Weapon();

        this.trigger.trigger(boni, dndCharacter, weapon);

        assertTrue(boni.contains(this.addBonus));
    }

    @Test
    public void triggerZeroValueBonusTest() {
        final Boni boni = new Boni();
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity weapon = new Weapon();
        this.trigger.setAddBonus(null);
        when(this.sitConService.getBonusValueForKey(
                StateRegistry.StateParticle.ACTIVE_ITEM,
                (DndCharacter) dndCharacter)).thenReturn(new BonusValue(0L));

        this.trigger.trigger(boni, dndCharacter, weapon);

        assertEquals(0L, Iterators.size(boni.iterator()));
    }

    @Test
    public void triggerNonZeroValueBonusTest() {
        final Boni boni = new Boni();
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity weapon = new Weapon();
        this.trigger.setAddBonus(null);
        when(this.sitConService.getBonusValueForKey(
                StateRegistry.StateParticle.ACTIVE_ITEM,
                (DndCharacter) dndCharacter)).thenReturn(new BonusValue(7L));

        this.trigger.trigger(boni, dndCharacter, weapon);

        assertEquals(7L, boni.iterator().next().getValues()
                .getBonusValueByRank(BonusRank.RANK_0).getValue());
    }

}
