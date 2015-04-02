package org.asciicerebrum.mydndgame.domain.factories;

import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.particles.ExperiencePoints;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlots;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.BaseAbilities;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.BaseAbilityEntry;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Condition;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Conditions;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancements;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlots;
import org.asciicerebrum.mydndgame.domain.ruleentities.Race;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class DndCharacterFactory implements EntityFactory<DndCharacter> {

    /**
     * The factory for the level advancement.
     */
    private EntityFactory<LevelAdvancement> levelAdvancementFactory;

    /**
     * The factory for the state registry.
     */
    private EntityFactory<StateRegistry> stateRegistryFactory;

    /**
     * The factory for the base ability entries.
     */
    private EntityFactory<BaseAbilityEntry> baseAbilityEntryFactory;

    /**
     * The factory for the conditions.
     */
    private EntityFactory<Condition> conditionFactory;

    @Override
    public final DndCharacter newEntity(final EntitySetup setup) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the character "
                    + "is not complete.");
        }

        DndCharacter dndCharacter = ApplicationContextProvider
                .getApplicationContext().getBean(DndCharacter.class);

        this.trivialSetup(setup, dndCharacter);
        this.fillBodySlots(setup, dndCharacter);
        this.fillLevelAdvancements(setup, dndCharacter);
        this.fillStateRegistry(setup, dndCharacter);
        this.fillBaseAbilities(setup, dndCharacter);
        this.fillConditions(setup, dndCharacter);

        return dndCharacter;
    }

    /**
     * Setting up trivial parameters of dnd character.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character to set up.
     */
    final void trivialSetup(final EntitySetup setup,
            final DndCharacter dndCharacter) {
        dndCharacter.setRace(ApplicationContextProvider
                .getApplicationContext().getBean(
                        setup.getProperty(SetupProperty.RACE), Race.class));
        dndCharacter.setUniqueId(new UniqueId(
                setup.getProperty(SetupProperty.UNIQUEID)));
        dndCharacter.setCurrentStaticHp(new HitPoints(
                setup.getProperty(SetupProperty.HIT_POINTS)));
        dndCharacter.setCurrentXp(new ExperiencePoints(
                setup.getProperty(SetupProperty.EXPERIENCE_POINTS)));
        dndCharacter.setCurrentStaticHpNonLethal(new HitPoints(
                setup.getProperty(SetupProperty.HIT_POINTS_NONLETHAL)));
    }

    /**
     * Filling up the body slots of the character.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character whose body slots are to be filled.
     */
    final void fillBodySlots(final EntitySetup setup,
            final DndCharacter dndCharacter) {

        // Get the blueprint body slots from the race, wrap them into
        // personalized body slots and mass-assign the holder.
        final PersonalizedBodySlots personalizedBodySlots
                = new PersonalizedBodySlots();

        final BodySlots bluePrintSlots
                = dndCharacter.getRace().getBodySlotBluePrint();

        dndCharacter.setPersonalizedBodySlots(personalizedBodySlots);

        final List<EntitySetup> bodySlotSetups
                = setup.getPropertySetups(SetupProperty.BODY_SLOTS);
        if (bodySlotSetups != null) {
            final Iterator<BodySlot> bluePrintSlotIterator
                    = bluePrintSlots.iterator();
            while (bluePrintSlotIterator.hasNext()) {
                final BodySlot bluePrintSlot = bluePrintSlotIterator.next();

                final PersonalizedBodySlot realSlot
                        = new PersonalizedBodySlot();
                realSlot.setBodySlot(bluePrintSlot);
                personalizedBodySlots.add(realSlot);

                final EntitySetup slotSetup
                        = this.findSetupForSlot(bluePrintSlot, bodySlotSetups);

                // the new information in the body slot setup is only the item.
                // so if the list of setups contains one similar to this blue
                // print body slot, it's item is taken.
                if (slotSetup != null) {
                    realSlot.setItemId(new UniqueId(
                            slotSetup.getProperty(
                                    SetupProperty.BODY_SLOT_ITEM)));
                }
            }
        }
    }

    /**
     * Searches through the list of body slot setups if there is anything that
     * fits to the given blue print body slot.
     *
     * @param bluePrintSlot the body slot to compare the setups with.
     * @param bodySlotSetups the setups holding a potential setup for the given
     * slot.
     * @return the setup that is made for the slot.
     */
    final EntitySetup findSetupForSlot(final BodySlot bluePrintSlot,
            final List<EntitySetup> bodySlotSetups) {
        for (EntitySetup bodySlotSetup : bodySlotSetups) {
            if (bodySlotSetup.getProperty(SetupProperty.BODY_SLOT_TYPE)
                    .equalsIgnoreCase(bluePrintSlot.getBodySlotType()
                            .getId().getValue())
                    && bodySlotSetup.getProperty(
                            SetupProperty.BODY_SLOT_PRIMARY_ATTACK)
                    .equalsIgnoreCase(bluePrintSlot.getIsPrimaryAttackSlot()
                            .toString())) {
                return bodySlotSetup;
            }
        }
        return null;
    }

    /**
     * Setting up the advancement levels of the character.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character who gets level advancements.
     */
    final void fillLevelAdvancements(final EntitySetup setup,
            final DndCharacter dndCharacter) {
        LevelAdvancements levelAdvancements = new LevelAdvancements();
        for (EntitySetup lvlAdvSetup
                : setup.getPropertySetups(SetupProperty.LEVEL_ADVANCEMENTS)) {
            levelAdvancements.add(this.getLevelAdvancementFactory()
                    .newEntity(lvlAdvSetup));
        }
        dndCharacter.setLevelAdvancements(levelAdvancements);
    }

    /**
     * Setting up the state registry of the character.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character to set up.
     */
    final void fillStateRegistry(final EntitySetup setup,
            final DndCharacter dndCharacter) {

        final EntitySetup regSetup
                = setup.getPropertySetup(SetupProperty.STATE_REGISTRY);
        if (regSetup != null) {
            dndCharacter.setStateRegistry(this.getStateRegistryFactory()
                    .newEntity(regSetup));
        }
    }

    /**
     * Setting up the base abilities of the dnd character.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character to set up.
     */
    final void fillBaseAbilities(final EntitySetup setup,
            final DndCharacter dndCharacter) {
        BaseAbilities baseAbilities = new BaseAbilities();
        for (EntitySetup abilityEntrySetup
                : setup.getPropertySetups(SetupProperty.BASE_ABILITY_ENTRIES)) {
            baseAbilities.addBaseAbilityEntry(this.getBaseAbilityEntryFactory()
                    .newEntity(abilityEntrySetup));
        }
        dndCharacter.setBaseAbilities(baseAbilities);

    }

    /**
     * Setting up the conditions the character suffers or profits from.
     * Conditions are not mandatory.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character to set up.
     */
    final void fillConditions(final EntitySetup setup,
            final DndCharacter dndCharacter) {
        final Conditions conditions = new Conditions();
        final List<EntitySetup> conditionSetups
                = setup.getPropertySetups(SetupProperty.CONDITIONS);
        if (conditionSetups != null) {
            for (EntitySetup conditionSetup : conditionSetups) {
                conditions.add(this.getConditionFactory()
                        .newEntity(conditionSetup));
            }
            dndCharacter.setConditions(conditions);
        }
    }

    /**
     * @return the levelAdvancementFactory
     */
    public final EntityFactory<LevelAdvancement>
            getLevelAdvancementFactory() {
        return levelAdvancementFactory;
    }

    /**
     * @param levelAdvancementFactoryIn the levelAdvancementFactory to set
     */
    public final void setLevelAdvancementFactory(
            final EntityFactory<LevelAdvancement> levelAdvancementFactoryIn) {
        this.levelAdvancementFactory = levelAdvancementFactoryIn;
    }

    /**
     * @return the stateRegistryFactory
     */
    public final EntityFactory<StateRegistry> getStateRegistryFactory() {
        return stateRegistryFactory;
    }

    /**
     * @param stateRegistryFactoryInput the stateRegistryFactory to set
     */
    public final void setStateRegistryFactory(
            final EntityFactory<StateRegistry> stateRegistryFactoryInput) {
        this.stateRegistryFactory = stateRegistryFactoryInput;
    }

    /**
     * @return the baseAbilityEntryFactory
     */
    public final EntityFactory<BaseAbilityEntry>
            getBaseAbilityEntryFactory() {
        return baseAbilityEntryFactory;
    }

    /**
     * @param baseAbilityEntryFactoryIn the baseAbilityEntryFactory to set
     */
    public final void setBaseAbilityEntryFactory(
            final EntityFactory<BaseAbilityEntry> baseAbilityEntryFactoryIn) {
        this.baseAbilityEntryFactory = baseAbilityEntryFactoryIn;
    }

    /**
     * @return the conditionFactory
     */
    public final EntityFactory<Condition> getConditionFactory() {
        return conditionFactory;
    }

    /**
     * @param conditionFactoryInput the conditionFactory to set
     */
    public final void setConditionFactory(
            final EntityFactory<Condition> conditionFactoryInput) {
        this.conditionFactory = conditionFactoryInput;
    }

}
