package org.asciicerebrum.mydndgame.domain.factories;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.particles.ExperiencePoints;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
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
     * The factory for the body slots.
     */
    private EntityFactory<PersonalizedBodySlot> bodySlotFactory;

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
    public final DndCharacter newEntity(final EntitySetup setup,
            final Campaign campaign, final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the character "
                    + " is not complete.");
        }

        DndCharacter dndCharacter = ApplicationContextProvider
                .getApplicationContext().getBean(DndCharacter.class);

        this.trivialSetup(setup, dndCharacter);
        this.fillBodySlots(setup, dndCharacter, campaign, reassignments);
        this.fillLevelAdvancements(setup, dndCharacter, campaign,
                reassignments);
        this.fillStateRegistry(setup, dndCharacter, campaign, reassignments);
        this.fillBaseAbilities(setup, dndCharacter, campaign, reassignments);
        this.fillConditions(setup, dndCharacter, campaign, reassignments);

        return dndCharacter;
    }

    @Override
    public final void reAssign(final EntitySetup setup,
            final DndCharacter entity, final Campaign campaign,
            final Reassignments reassignments) {
        // nothing to do here.
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
     * @param campaign the campaign as the central entity map.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillBodySlots(final EntitySetup setup,
            final DndCharacter dndCharacter, final Campaign campaign,
            final Reassignments reassignments) {

        // Get the blueprint body slots from the race, wrap them into
        // personalized body slots and mass-assign the holder.
        final PersonalizedBodySlots personalizedBodySlots
                = new PersonalizedBodySlots();
        personalizedBodySlots.wrapSlots(
                dndCharacter.getRace().getBodySlotBluePrint());
        personalizedBodySlots.setHolder(dndCharacter);

        dndCharacter.setPersonalizedBodySlots(personalizedBodySlots);

        final List<EntitySetup> bodySlotSetups
                = setup.getPropertySetups(SetupProperty.BODY_SLOTS);
        if (bodySlotSetups != null) {
            for (EntitySetup bodySlotSetup : bodySlotSetups) {

                bodySlotSetup.setProperty(SetupProperty.BODY_SLOT_HOLDER,
                        dndCharacter.getUniqueId().getValue());

                // The body slot factory creates an example body slot from the
                // setup. This example is used to get the first personal body
                // slot from the collection created above that resembles its
                // attributes.
                this.getBodySlotFactory().newEntity(bodySlotSetup, campaign,
                        reassignments);
            }
        }
    }

    /**
     * Setting up the advancement levels of the character.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character who gets level advancements.
     * @param campaign the campaign as the central entity map.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillLevelAdvancements(final EntitySetup setup,
            final DndCharacter dndCharacter, final Campaign campaign,
            final Reassignments reassignments) {
        LevelAdvancements levelAdvancements = new LevelAdvancements();
        for (EntitySetup lvlAdvSetup
                : setup.getPropertySetups(SetupProperty.LEVEL_ADVANCEMENTS)) {
            levelAdvancements.add(this.getLevelAdvancementFactory()
                    .newEntity(lvlAdvSetup, campaign, reassignments));
        }
        dndCharacter.setLevelAdvancements(levelAdvancements);
    }

    /**
     * Setting up the state registry of the character.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character to set up.
     * @param campaign the campaign as the central entity map.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillStateRegistry(final EntitySetup setup,
            final DndCharacter dndCharacter, final Campaign campaign,
            final Reassignments reassignments) {
        dndCharacter.setStateRegistry(this.getStateRegistryFactory().newEntity(
                setup.getPropertySetup(SetupProperty.STATE_REGISTRY),
                campaign, reassignments));
    }

    /**
     * Setting up the base abilities of the dnd character.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character to set up.
     * @param campaign the campaign as the central entity map.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillBaseAbilities(final EntitySetup setup,
            final DndCharacter dndCharacter, final Campaign campaign,
            final Reassignments reassignments) {
        BaseAbilities baseAbilities = new BaseAbilities();
        for (EntitySetup abilityEntrySetup
                : setup.getPropertySetups(SetupProperty.BASE_ABILITY_ENTRIES)) {
            baseAbilities.addBaseAbilityEntry(this.getBaseAbilityEntryFactory()
                    .newEntity(abilityEntrySetup, campaign, reassignments));
        }
        dndCharacter.setBaseAbilities(baseAbilities);

    }

    /**
     * Setting up the conditions the character suffers or profits from.
     * Conditions are not mandatory.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character to set up.
     * @param campaign the campaign as the central entity map.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillConditions(final EntitySetup setup,
            final DndCharacter dndCharacter, final Campaign campaign,
            final Reassignments reassignments) {
        final Conditions conditions = new Conditions();
        final List<EntitySetup> conditionSetups
                = setup.getPropertySetups(SetupProperty.CONDITIONS);
        if (conditionSetups != null) {
            for (EntitySetup conditionSetup : conditionSetups) {
                conditions.add(this.getConditionFactory()
                        .newEntity(conditionSetup, campaign, reassignments));
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
     * @return the bodySlotFactory
     */
    public final EntityFactory<PersonalizedBodySlot> getBodySlotFactory() {
        return bodySlotFactory;
    }

    /**
     * @param bodySlotFactoryInput the bodySlotFactory to set
     */
    public final void setBodySlotFactory(
            final EntityFactory<PersonalizedBodySlot> bodySlotFactoryInput) {
        this.bodySlotFactory = bodySlotFactoryInput;
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
