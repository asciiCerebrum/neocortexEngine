package org.asciicerebrum.mydndgame.mechanics.observertriggers;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusType;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Bonus.ResemblanceFacet;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class RemoveBonusObserverTriggerTest {

    private RemoveBonusObserverTrigger trigger;

    private Bonus removeBonus;

    private BonusType removeBonusType;

    private ResemblanceFacet[] resemblanceFacets;

    public RemoveBonusObserverTriggerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.trigger = new RemoveBonusObserverTrigger();
        this.removeBonus = new Bonus();
        this.removeBonusType = new BonusType();
        this.removeBonus.setBonusType(this.removeBonusType);
        this.resemblanceFacets = new ResemblanceFacet[]{
            ResemblanceFacet.BONUS_TYPE};

        this.trigger.setRemoveBonus(this.removeBonus);
        this.trigger.setResemblanceFacets(this.resemblanceFacets);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void triggerTest() {
        final Boni boni = new Boni();
        final Bonus bonusA = new Bonus();
        boni.addBonus(bonusA);
        bonusA.setBonusType(new BonusType());
        final Bonus bonusB = new Bonus();
        boni.addBonus(bonusB);
        bonusB.setBonusType(this.removeBonusType);
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        final ContextBoni ctxBoni = new ContextBoni(boni, contextItem);

        this.trigger.trigger(ctxBoni, dndCharacter, contextItem);

        assertEquals(1L, Iterators.size(ctxBoni.iterator()));
    }
}
