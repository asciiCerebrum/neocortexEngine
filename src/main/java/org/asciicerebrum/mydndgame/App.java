package org.asciicerebrum.mydndgame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        ApplicationContext context
                = new ClassPathXmlApplicationContext(
                        new String[]{"applicationContext.xml"});

        Race human = context.getBean("human", Race.class);

        System.out.println("CharacterClass :: " + human.getId());

        CharacterSetup setupHarsk = new CharacterSetup();
        setupHarsk.setName("Harsk");
        setupHarsk.setRace("dwarf");
        setupHarsk.getBaseAbilityMap().put("str", 12L);
        setupHarsk.getBaseAbilityMap().put("dex", 11L);
        setupHarsk.getBaseAbilityMap().put("con", 13L);
        setupHarsk.getBaseAbilityMap().put("int", 10L);
        setupHarsk.getBaseAbilityMap().put("wis", 10L);
        setupHarsk.getBaseAbilityMap().put("cha", 8L);
        setupHarsk.getLevelAdvancementStack().push(
                new LevelAdvancement("fighter", null, null));
        setupHarsk.getLevelAdvancementStack().push(
                new LevelAdvancement("fighter", 7, null));
        
        CharacterSetup setupValeros = new CharacterSetup();
        setupValeros.setName("Valeros");
        setupValeros.setRace("human");
        setupValeros.getBaseAbilityMap().put("str", 12L);
        setupValeros.getBaseAbilityMap().put("dex", 9L);
        setupValeros.getBaseAbilityMap().put("con", 14L);
        setupValeros.getBaseAbilityMap().put("int", 9L);
        setupValeros.getBaseAbilityMap().put("wis", 11L);
        setupValeros.getBaseAbilityMap().put("cha", 10L);
        setupValeros.getLevelAdvancementStack().push(
                new LevelAdvancement("fighter", null, null));

        DndCharacter harsk
                = new DndCharacter.Builder(setupHarsk, context).build();
        DndCharacter valeros
                = new DndCharacter.Builder(setupValeros, context).build();

        // HP
        System.out.println("Harsk HP :: " + harsk.getMaxHp()
                + " :: baseAtk " + harsk.getBaseAtkBoni());
        System.out.println("Valeros HP :: " + valeros.getMaxHp()
                + " :: baseAtk " + valeros.getBaseAtkBoni());

        //TODO Melee Attack Bonus
        //TODO armor class
    }
}
