package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.ExperiencePoints;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.Race;
import org.asciicerebrum.mydndgame.domain.ruleentities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.BaseAbilities;
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
    private PersonalizedBodySlots personalizedBodySlots;

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
    private Conditions conditions;

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
    public final BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        bonusSources.add(this.baseAbilities);
        bonusSources.add(this.personalizedBodySlots);
        bonusSources.add(this.conditions);
        bonusSources.add(this.levelAdvancements);
        bonusSources.add(this.race);

        return bonusSources;
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
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
     * All armor the character is currently wearing.
     *
     * @return the collection of armor.
     */
    public final Armors getArmorWorn() {
        final PersonalizedBodySlots personalizedSlots
                = this.getPersonalizedBodySlots();
        final Armors armors = new Armors();
        if (personalizedSlots == null) {
            return armors;
        }
        return (Armors) personalizedSlots.getItemsByClass(armors, Armor.class);
    }

}
