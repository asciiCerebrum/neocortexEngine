package org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters;

import java.util.ArrayList;
import org.asciicerebrum.mydndgame.domain.setup.BaseAbilityEntrySetup;
import org.asciicerebrum.mydndgame.domain.setup.CharacterSetup;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.FeatSetup;
import org.asciicerebrum.mydndgame.domain.setup.LevelAdvancementSetup;
import org.asciicerebrum.mydndgame.domain.setup.PersonalizedBodySlotSetup;
import org.asciicerebrum.mydndgame.domain.setup.StateRegistrySetup;

/**
 *
 * @author species8472
 */
public class HarskDwarfFighter2 {

    public static CharacterSetup getSetup() {
        final CharacterSetup harsk = new CharacterSetup();

        harsk.setId("harsk");
        harsk.setRace("dwarf");
        harsk.setCurrentXp("1000");
        harsk.setCurrentHp("18");

        final BaseAbilityEntrySetup strSetup = new BaseAbilityEntrySetup();
        strSetup.setAbility("str");
        strSetup.setAbilityValue("14");
        final BaseAbilityEntrySetup dexSetup = new BaseAbilityEntrySetup();
        dexSetup.setAbility("dex");
        dexSetup.setAbilityValue("15");
        final BaseAbilityEntrySetup conSetup = new BaseAbilityEntrySetup();
        conSetup.setAbility("con");
        conSetup.setAbilityValue("15");
        final BaseAbilityEntrySetup intSetup = new BaseAbilityEntrySetup();
        intSetup.setAbility("int");
        intSetup.setAbilityValue("10");
        final BaseAbilityEntrySetup wisSetup = new BaseAbilityEntrySetup();
        wisSetup.setAbility("wis");
        wisSetup.setAbilityValue("12");
        final BaseAbilityEntrySetup chaSetup = new BaseAbilityEntrySetup();
        chaSetup.setAbility("cha");
        chaSetup.setAbilityValue("6");

        harsk.setBaseAbilitySetups(new ArrayList<EntitySetup>() {
            {
                this.add(strSetup);
                this.add(dexSetup);
                this.add(conSetup);
                this.add(intSetup);
                this.add(wisSetup);
                this.add(chaSetup);
            }
        });

        final FeatSetup feat1Setup = new FeatSetup();
        feat1Setup.setFeatType("powerAttack");

        final LevelAdvancementSetup fighter1Setup = new LevelAdvancementSetup();
        fighter1Setup.setAdvancementNumber("0");
        fighter1Setup.setClassLevel("fighter1");
        fighter1Setup.setHpAdvancement("0");
        fighter1Setup.addFeatAdvancement(feat1Setup);
        final LevelAdvancementSetup fighter2Setup = new LevelAdvancementSetup();
        fighter2Setup.setAdvancementNumber("1");
        fighter2Setup.setClassLevel("fighter2");
        fighter2Setup.setHpAdvancement("6");

        harsk.setLevelAdvancementSetups(new ArrayList<EntitySetup>() {
            {
                this.add(fighter1Setup);
                this.add(fighter2Setup);
            }
        });

        final PersonalizedBodySlotSetup hand1Setup
                = new PersonalizedBodySlotSetup();
        hand1Setup.setBodySlotType("primaryHand");
        hand1Setup.setItem("standardBattleaxe");
        hand1Setup.setIsPrimaryAttackSlot("true");
        final PersonalizedBodySlotSetup hand2Setup
                = new PersonalizedBodySlotSetup();
        hand2Setup.setBodySlotType("secondaryHand");
        hand2Setup.setItem("standardLightWoodenShield");
        hand2Setup.setIsPrimaryAttackSlot("false");
        final PersonalizedBodySlotSetup torsoSetup
                = new PersonalizedBodySlotSetup();
        torsoSetup.setBodySlotType("torso");
        torsoSetup.setItem("standardStuddedLeather");
        torsoSetup.setIsPrimaryAttackSlot("false");

        harsk.setBodySlotSetups(new ArrayList<EntitySetup>() {
            {
                this.add(hand1Setup);
                this.add(hand2Setup);
                this.add(torsoSetup);
            }
        });

        final StateRegistrySetup regSetup = new StateRegistrySetup();
        final StateRegistrySetup.StateRegistryEntrySetup entrySetup
                = new StateRegistrySetup.StateRegistryEntrySetup();

        entrySetup.setRegistryParticle("POWER_ATTACK_BONUS");
        entrySetup.setContextObjectId("standardBattleaxe");
        entrySetup.setRegistryValue("-1");
        entrySetup.setRegistryValueType("LONG");

        regSetup.addStateRegistryEntry(entrySetup);
        harsk.setStateRegistrySetup(regSetup);

        return harsk;
    }

}
