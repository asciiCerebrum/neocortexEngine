package org.asciicerebrum.mydndgame.domain.setup;

import java.util.List;

/**
 *
 * @author species8472
 */
public class CharacterSetup extends AbstractEntitySetup {

    /**
     * Defines the required properties.
     */
    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.RACE, SetupProperty.UNIQUEID,
                SetupProperty.HIT_POINTS, SetupProperty.EXPERIENCE_POINTS,
                SetupProperty.BODY_SLOTS, SetupProperty.LEVEL_ADVANCEMENTS,
                SetupProperty.BASE_ABILITY_ENTRIES};

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    /**
     * @param race the race.
     */
    public final void setRace(final String race) {
        this.getSingleProperties().put(SetupProperty.RACE, race);
    }

    /**
     * @param uniqueId the unique id.
     */
    public final void setUniqueId(final String uniqueId) {
        this.getSingleProperties().put(SetupProperty.UNIQUEID, uniqueId);
    }

    /**
     * @param currentHp the current hp.
     */
    public final void setCurrentHp(final String currentHp) {
        this.getSingleProperties().put(SetupProperty.HIT_POINTS, currentHp);
    }

    /**
     * @param currentHpNonLethal the current non-lethal hp.
     */
    public final void setCurrentHpNonLethal(final String currentHpNonLethal) {
        this.getSingleProperties().put(SetupProperty.HIT_POINTS_NONLETHAL,
                currentHpNonLethal);
    }

    /**
     * @param currentXp the current xp.
     */
    public final void setCurrentXp(final String currentXp) {
        this.getSingleProperties()
                .put(SetupProperty.EXPERIENCE_POINTS, currentXp);
    }

    /**
     * @param bodySlotSetups the body slot setups.
     */
    public final void setBodySlotSetups(
            final List<EntitySetup> bodySlotSetups) {
        this.getListSetup().put(SetupProperty.BODY_SLOTS, bodySlotSetups);
    }

    /**
     * @param lvlAdvSetups the level advancement setups.
     */
    public final void setLevelAdvancementSetups(
            final List<EntitySetup> lvlAdvSetups) {
        this.getListSetup().put(SetupProperty.LEVEL_ADVANCEMENTS, lvlAdvSetups);
    }

    /**
     * @param registrySetup the state registry setup.
     */
    public final void setStateRegistrySetup(final EntitySetup registrySetup) {
        this.getSingleSetup().put(SetupProperty.STATE_REGISTRY, registrySetup);
    }

    /**
     * @param baseAbilitySetups the base ability setups.
     */
    public final void setBaseAbilitySetups(
            final List<EntitySetup> baseAbilitySetups) {
        this.getListSetup().put(SetupProperty.BASE_ABILITY_ENTRIES,
                baseAbilitySetups);
    }

    /**
     * @param conditionSetups the conditions setups.
     */
    public final void setConditionSetups(
            final List<EntitySetup> conditionSetups) {
        this.getListSetup().put(SetupProperty.CONDITIONS, conditionSetups);
    }

}
