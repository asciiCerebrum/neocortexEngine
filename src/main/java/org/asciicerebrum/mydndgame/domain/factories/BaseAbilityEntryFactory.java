package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.core.particles.AbilityScore;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.BaseAbilityEntry;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class BaseAbilityEntryFactory
        implements EntityFactory<BaseAbilityEntry> {

    @Override
    public final BaseAbilityEntry newEntity(final EntitySetup setup,
            final Campaign campaign) {
        BaseAbilityEntry entry = new BaseAbilityEntry();

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the base ability "
                    + " entry is not complete.");
        }

        entry.setAbility(ApplicationContextProvider
                .getApplicationContext().getBean(setup.getProperty(
                                SetupProperty.BASE_ABILITY), Ability.class));
        entry.setAbilityValue(new AbilityScore(setup.getProperty(
                SetupProperty.BASE_ABILITY_VALUE)));

        return entry;
    }

    @Override
    public void reAssign(final EntitySetup setup,
            final BaseAbilityEntry entity, final Campaign campaign) {
        // nothing to do here.
    }

}
