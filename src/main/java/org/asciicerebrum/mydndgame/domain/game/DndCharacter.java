package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.AdvancementNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.ExperiencePoints;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatBindings;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatType;
import org.asciicerebrum.mydndgame.domain.ruleentities.Race;
import org.asciicerebrum.mydndgame.domain.ruleentities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.SpecialAbilities;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.BaseAbilities;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Condition;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Conditions;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancements;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlots;

/**
 *
 * @author species8472
 */
public class DndCharacter extends UniqueEntity implements ICharacter,
        BonusSource, ObserverSource {

    /**
     * The level advancements the character has gathered.
     */
    private LevelAdvancements levelAdvancements;

    /**
     * The base abilities.
     */
    private BaseAbilities baseAbilities;

    /**
     * The race of the character.
     */
    private Race race;

    /**
     * All the personalized body slots the character has.
     */
    private PersonalizedBodySlots personalizedBodySlots
            = new PersonalizedBodySlots();

    /**
     * The registry holding the state.
     */
    private StateRegistry stateRegistry;

    /**
     * The current static HP of the character.
     */
    private HitPoints currentStaticHp;

    /**
     * The current static non lethal HP of the character.
     */
    private HitPoints currentStaticHpNonLethal;

    /**
     * The current XP of the character.
     */
    private ExperiencePoints currentXp;

    /**
     * All the conditions the character is currently in.
     */
    private Conditions conditions = new Conditions();

    /**
     * The special abilities given by the prototype dnd character. All dnd
     * characters will have those and they are injected by the application
     * context.
     */
    private SpecialAbilities prototypeSpecialAbilities = new SpecialAbilities();

    /**
     * @return the levelAdvancements
     */
    public final LevelAdvancements getLevelAdvancements() {
        return levelAdvancements;
    }

    /**
     * @param levelAdvancementsInput the levelAdvancements to set
     */
    public final void setLevelAdvancements(
            final LevelAdvancements levelAdvancementsInput) {
        this.levelAdvancements = levelAdvancementsInput;
    }

    /**
     * @return the baseAbilities
     */
    public final BaseAbilities getBaseAbilities() {
        return baseAbilities;
    }

    /**
     * @param baseAbilitiesInput the baseAbilities to set
     */
    public final void setBaseAbilities(
            final BaseAbilities baseAbilitiesInput) {
        this.baseAbilities = baseAbilitiesInput;
    }

    /**
     * @return the race
     */
    public final Race getRace() {
        return race;
    }

    /**
     * @param raceInput the race to set
     */
    public final void setRace(final Race raceInput) {
        this.race = raceInput;
    }

    /**
     * @return the bodySlots
     */
    public final PersonalizedBodySlots getPersonalizedBodySlots() {
        return this.personalizedBodySlots;
    }

    /**
     * @param bodySlotsInput the bodySlots to set
     */
    public final void setPersonalizedBodySlots(
            final PersonalizedBodySlots bodySlotsInput) {
        this.personalizedBodySlots = bodySlotsInput;
    }

    /**
     * @return the stateRegistry
     */
    public final StateRegistry getStateRegistry() {
        return stateRegistry;
    }

    /**
     * @param stateRegistryInput the stateRegistry to set
     */
    public final void setStateRegistry(final StateRegistry stateRegistryInput) {
        this.stateRegistry = stateRegistryInput;
    }

    /**
     * @return the currentHp
     */
    public final HitPoints getCurrentStaticHp() {
        return currentStaticHp;
    }

    /**
     * @param currentStaticHpInput the currentHp to set
     */
    public final void setCurrentStaticHp(final HitPoints currentStaticHpInput) {
        this.currentStaticHp = currentStaticHpInput;
    }

    /**
     * @return the currentHpNonLethal
     */
    public final HitPoints getCurrentStaticHpNonLethal() {
        return currentStaticHpNonLethal;
    }

    /**
     * @param currentStaticHpNonLethalInput the currentHpNonLethal to set
     */
    public final void setCurrentStaticHpNonLethal(
            final HitPoints currentStaticHpNonLethalInput) {
        this.currentStaticHpNonLethal = currentStaticHpNonLethalInput;
    }

    /**
     * @return the currentXp
     */
    public final ExperiencePoints getCurrentXp() {
        return currentXp;
    }

    /**
     * @param currentXpInput the currentXp to set
     */
    public final void setCurrentXp(final ExperiencePoints currentXpInput) {
        this.currentXp = currentXpInput;
    }

    /**
     * @return the conditions
     */
    public final Conditions getConditions() {
        return conditions;
    }

    /**
     * @param conditionsInput the conditions to set
     */
    public final void setConditions(final Conditions conditionsInput) {
        this.conditions = conditionsInput;
    }

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni contextBoni = new ContextBoni();

        contextBoni.add(this.baseAbilities.getBoni(context, resolver));
        contextBoni.add(this.personalizedBodySlots.getBoni(context, resolver));
        contextBoni.add(this.conditions.getBoni(context, resolver));
        contextBoni.add(this.levelAdvancements.getBoni(context, resolver));
        contextBoni.add(this.race.getBoni(context, resolver));
        contextBoni.add(this.prototypeSpecialAbilities.getBoni(
                context, resolver));

        return contextBoni;
    }

    /**
     * Retrieves the unmodified base size of the character - defined by race.
     *
     * @return the base size category.
     */
    public final SizeCategory getBaseSize() {
        return this.race.getSize();
    }

    /**
     * Adds a condition to the character. Checks if the conditions collection is
     * already set.
     *
     * @param condition the condition to add.
     */
    public final void addCondition(final Condition condition) {
        this.getConditions().add(condition);
    }

    /**
     * Calculates the unmodified base hit points of a given dnd character by
     * gathering them from each level advancement. This value is not modified by
     * the con modifier!
     *
     * @return the base hit points.
     */
    public final HitPoints getBaseMaxHp() {
        return this.levelAdvancements.summateHpAdvancements();
    }

    /**
     * Calculates the total number of level advancements. This equals the level
     * of the character.
     *
     * @return the total number of class levels.
     */
    public final AdvancementNumber getTotalClassLevel() {
        return this.levelAdvancements.countLevelAdvancements();
    }

    /**
     * Calculates the unmodified base attack boni of this dnd character
     * instance.
     *
     * @return the base attack boni.
     */
    public final BonusValueTuple getBaseAtkBoni() {
        return this.levelAdvancements.summateBaseAtkBoni();
    }

    /**
     * Retrieves the feat bindings by the given feat type from multiple sources.
     *
     * @param featType the feat type the bindings are needed for.
     * @return the collection of feat bindings.
     */
    public final FeatBindings getFeatBindingsByFeatType(
            final FeatType featType) {

        final FeatBindings featBindings = new FeatBindings();

        featBindings.add(this.levelAdvancements
                .getFeatBindingsByFeatType(featType));
        //TODO add further sources of feats here. E.g. class levels.

        return featBindings;
    }

    /**
     * @return the prototypeSpecialAbilities
     */
    public final SpecialAbilities getPrototypeSpecialAbilities() {
        return prototypeSpecialAbilities;
    }

    /**
     * @param prototypeSpecialAbilitiesInput the prototypeSpecialAbilities to
     * set
     */
    public final void setPrototypeSpecialAbilities(
            final SpecialAbilities prototypeSpecialAbilitiesInput) {
        this.prototypeSpecialAbilities = prototypeSpecialAbilitiesInput;
    }

}
