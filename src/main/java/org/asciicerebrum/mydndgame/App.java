package org.asciicerebrum.mydndgame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author species8472
 */
public class App {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

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
                new LevelAdvancement("fighter", null, null));
        setupHarsk.getLevelAdvancementStack().add(
                new LevelAdvancement("fighter", 7L, null));

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
                new LevelAdvancement("fighter", null, null));

        DndCharacter harsk
                = new DndCharacter.Builder(setupHarsk, context).build();
        DndCharacter valeros
                = new DndCharacter.Builder(setupValeros, context).build();

        // HP
        System.out.println("Harsk HP :: " + harsk.getMaxHp()
                + " :: baseAtk_1 " + harsk.getBaseAtkBoni().get(0).getValue()
                + " :: AC " + harsk.getAc());
        System.out.println("Valeros HP :: " + valeros.getMaxHp()
                + " :: baseAtk_1 " + valeros.getBaseAtkBoni().get(0).getValue()
                + " :: AC " + valeros.getAc());

        //TODO Melee Attack Bonus (dynamic str bonus)
    }
}
