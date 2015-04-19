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
public class MerisielElfRogue1 {

    public static CharacterSetup getSetup() {
        final CharacterSetup merisiel = new CharacterSetup();

        merisiel.setId("merisiel");
        merisiel.setRace("elf");
        merisiel.setCurrentXp("0");
        merisiel.setCurrentHp("0");

        final BaseAbilityEntrySetup strSetup = new BaseAbilityEntrySetup();
        strSetup.setAbility("str");
        strSetup.setAbilityValue("12");
        final BaseAbilityEntrySetup dexSetup = new BaseAbilityEntrySetup();
        dexSetup.setAbility("dex");
        dexSetup.setAbilityValue("17");
        final BaseAbilityEntrySetup conSetup = new BaseAbilityEntrySetup();
        conSetup.setAbility("con");
        conSetup.setAbilityValue("12");
        final BaseAbilityEntrySetup intSetup = new BaseAbilityEntrySetup();
        intSetup.setAbility("int");
        intSetup.setAbilityValue("8");
        final BaseAbilityEntrySetup wisSetup = new BaseAbilityEntrySetup();
        wisSetup.setAbility("wis");
        wisSetup.setAbilityValue("13");
        final BaseAbilityEntrySetup chaSetup = new BaseAbilityEntrySetup();
        chaSetup.setAbility("cha");
        chaSetup.setAbilityValue("10");

        merisiel.setBaseAbilitySetups(new ArrayList<EntitySetup>() {
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
        feat1Setup.setFeatType("weaponFinesse");

        final LevelAdvancementSetup rogue1Setup = new LevelAdvancementSetup();
        rogue1Setup.setAdvancementNumber("0");
        rogue1Setup.setClassLevel("rogue1");
        rogue1Setup.setHpAdvancement("0");
        rogue1Setup.addFeatAdvancement(feat1Setup);

        merisiel.setLevelAdvancementSetups(new ArrayList<EntitySetup>() {
            {
                this.add(rogue1Setup);
            }
        });

        final PersonalizedBodySlotSetup handSetup
                = new PersonalizedBodySlotSetup();
        handSetup.setBodySlotType("primaryHand");
        handSetup.setItem("mwkRapier");
        handSetup.setIsPrimaryAttackSlot("true");
        final PersonalizedBodySlotSetup torsoSetup
                = new PersonalizedBodySlotSetup();
        torsoSetup.setBodySlotType("torso");
        torsoSetup.setItem("standardStuddedLeather");
        torsoSetup.setIsPrimaryAttackSlot("false");

        merisiel.setBodySlotSetups(new ArrayList<EntitySetup>() {
            {
                this.add(handSetup);
                this.add(torsoSetup);
            }
        });

        final StateRegistrySetup regSetup = new StateRegistrySetup();
        final StateRegistrySetup.StateRegistryEntrySetup entrySetup
                = new StateRegistrySetup.StateRegistryEntrySetup();

        entrySetup.setRegistryParticle("WEAPON_FINESSE_MODE");
        entrySetup.setContextObjectId("mwkRapier");
        entrySetup.setRegistryValue("true");
        entrySetup.setRegistryValueType("BOOLEAN");

        regSetup.addStateRegistryEntry(entrySetup);
        merisiel.setStateRegistrySetup(regSetup);

        return merisiel;
    }

}
