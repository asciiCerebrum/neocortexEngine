package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.core.particles.AbilityScore;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.BaseAbilityEntry;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class BaseAbilityEntryFactory
        implements EntityFactory<BaseAbilityEntry> {

    /**
     * The spring application context for retrieving the prototype beans.
     */
    private ApplicationContext context;

    @Override
    public final BaseAbilityEntry newEntity(final EntitySetup setup,
            final Reassignments reassignments) {
        BaseAbilityEntry entry = new BaseAbilityEntry();

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the base ability "
                    + " entry is not complete.");
        }

        entry.setAbility(this.getContext().getBean(setup.getProperty(
                SetupProperty.BASE_ABILITY), Ability.class));
        entry.setAbilityValue(new AbilityScore(setup.getProperty(
                SetupProperty.BASE_ABILITY_VALUE)));

        return entry;
    }

    @Override
    public void reAssign(final EntitySetup setup,
            final BaseAbilityEntry entity, final Reassignments reassignments) {
        // nothing to do here.
    }

    /**
     * @param contextInput the context to set
     */
    public final void setContext(final ApplicationContext contextInput) {
        this.context = contextInput;
    }

    /**
     * @return the context
     */
    public final ApplicationContext getContext() {
        return context;
    }

}
