package org.asciicerebrum.mydndgame.domain.factories;

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
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot.Facet;
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

        DndCharacter dndCharacter = this.context.getBean(DndCharacter.class);

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
            final DndCharacter entity) {
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
        dndCharacter.setRace(this.context.getBean(
                setup.getProperty(SetupProperty.RACE), Race.class));
        dndCharacter.setUniqueId(new UniqueId(
                setup.getProperty(SetupProperty.UNIQUEID)));
        dndCharacter.setCurrentStaticHp(new HitPoints(
                setup.getProperty(SetupProperty.HIT_POINTS)));
        dndCharacter.setCurrentStaticHpNonLethal(new HitPoints(
                setup.getProperty(SetupProperty.HIT_POINTS_NONLETHAL)));
        dndCharacter.setCurrentXp(new ExperiencePoints(
                setup.getProperty(SetupProperty.EXPERIENCE_POINTS)));
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

        final PersonalizedBodySlots personalizedBodySlots
                = new PersonalizedBodySlots();
        personalizedBodySlots.wrapSlots(
                dndCharacter.getRace().getBodySlotBluePrint());
        personalizedBodySlots.setHolder(dndCharacter);

        for (EntitySetup bodySlotSetup
                : setup.getPropertySetups(SetupProperty.BODY_SLOTS)) {

            final PersonalizedBodySlot bluePrintBodySlot
                    = this.bodySlotFactory.newEntity(bodySlotSetup,
                            reassignments);
            // final an empty slot (facet.item) of the same type and primary-
            // attack-slot-value
            final PersonalizedBodySlot realBodySlot
                    = personalizedBodySlots.findFirstSimilar(bluePrintBodySlot,
                            Facet.BODY_SLOT_TYPE, Facet.PRIMARY_ATTACK_SLOT,
                            Facet.ITEM);

            boolean itemUnresolved
                    = ((PersonalizedBodySlotFactory) this.bodySlotFactory)
                    .assignItem(bodySlotSetup, realBodySlot);

            if (itemUnresolved) {
                reassignments.addEntry(this.bodySlotFactory, bodySlotSetup,
                        realBodySlot);
            }
        }

        dndCharacter.setPersonalizedBodySlots(personalizedBodySlots);
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
            levelAdvancements.add(
                    this.levelAdvancementFactory.newEntity(lvlAdvSetup,
                            reassignments));
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
        dndCharacter.setStateRegistry(this.stateRegistryFactory.newEntity(
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
            baseAbilities.addBaseAbilityEntry(
                    this.baseAbilityEntryFactory.newEntity(abilityEntrySetup,
                            reassignments));
        }
        dndCharacter.setBaseAbilities(baseAbilities);

    }

    /**
     * Setting up the conditions the character suffers or profits from.
     *
     * @param setup the dnd character setup.
     * @param dndCharacter the character to set up.
     * @param reassignments the reassignment object for resolving unfound
     * objects.
     */
    final void fillConditions(final EntitySetup setup,
            final DndCharacter dndCharacter,
            final Reassignments reassignments) {
        Conditions conditions = new Conditions();
        for (EntitySetup conditionSetup
                : setup.getPropertySetups(SetupProperty.CONDITIONS)) {
            conditions.add(
                    this.conditionFactory.newEntity(conditionSetup,
                            reassignments));
        }
        dndCharacter.setConditions(conditions);
    }

}
