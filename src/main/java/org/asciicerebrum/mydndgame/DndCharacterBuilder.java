package org.asciicerebrum.mydndgame;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IFeat;
import org.asciicerebrum.mydndgame.interfaces.entities.ILevel;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.Slotable;
import org.asciicerebrum.mydndgame.interfaces.observing.Observable;
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
    public final ICharacter build() {
        ICharacter dndCharacter = this.context.getBean(
                DND_CHARACTER_PROTOTYPE_ID, DndCharacter.class);

        // trivial setup
        dndCharacter.setRace(this.context.getBean(
                this.setup.getRace(), Race.class));
        dndCharacter.setSetup(this.setup);

        // setup of body slots by the types given by the race
        for (IBodySlotType bsType : dndCharacter.getRace()
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
            dndCharacter.getBaseAbilityMap()
                    .put(ability, abilityEntry.getValue());
        }

        // registering own observers
        for (IObserver observer : dndCharacter.getObservers()) {
            ((Observable) dndCharacter).getObservableDelegate()
                    .registerListener(observer.getHook(), observer,
                            ((Observable) dndCharacter).getObserverMap());
        }

        // advancement
        for (LevelAdvancement advance
                : this.setup.getLevelAdvancementStack()) {

            // adding class to the list
            CharacterClass chClass
                    = this.context.getBean(advance.getClassName(),
                            CharacterClass.class);
            dndCharacter.getClassList().add(chClass);

            // adding feats of class
            for (IFeat feat : chClass.getClassFeats()) {
                this.addFeat(dndCharacter, feat);
            }

            // adding additional hit points (to the list)
            dndCharacter.getHpAdditionList().add(advance.getHpAddition());

            // adding class levels as they come
            Integer classCount = dndCharacter
                    .countClassLevelsByCharacterClass(chClass);
            ILevel cLevel = chClass.getClassLevelByLevel(classCount);
            dndCharacter.getClassLevels().add(cLevel);

            /*
             Every dndCharacter has per default a baseAtk bonus instance (via
             application context). This bonus has a dynamic value. It uses a
             dynamic value provider spring bean that dynamically adds together
             all the atk boni from all the character class levels of all
             character classes. The dndCharacter gets multiple of these boni,
             each one with a separate rank - up to 5 (rank 0, ..., 4).
             */
            if (StringUtils.isNotBlank(advance.getAbilityName())) {
                Ability additionalAbility = this.context.getBean(
                        advance.getAbilityName(), Ability.class);
                dndCharacter.getAbilityAdvances().add(additionalAbility);
            }

            // adding feats
            if (StringUtils.isNotBlank(advance.getFeatName())) {
                IFeat feat = this.context.getBean(
                        advance.getFeatName(), Feat.class);
                this.addFeat(dndCharacter, feat);
            }
        }

        // adding possessions
        for (Map.Entry<String, String> posEntry
                : this.setup.getPossessionContainer().entrySet()) {

            Slotable slot = dndCharacter.getBodySlotByType(
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

    /**
     * Adding a feat to a character and registering its observers.
     *
     * @param dndCharacter the dnd Character.
     * @param feat the feat.
     */
    private void addFeat(final ICharacter dndCharacter, final IFeat feat) {
        dndCharacter.getFeats().add(feat);

        // registering feat hooks
        for (IObserver observer : feat.getObservers()) {
            ((Observable) dndCharacter).getObservableDelegate()
                    .registerListener(observer.getHook(), observer,
                            ((Observable) dndCharacter).getObserverMap());
        }
    }
}
