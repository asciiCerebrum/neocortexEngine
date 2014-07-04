package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.testcategories.IntegrationTest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author species8472
 */
@Category(IntegrationTest.class)
public class CharacterCreationIntegrationTest {

    private static final String CLASS_FIGHTER = "fighter";

    private DndCharacter harsk;
    private DndCharacter valeros;

    @Before
    public void setUp() {
        ApplicationContext context
                = new ClassPathXmlApplicationContext(
                        new String[]{"applicationContext.xml"});

        CharacterSetup setupHarsk = new CharacterSetup();
        setupHarsk.setName("Harsk");
        setupHarsk.setRace("dwarf");
        setupHarsk.getBaseAbilityMap().put("str", 12L);
        setupHarsk.getBaseAbilityMap().put("dex", 11L);
        setupHarsk.getBaseAbilityMap().put("con", 13L);
        setupHarsk.getBaseAbilityMap().put("int", 10L);
        setupHarsk.getBaseAbilityMap().put("wis", 10L);
        setupHarsk.getBaseAbilityMap().put("cha", 8L);
        setupHarsk.getLevelAdvancementStack().add(
                new LevelAdvancement(CLASS_FIGHTER, null, null));
        setupHarsk.getLevelAdvancementStack().add(
                new LevelAdvancement(CLASS_FIGHTER, 7L, null));

        CharacterSetup setupValeros = new CharacterSetup();
        setupValeros.setName("Valeros");
        setupValeros.setRace("human");
        setupValeros.getBaseAbilityMap().put("str", 12L);
        setupValeros.getBaseAbilityMap().put("dex", 9L);
        setupValeros.getBaseAbilityMap().put("con", 14L);
        setupValeros.getBaseAbilityMap().put("int", 9L);
        setupValeros.getBaseAbilityMap().put("wis", 11L);
        setupValeros.getBaseAbilityMap().put("cha", 10L);
        setupValeros.getLevelAdvancementStack().add(
                new LevelAdvancement(CLASS_FIGHTER, null, null));

        this.harsk
                = new DndCharacter.Builder(setupHarsk, context).build();
        this.valeros
                = new DndCharacter.Builder(setupValeros, context).build();
    }

    @Test
    public void harskMaxHp() {
        assertEquals(Long.valueOf(19), this.harsk.getMaxHp());
    }

    @Test
    public void harskBaseAtk1() {
        assertEquals(Long.valueOf(2),
                this.harsk.getBaseAtkBoni().get(0).getValue());
    }

    @Test
    public void harskBaseAtkLength() {
        assertEquals(1, this.harsk.getBaseAtkBoni().size());
    }

    @Test
    public void harskAC() {
        assertEquals(Long.valueOf(10), this.harsk.getAc());
    }

    @Test
    public void valerosMaxHp() {
        assertEquals(Long.valueOf(12), this.valeros.getMaxHp());
    }

    @Test
    public void valerosBaseAtk1() {
        assertEquals(Long.valueOf(1),
                this.valeros.getBaseAtkBoni().get(0).getValue());
    }

    @Test
    public void valerosBaseAtkLength() {
        assertEquals(1, this.valeros.getBaseAtkBoni().size());
    }

    @Test
    public void valerosAC() {
        assertEquals(Long.valueOf(9), this.valeros.getAc());
    }

    //TODO Melee Attack Bonus (dynamic str bonus)
}
