package org.asciicerebrum.neocortexengine.mechanics.valueproviders;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.Armors;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorCategory;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorPrototype;
import org.asciicerebrum.neocortexengine.facades.game.ArmorServiceFacade;
import org.asciicerebrum.neocortexengine.facades.game.CharacterServiceFacade;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class ArmorCheckPenaltyProviderTest {

    private ArmorCheckPenaltyProvider provider;

    private ArmorCategory armorCategory;

    private ArmorServiceFacade armorServiceFacade;

    private CharacterServiceFacade characterServiceFacade;

    public ArmorCheckPenaltyProviderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.provider = new ArmorCheckPenaltyProvider();
        this.armorCategory = new ArmorCategory();
        this.armorCategory.setUniqueId(new UniqueId("armorCategory"));
        this.armorServiceFacade = mock(ArmorServiceFacade.class);
        this.characterServiceFacade = mock(CharacterServiceFacade.class);

        this.provider.setArmorCategory(this.armorCategory);
        this.provider.setArmorFacade(this.armorServiceFacade);
        this.provider.setCharacterServiceFacade(this.characterServiceFacade);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getDynamicValueNoCategoryTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Armor();

        this.provider.setArmorCategory(null);
        final BonusValue testVal = this.provider.getDynamicValue(
                dndCharacter, contextItem);
        assertEquals(0L, testVal.getValue());
    }

    @Test
    public void getDynamicValueNormalTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        final Armor contextItem = new Armor();
        
        final ArmorCategory catArm = new ArmorCategory();
        catArm.setUniqueId(new UniqueId("armorCategory"));
        final ArmorPrototype protoArm = new ArmorPrototype();
        protoArm.setArmorCategory(catArm);
        contextItem.setInventoryItemPrototype(protoArm);

        when(this.armorServiceFacade.getMinimumArmorCheckPenalty(
                (Armors) anyObject(), (DndCharacter) anyObject()))
                .thenReturn(new BonusValue(5L));

        final Armors armors = new Armors();
        armors.add(contextItem);

        when(this.characterServiceFacade.getArmorWorn(dndCharacter))
                .thenReturn(armors);

        final BonusValue testVal = this.provider.getDynamicValue(
                dndCharacter, contextItem);
        assertEquals(5L, testVal.getValue());
    }

}
