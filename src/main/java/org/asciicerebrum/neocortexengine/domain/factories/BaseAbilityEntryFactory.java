package org.asciicerebrum.neocortexengine.domain.factories;

import org.asciicerebrum.neocortexengine.domain.ruleentities.Ability;
import org.asciicerebrum.neocortexengine.domain.core.particles.AbilityScore;
import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.domain.setup.SetupProperty;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.BaseAbilityEntry;
import org.asciicerebrum.neocortexengine.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class BaseAbilityEntryFactory
        implements EntityFactory<BaseAbilityEntry> {

    @Override
    public final BaseAbilityEntry newEntity(final EntitySetup setup) {
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

}
