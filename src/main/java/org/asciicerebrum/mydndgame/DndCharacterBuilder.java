package org.asciicerebrum.mydndgame;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class DndCharacterBuilder {

    /**
     * The spring bean id of the DND character prototype.
     */
    private static final String DND_CHARACTER_PROTOTYPE_ID = "dndCharacter";
    /**
     * The setup needed for character creation.
     */
    private final CharacterSetup setup;
    /**
     * The spring application context to find beans.
     */
    private final ApplicationContext context;

    /**
     *
     * @param setupInput the setup object with information of how to create the
     * character.
     * @param contextInput the spring application context to find the beans.
     */
    public DndCharacterBuilder(final CharacterSetup setupInput,
            final ApplicationContext contextInput) {
        this.setup = setupInput;
        this.context = contextInput;
    }

    /**
     *
     * @return the newly created character.
     */
    public final DndCharacter build() {
        DndCharacter dndCharacter = this.context.getBean(
                DND_CHARACTER_PROTOTYPE_ID, DndCharacter.class);

        // trivial setup
        dndCharacter.setRace(this.context.getBean(
                this.setup.getRace(), Race.class));
        dndCharacter.setSetup(this.setup);

        // setup of body slots by the types given by the race
        for (BodySlotType bsType : dndCharacter.getRace()
                .getProvidedBodySlotTypes()) {
            BodySlot bs = new BodySlot();
            bs.setBodySlotType(bsType);
            bs.setHolder(dndCharacter);
            dndCharacter.getBodySlots().add(bs);
        }

        // ability map from character creation (advancement in this area
        // follows later)
        for (Map.Entry<String, Long> abilityEntry
                : this.setup.getBaseAbilityMap().entrySet()) {
            Ability ability = this.context.getBean(abilityEntry.getKey(),
                    Ability.class);
            dndCharacter.getAbilityMap().put(ability, abilityEntry.getValue());
        }

        // advancement
        for (LevelAdvancement advance
                : this.setup.getLevelAdvancementStack()) {

            // adding class to the list
            CharacterClass chClass
                    = this.context.getBean(advance.getClassName(),
                            CharacterClass.class);
            dndCharacter.getClassList().add(chClass);

            // adding additional hit points (to the list)
            dndCharacter.getHpAdditionList().add(advance.getHpAddition());

            // adding class levels as they come
            Integer classCount = dndCharacter
                    .countClassLevelsByCharacterClass(chClass);
            ClassLevel cLevel = chClass.getClassLevelByLevel(classCount + 1);
            if (cLevel != null) {
                dndCharacter.getClassLevels().add(cLevel);
            }

            // merge baseAtk boni
            //TODO make this calculated in realtime
            //dndCharacter.mergeBaseAtkBoni(chClass, cLevel);
            // ability increment with level advancement
            //TODO save ability advancements differntly to calculate the
            // effective score in realtime!!! this calculation is then done in
            // the dndCharacter class.
            /*
             if (StringUtils.isNotBlank(advance.getAbilityName())) {
             Ability additionalAbility
             = this.context.getBean(
             advance.getAbilityName(), Ability.class);
             dndCharacter.getAbilityMap().put(additionalAbility,
             dndCharacter.getAbilityMap()
             .get(additionalAbility) + 1);
             }
             */
            // adding feats
            if (StringUtils.isNotBlank(advance.getFeatName())) {
                Feat feat = this.context.getBean(
                        advance.getFeatName(), Feat.class);
                dndCharacter.getFeats().add(feat);
            }
        }

        // adding possessions
        for (Map.Entry<String, String> posEntry
                : this.setup.getPossessionContainer().entrySet()) {

            BodySlot slot = dndCharacter.getBodySlotByType(
                    this.context.getBean(posEntry.getValue(),
                            BodySlotType.class));
            InventoryItem item = this.context.getBean(posEntry.getKey(),
                    InventoryItem.class);
            if (slot != null) {
                slot.setItem(item);
                //TODO throw exception if body slot is null
            }
        }

        return dndCharacter;
    }
}
