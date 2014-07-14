package org.asciicerebrum.mydndgame;

import java.util.List;
import org.asciicerebrum.mydndgame.testcategories.IntegrationTest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author species8472
 */
@Category(IntegrationTest.class)
public class CharacterCreationIntegrationTest {

    private static final String CLASS_FIGHTER = "fighter";
    private static final String FEAT_WEAPON_FINESSE = "weaponFinesse";
    private static final String AXE4HARSK_ID = "battleAxeHarsk";
    private static final String RAPIER4VALEROS_ID = "rapierValeros";
    private static final String DAGGER4VALEROS_ID = "mwkRapierValeros";
    private static final String PRIMARY_HAND_TYPE = "primaryHand";

    private DndCharacter harsk;
    private DndCharacter valeros;

    private BodySlotType primaryHand;

    @Before
    public void setUp() {
        ApplicationContext context
                = new ClassPathXmlApplicationContext(
                        new String[]{"applicationContext.xml"});

        // instance of bean factory to add new beans programmatically, e.g.
        // all the weapons!
        ConfigurableListableBeanFactory beanFactory
                = ((ConfigurableApplicationContext) context).getBeanFactory();

        WeaponSetup axe4HarskSetup = new WeaponSetup();
        axe4HarskSetup.setId(AXE4HARSK_ID);
        axe4HarskSetup.setName("battleaxe");
        axe4HarskSetup.setSizeCategory("medium");
        Weapon axe4Harsk
                = new Weapon.Builder(axe4HarskSetup, context).build();

        WeaponSetup rapier4ValerosSetup = new WeaponSetup();
        rapier4ValerosSetup.setId(RAPIER4VALEROS_ID);
        rapier4ValerosSetup.setName("rapier");
        rapier4ValerosSetup.setSizeCategory("medium");
        Weapon rapier4Valeros
                = new Weapon.Builder(rapier4ValerosSetup, context).build();

        WeaponSetup dagger4ValerosSetup = new WeaponSetup();
        dagger4ValerosSetup.setId(DAGGER4VALEROS_ID);
        dagger4ValerosSetup.setName("dagger");
        dagger4ValerosSetup.setSizeCategory("medium");
        Weapon dagger4Valeros
                = new Weapon.Builder(dagger4ValerosSetup, context).build();

        beanFactory.registerSingleton(AXE4HARSK_ID, axe4Harsk);
        beanFactory.registerSingleton(RAPIER4VALEROS_ID, rapier4Valeros);
        beanFactory.registerSingleton(DAGGER4VALEROS_ID, dagger4Valeros);

        CharacterSetup setupHarsk = new CharacterSetup();
        setupHarsk.setName("Harsk");
        setupHarsk.setRace("dwarf");
        setupHarsk.getBaseAbilityMap().put("str", 13L);
        setupHarsk.getBaseAbilityMap().put("dex", 11L);
        setupHarsk.getBaseAbilityMap().put("con", 13L);
        setupHarsk.getBaseAbilityMap().put("int", 10L);
        setupHarsk.getBaseAbilityMap().put("wis", 10L);
        setupHarsk.getBaseAbilityMap().put("cha", 8L);
        setupHarsk.getLevelAdvancementStack().add(
                new LevelAdvancement(CLASS_FIGHTER, null, null, "powerAttack"));
        setupHarsk.getLevelAdvancementStack().add(
                new LevelAdvancement(CLASS_FIGHTER, 7L, null, null));
        setupHarsk.getPossessionContainer().put(AXE4HARSK_ID, PRIMARY_HAND_TYPE);
        setupHarsk.getStateRegistry().put("powerAttackValue", 1L); // first parameter is the stateKey, which is defined in the feat bean, as well as the type of the value - here Long

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
                new LevelAdvancement(CLASS_FIGHTER, null, null, FEAT_WEAPON_FINESSE));
        setupValeros.getPossessionContainer().put(RAPIER4VALEROS_ID, PRIMARY_HAND_TYPE);
        setupValeros.getPossessionContainer().put(DAGGER4VALEROS_ID, "secondaryHand");
        setupValeros.getStateRegistry().put("weaponFinesseMode." + RAPIER4VALEROS_ID, "true"); // this must not be directly saved at the weapon; stateKey and value type Weapon (or String?)
        setupValeros.getStateRegistry().put("weaponFinesseMode." + DAGGER4VALEROS_ID, "true");
        setupValeros.getStateRegistry().put("weaponDamageMode." + DAGGER4VALEROS_ID, "slashing");

        this.primaryHand = context.getBean(PRIMARY_HAND_TYPE, BodySlotType.class);

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

    @Test
    public void valerosFeatNumber() {
        assertEquals(1, this.valeros.getFeats().size());
    }

    @Test
    public void valerosFirstFeatName() {
        assertEquals(FEAT_WEAPON_FINESSE, this.valeros.getFeats().get(0).getId());
    }

    @Test
    public void valerosPrimaryHandItemName() {
        assertEquals(RAPIER4VALEROS_ID,
                this.valeros.getBodySlots().get(0).getItem().getId());
    }

    @Test
    public void valerosMeleeAtkBonusFirstValue() {
        List<Long> meleeAtkBoni
                = this.valeros.getMeleeAtkBonus(this.primaryHand);

        assertEquals(Long.valueOf(2), meleeAtkBoni.get(0));
    }

    @Test
    public void harskMeleeAtkBonusFirstValue() {
        List<Long> meleeAtkBoni
                = this.harsk.getMeleeAtkBonus(this.primaryHand);

        assertEquals(Long.valueOf(3), meleeAtkBoni.get(0));
    }

    //TODO Melee Attack Bonus (dynamic str bonus)
}
