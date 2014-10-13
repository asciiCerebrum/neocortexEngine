package org.asciicerebrum.mydndgame.builders;

import org.asciicerebrum.mydndgame.Condition;
import org.asciicerebrum.mydndgame.ConditionSetup;
import org.asciicerebrum.mydndgame.ConditionType;
import org.asciicerebrum.mydndgame.DndCharacter;
import org.asciicerebrum.mydndgame.WorldDate;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICondition;
import org.asciicerebrum.mydndgame.interfaces.entities.IConditionType;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorldDate;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author species8472
 */
public class ConditionBuilder {

    /**
     * Attributes of how to build the condition entity.
     */
    private final ConditionSetup setup;

    /**
     * Spring application context.
     */
    private final ApplicationContext context;

    /**
     * Constructor for the condition builder.
     *
     * @param setupInput the setup object for a specific condition.
     * @param contextInput the spring context to act in.
     */
    public ConditionBuilder(final ConditionSetup setupInput,
            final ApplicationContext contextInput) {
        this.setup = setupInput;
        this.context = contextInput;
    }

    /**
     * Building the condition instance by its blueprint from the setup object.
     *
     * @return the constructed condition.
     */
    public final ICondition build() {
        ICondition condition = new Condition();

        condition.setId(this.setup.getId());

        IConditionType conditionType
                = this.context.getBean(this.setup.getName(),
                        ConditionType.class);

        condition.setConditionType(conditionType);

        IWorldDate startingDate = new WorldDate();
        startingDate.setCombatRoundNumber(this.setup.getStartingRoundNumber());
        startingDate.setCombatRoundPosition(
                this.setup.getStartingRoundPosition());

        IWorldDate expiryDate = new WorldDate();
        expiryDate.setCombatRoundNumber(this.setup.getExpiryRoundNumber());
        expiryDate.setCombatRoundPosition(this.setup.getExpiryRoundPosition());

        condition.setStartingDate(startingDate);
        condition.setExpiryDate(expiryDate);

        // retrieving characters from application context.
        ICharacter causeCharacter
                = this.context.getBean(this.setup.getCauseCharacterId(),
                        DndCharacter.class);
        condition.setCauseCharacter(causeCharacter);

        //TODO acquire affected character by the affected character id in the
        // setup and dndCharacter as class.
        //TODO register the condition in the character.
        // registering it in the context.
        //TODO do this registering also for weapon and character!
        ConfigurableListableBeanFactory beanFactory
                = ((ConfigurableApplicationContext) this.context)
                .getBeanFactory();

        beanFactory.registerSingleton(condition.getId(), condition);

        return condition;
    }

}
