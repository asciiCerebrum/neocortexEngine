package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.particles.ExperiencePoints;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.rules.composition.BaseAbilities;
import org.asciicerebrum.mydndgame.domain.rules.composition.Conditions;
import org.asciicerebrum.mydndgame.domain.rules.composition.LevelAdvancements;
import org.asciicerebrum.mydndgame.domain.rules.composition.PersonalizedBodySlots;
import org.asciicerebrum.mydndgame.domain.rules.Race;
import org.asciicerebrum.mydndgame.domain.rules.SizeCategory;

/**
 *
 * @author species8472
 */
public class DndCharacter extends UniqueEntity implements BonusSource,
        ObserverSource {

    private LevelAdvancements levelAdvancements;

    private BaseAbilities baseAbilities;

    private Race race;

    private PersonalizedBodySlots personalizedBodySlots;

    private StateRegistry stateRegistry;

    private HitPoints currentStaticHp;

    private HitPoints currentStaticHpNonLethal;

    private ExperiencePoints currentXp;

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
    public BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        bonusSources.add(this.baseAbilities);
        bonusSources.add(this.personalizedBodySlots);
        bonusSources.add(this.conditions);
        bonusSources.add(this.levelAdvancements);
        bonusSources.add(this.race);

        return bonusSources;
    }

    @Override
    public Boni getBoni() {
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

    public final Armors getArmorWorn() {
        return (Armors) this.getPersonalizedBodySlots().getItemsByClass(
                new Armors(), Armor.class);
    }

}
