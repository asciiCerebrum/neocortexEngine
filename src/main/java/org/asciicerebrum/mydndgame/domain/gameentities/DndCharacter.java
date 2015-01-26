package org.asciicerebrum.mydndgame.domain.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.BaseAbilities;
import org.asciicerebrum.mydndgame.domain.core.attribution.SizeCategory;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.observers.Observers;
import org.asciicerebrum.mydndgame.domain.core.particles.ExperiencePoints;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.facades.gameentities.CharacterServiceFacade;

/**
 *
 * @author species8472
 */
public class DndCharacter extends UniqueEntity implements BonusSource,
        ObserverSource {

    private CharacterServiceFacade characterServiceFacade;

    private LevelAdvancements levelAdvancements;

    private BaseAbilities baseAbilities;

    private Race race;

    private BodySlots bodySlots;

    private StateRegistry stateRegistry;

    private HitPoints currentStaticHp;

    private HitPoints currentStaticHpNonLethal;

    private ExperiencePoints currentXp;

    private Conditions conditions;

    /**
     * @return the characterServiceFacade
     */
    public final CharacterServiceFacade getCharacterServiceFacade() {
        return characterServiceFacade;
    }

    /**
     * @param characterServiceFacadeInput the characterServiceFacade to set
     */
    public final void setCharacterServiceFacade(
            final CharacterServiceFacade characterServiceFacadeInput) {
        this.characterServiceFacade = characterServiceFacadeInput;
    }

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
    public final BodySlots getBodySlots() {
        return bodySlots;
    }

    /**
     * @param bodySlotsInput the bodySlots to set
     */
    public final void setBodySlots(final BodySlots bodySlotsInput) {
        this.bodySlots = bodySlotsInput;
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
        bonusSources.add(this.bodySlots);
        bonusSources.add(this.conditions);
        bonusSources.add(this.levelAdvancements);
        bonusSources.add(this.race);

        return bonusSources;
    }

    @Override
    public Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public Observers getObservers() {
        return Observers.EMPTY_OBSERVERS;
    }

    @Override
    public ObserverSources getObserverSources() {
        final ObserverSources observerSources = new ObserverSources();

        observerSources.add(this.baseAbilities);
        observerSources.add(this.bodySlots);
        observerSources.add(this.conditions);
        observerSources.add(this.levelAdvancements);
        observerSources.add(this.race);

        return observerSources;
    }

    public final Armors getWieldedArmor() {
        return this.getBodySlots().getArmorWorn();
    }

    public final SizeCategory getSize() {
        return this.characterServiceFacade.getSize(this.race.getSize(), this);
    }

    public final Armors getArmorWorn() {
        return this.bodySlots.getArmorWorn();
    }

}