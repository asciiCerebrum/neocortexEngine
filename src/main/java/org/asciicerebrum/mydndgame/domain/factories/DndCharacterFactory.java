package org.asciicerebrum.mydndgame.domain.factories;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.particles.ExperiencePoints;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
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
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class DndCharacterFactory implements EntityFactory<DndCharacter> {

    /**
     * The spring application context to find beans.
     */
    private ApplicationContext context;

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
    public final DndCharacter newEntity(
            final EntitySetup setup,
            final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the character "
                    + " is not complete.");
        }

        DndCharacter dndCharacter = this.getContext()
                .getBean(DndCharacter.class);

        this.trivialSetup(setup, dndCharacter);
        this.fillBodySlots(setup, dndCharacter, reassignments);
        this.fillLevelAdvancements(setup, dndCharacter, reassignments);
        this.fillStateRegistry(setup, dndCharacter, reassignments);
        this.fillBaseAbilities(setup, dndCharacter, reassignments);
        this.fillConditions(setup, dndCharacter, reassignments);

        return dndCharacter;
    }

    @Override
    public final void reAssign(final EntitySetup setup,
            final DndCharacter entity, final Reassignments reassignments) {
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
        dndCharacter.setRace(this.getContext().getBean(
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
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillBodySlots(final EntitySetup setup,
            final DndCharacter dndCharacter,
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
                this.getBodySlotFactory().newEntity(bodySlotSetup,
                        reassignments);
            }
        }
    }

    /**
     * Setting up the advancement levels of the character.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character who gets level advancements.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillLevelAdvancements(final EntitySetup setup,
            final DndCharacter dndCharacter,
            final Reassignments reassignments) {
        LevelAdvancements levelAdvancements = new LevelAdvancements();
        for (EntitySetup lvlAdvSetup
                : setup.getPropertySetups(SetupProperty.LEVEL_ADVANCEMENTS)) {
            levelAdvancements.add(this.getLevelAdvancementFactory()
                    .newEntity(lvlAdvSetup, reassignments));
        }
        dndCharacter.setLevelAdvancements(levelAdvancements);
    }

    /**
     * Setting up the state registry of the character.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character to set up.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillStateRegistry(final EntitySetup setup,
            final DndCharacter dndCharacter,
            final Reassignments reassignments) {
        dndCharacter.setStateRegistry(this.getStateRegistryFactory().newEntity(
                setup.getPropertySetup(SetupProperty.STATE_REGISTRY),
                reassignments));
    }

    /**
     * Setting up the base abilities of the dnd character.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character to set up.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillBaseAbilities(final EntitySetup setup,
            final DndCharacter dndCharacter,
            final Reassignments reassignments) {
        BaseAbilities baseAbilities = new BaseAbilities();
        for (EntitySetup abilityEntrySetup
                : setup.getPropertySetups(SetupProperty.BASE_ABILITY_ENTRIES)) {
            baseAbilities.addBaseAbilityEntry(this.getBaseAbilityEntryFactory()
                    .newEntity(abilityEntrySetup, reassignments));
        }
        dndCharacter.setBaseAbilities(baseAbilities);

    }

    /**
     * Setting up the conditions the character suffers or profits from.
     * Conditions are not mandatory.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character to set up.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillConditions(final EntitySetup setup,
            final DndCharacter dndCharacter,
            final Reassignments reassignments) {
        final Conditions conditions = new Conditions();
        final List<EntitySetup> conditionSetups
                = setup.getPropertySetups(SetupProperty.CONDITIONS);
        if (conditionSetups != null) {
            for (EntitySetup conditionSetup : conditionSetups) {
                conditions.add(this.getConditionFactory()
                        .newEntity(conditionSetup, reassignments));
            }
            dndCharacter.setConditions(conditions);
        }
    }

    /**
     * @return the context
     */
    protected final ApplicationContext getContext() {
        return context;
    }

    /**
     * @param contextInput the context to set
     */
    protected final void setContext(final ApplicationContext contextInput) {
        this.context = contextInput;
    }

    /**
     * @return the levelAdvancementFactory
     */
    protected final EntityFactory<LevelAdvancement>
            getLevelAdvancementFactory() {
        return levelAdvancementFactory;
    }

    /**
     * @param levelAdvancementFactoryIn the levelAdvancementFactory to set
     */
    protected final void setLevelAdvancementFactory(
            final EntityFactory<LevelAdvancement> levelAdvancementFactoryIn) {
        this.levelAdvancementFactory = levelAdvancementFactoryIn;
    }

    /**
     * @return the bodySlotFactory
     */
    protected final EntityFactory<PersonalizedBodySlot> getBodySlotFactory() {
        return bodySlotFactory;
    }

    /**
     * @param bodySlotFactoryInput the bodySlotFactory to set
     */
    protected final void setBodySlotFactory(
            final EntityFactory<PersonalizedBodySlot> bodySlotFactoryInput) {
        this.bodySlotFactory = bodySlotFactoryInput;
    }

    /**
     * @return the stateRegistryFactory
     */
    protected final EntityFactory<StateRegistry> getStateRegistryFactory() {
        return stateRegistryFactory;
    }

    /**
     * @param stateRegistryFactoryInput the stateRegistryFactory to set
     */
    protected final void setStateRegistryFactory(
            final EntityFactory<StateRegistry> stateRegistryFactoryInput) {
        this.stateRegistryFactory = stateRegistryFactoryInput;
    }

    /**
     * @return the baseAbilityEntryFactory
     */
    protected final EntityFactory<BaseAbilityEntry>
            getBaseAbilityEntryFactory() {
        return baseAbilityEntryFactory;
    }

    /**
     * @param baseAbilityEntryFactoryIn the baseAbilityEntryFactory to set
     */
    protected final void setBaseAbilityEntryFactory(
            final EntityFactory<BaseAbilityEntry> baseAbilityEntryFactoryIn) {
        this.baseAbilityEntryFactory = baseAbilityEntryFactoryIn;
    }

    /**
     * @return the conditionFactory
     */
    protected final EntityFactory<Condition> getConditionFactory() {
        return conditionFactory;
    }

    /**
     * @param conditionFactoryInput the conditionFactory to set
     */
    protected final void setConditionFactory(
            final EntityFactory<Condition> conditionFactoryInput) {
        this.conditionFactory = conditionFactoryInput;
    }

}
