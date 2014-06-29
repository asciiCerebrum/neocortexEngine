package org.asciicerebrum.mydndgame;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author species8472
 */
public final class App {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(App.class);

    /**
     * Defines a character class for the example characters.
     */
    private static final String CLASS_FIGHTER = "fighter";

    /**
     * Label for the base attack bonus.
     */
    private static final String LABEL_BASE_ATK_1 = "baseAtk_1 ";

    /**
     * Label for the armor class.
     */
    private static final String LABEL_AC = "AC ";

    /**
     * Label for the hit points.
     */
    private static final String LABEL_HP = "HP ";

    /**
     * Separator for the labels.
     */
    private static final String LABEL_SEPARATOR = " :: ";

    /**
     * App must not be instantiated.
     */
    private App() {

    }

    /**
     *
     * @param args for the main function.
     */
    public static void main(final String[] args) {

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

        DndCharacter harsk
                = new DndCharacter.Builder(setupHarsk, context).build();
        DndCharacter valeros
                = new DndCharacter.Builder(setupValeros, context).build();

        // HP
        LOGGER.info(harsk.getSetup().getName()
                + LABEL_SEPARATOR
                + LABEL_HP + harsk.getMaxHp()
                + LABEL_SEPARATOR
                + LABEL_BASE_ATK_1 + harsk.getBaseAtkBoni().get(0).getValue()
                + LABEL_SEPARATOR
                + LABEL_AC + harsk.getAc());
        LOGGER.info(valeros.getSetup().getName()
                + LABEL_SEPARATOR
                + LABEL_HP + valeros.getMaxHp()
                + LABEL_SEPARATOR
                + LABEL_BASE_ATK_1 + valeros.getBaseAtkBoni().get(0).getValue()
                + LABEL_SEPARATOR
                + LABEL_AC + valeros.getAc());

        //TODO Melee Attack Bonus (dynamic str bonus)
    }
}
