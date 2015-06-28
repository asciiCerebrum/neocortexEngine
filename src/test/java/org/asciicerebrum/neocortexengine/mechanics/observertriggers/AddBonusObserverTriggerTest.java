package org.asciicerebrum.neocortexengine.mechanics.observertriggers;

import com.google.common.collect.Iterators;
import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.StateRegistry;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusTarget;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusType;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity weapon = new Weapon();

        final ContextBoni ctxBoni = new ContextBoni();

        this.trigger.trigger(ctxBoni, dndCharacter, weapon);

        assertEquals(this.addBonus, ctxBoni.iterator().next().getBonus());
    }

    @Test
    public void triggerZeroValueBonusTest() {
        final ContextBoni ctxBoni = new ContextBoni();
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity weapon = new Weapon();
        final UniqueId weaponId = new UniqueId("weapon");
        weapon.setUniqueId(weaponId);
        this.trigger.setAddBonus(null);
        when(this.sitConService.getBonusValueForKey(
                StateRegistry.StateParticle.ACTIVE_ITEM,
                (DndCharacter) dndCharacter, weaponId))
                .thenReturn(new BonusValue(0L));

        this.trigger.trigger(ctxBoni, dndCharacter, weapon);

        assertEquals(0L, Iterators.size(ctxBoni.iterator()));
    }

    @Test
    public void triggerNonZeroValueBonusTest() {
        final ContextBoni ctxBoni = new ContextBoni();
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity weapon = new Weapon();
        final UniqueId weaponId = new UniqueId("weapon");
        weapon.setUniqueId(weaponId);
        this.trigger.setAddBonus(null);
        when(this.sitConService.getBonusValueForKey(
                StateRegistry.StateParticle.ACTIVE_ITEM,
                (DndCharacter) dndCharacter, weaponId))
                .thenReturn(new BonusValue(7L));

        this.trigger.trigger(ctxBoni, dndCharacter, weapon);

        assertEquals(7L, ctxBoni.iterator().next().getBonus().getValues()
                .getBonusValueByRank(BonusRank.RANK_0).getValue());
    }

}
