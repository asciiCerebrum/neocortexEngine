package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.core.particles.ExperiencePoints;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry;
import org.asciicerebrum.mydndgame.domain.game.dndcharacter.DndCharacter;
import org.asciicerebrum.mydndgame.domain.rules.composition.BaseAbilities;
import org.asciicerebrum.mydndgame.domain.rules.composition.BaseAbilityEntry;
import org.asciicerebrum.mydndgame.domain.rules.composition.Condition;
import org.asciicerebrum.mydndgame.domain.rules.composition.Conditions;
import org.asciicerebrum.mydndgame.domain.rules.composition.LevelAdvancement;
import org.asciicerebrum.mydndgame.domain.rules.composition.LevelAdvancements;
import org.asciicerebrum.mydndgame.domain.rules.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.rules.composition.PersonalizedBodySlot.Facet;
import org.asciicerebrum.mydndgame.domain.rules.composition.PersonalizedBodySlots;
import org.asciicerebrum.mydndgame.domain.rules.Race;
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

    private EntityFactory<LevelAdvancement> levelAdvancementFactory;

    private EntityFactory<PersonalizedBodySlot> bodySlotFactory;

    private EntityFactory<StateRegistry> stateRegistryFactory;

    private EntityFactory<BaseAbilityEntry> baseAbilityEntryFactory;

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

    public void reAssign(final EntitySetup setup, final DndCharacter entity) {
        // nothing to do here.
    }

    void trivialSetup(final EntitySetup setup,
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

    void fillBodySlots(final EntitySetup setup,
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

    void fillLevelAdvancements(final EntitySetup setup,
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

    void fillStateRegistry(final EntitySetup setup,
            final DndCharacter dndCharacter,
            final Reassignments reassignments) {
        dndCharacter.setStateRegistry(this.stateRegistryFactory.newEntity(
                setup.getPropertySetup(SetupProperty.STATE_REGISTRY),
                reassignments));
    }

    void fillBaseAbilities(final EntitySetup setup,
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

    void fillConditions(final EntitySetup setup,
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
