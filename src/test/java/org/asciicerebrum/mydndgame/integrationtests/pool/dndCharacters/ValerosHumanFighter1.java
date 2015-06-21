package org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters;

import java.util.ArrayList;
import org.asciicerebrum.mydndgame.domain.setup.BaseAbilityEntrySetup;
import org.asciicerebrum.mydndgame.domain.setup.CharacterSetup;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.FeatSetup;
import org.asciicerebrum.mydndgame.domain.setup.LevelAdvancementSetup;
import org.asciicerebrum.mydndgame.domain.setup.PersonalizedBodySlotSetup;

/**
 *
 * @author species8472
 */
public class ValerosHumanFighter1 {

    public static CharacterSetup getSetup() {
        final CharacterSetup valeros = new CharacterSetup();

        valeros.setId("valeros");
        valeros.setRace("human");
        valeros.setCurrentXp("0");
        valeros.setCurrentHp("11");

        final BaseAbilityEntrySetup strSetup = new BaseAbilityEntrySetup();
        strSetup.setAbility("str");
        strSetup.setAbilityValue("14");
        final BaseAbilityEntrySetup dexSetup = new BaseAbilityEntrySetup();
        dexSetup.setAbility("dex");
        dexSetup.setAbilityValue("15");
        final BaseAbilityEntrySetup conSetup = new BaseAbilityEntrySetup();
        conSetup.setAbility("con");
        conSetup.setAbilityValue("12");
        final BaseAbilityEntrySetup intSetup = new BaseAbilityEntrySetup();
        intSetup.setAbility("int");
        intSetup.setAbilityValue("13");
        final BaseAbilityEntrySetup wisSetup = new BaseAbilityEntrySetup();
        wisSetup.setAbility("wis");
        wisSetup.setAbilityValue("8");
        final BaseAbilityEntrySetup chaSetup = new BaseAbilityEntrySetup();
        chaSetup.setAbility("cha");
        chaSetup.setAbilityValue("10");

        valeros.setBaseAbilitySetups(new ArrayList<EntitySetup>() {
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
        feat1Setup.setFeatType("improvedInitiative");

        final LevelAdvancementSetup fighter1Setup = new LevelAdvancementSetup();
        fighter1Setup.setAdvancementNumber("0");
        fighter1Setup.setClassLevel("fighter1");
        fighter1Setup.setHpAdvancement("0");
        fighter1Setup.addFeatAdvancement(feat1Setup);

        valeros.setLevelAdvancementSetups(new ArrayList<EntitySetup>() {
            {
                this.add(fighter1Setup);
            }
        });

        final PersonalizedBodySlotSetup handSetup
                = new PersonalizedBodySlotSetup();
        handSetup.setBodySlotType("primaryHand");
        handSetup.setItem("standardLongsword");
        handSetup.setIsPrimaryAttackSlot("true");
        final PersonalizedBodySlotSetup torsoSetup
                = new PersonalizedBodySlotSetup();
        torsoSetup.setBodySlotType("torso");
        torsoSetup.setItem("mwkChainmail");
        torsoSetup.setIsPrimaryAttackSlot("false");

        valeros.setBodySlotSetups(new ArrayList<EntitySetup>() {
            {
                this.add(handSetup);
                this.add(torsoSetup);
            }
        });

        return valeros;
    }

}
