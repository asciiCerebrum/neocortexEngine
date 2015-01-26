package org.asciicerebrum.mydndgame.domain.gameentities.setup;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;

/**
 *
 * @author species8472
 */
public class CharacterSetup extends AbstractEntitySetup<DndCharacter> {

    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.RACE, SetupProperty.UNIQUEID,
                SetupProperty.HIT_POINTS, SetupProperty.EXPERIENCE_POINTS,
                SetupProperty.BODY_SLOTS, SetupProperty.LEVEL_ADVANCEMENTS,
                SetupProperty.BASE_ABILITY_ENTRIES};

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    public void setRace(final String race) {
        this.singleProperties.put(SetupProperty.RACE, race);
    }

    public void setUniqueId(final String uniqueId) {
        this.singleProperties.put(SetupProperty.UNIQUEID, uniqueId);
    }

    public void setCurrentHp(final String currentHp) {
        this.singleProperties.put(SetupProperty.HIT_POINTS, currentHp);
    }

    public void setCurrentHpNonLethal(final String currentHpNonLethal) {
        this.singleProperties.put(SetupProperty.HIT_POINTS_NONLETHAL,
                currentHpNonLethal);
    }

    public void setCurrentXp(final String currentXp) {
        this.singleProperties.put(SetupProperty.EXPERIENCE_POINTS, currentXp);
    }

    public void setBodySlotSetups(final List<EntitySetup> bodySlotSetups) {
        this.listSetup.put(SetupProperty.BODY_SLOTS, bodySlotSetups);
    }

    public void setLevelAdvancementSetups(
            final List<EntitySetup> lvlAdvSetups) {
        this.listSetup.put(SetupProperty.LEVEL_ADVANCEMENTS, lvlAdvSetups);
    }

    public void setStateRegistrySetup(final EntitySetup registrySetup) {
        this.singleSetup.put(SetupProperty.STATE_REGISTRY, registrySetup);
    }

    public void setBaseAbilitySetups(
            final List<EntitySetup> baseAbilitySetups) {
        this.listSetup.put(SetupProperty.BASE_ABILITY_ENTRIES,
                baseAbilitySetups);
    }

    public void setConditionSetups(
            final List<EntitySetup> conditionSetups) {
        this.listSetup.put(SetupProperty.CONDITIONS, conditionSetups);
    }

}
