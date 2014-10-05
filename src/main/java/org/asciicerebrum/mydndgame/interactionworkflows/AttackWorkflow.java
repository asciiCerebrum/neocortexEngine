package org.asciicerebrum.mydndgame.interactionworkflows;

import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;
import org.asciicerebrum.mydndgame.interfaces.managers.IDiceRollManager;

/**
 *
 * @author species8472
 */
public class AttackWorkflow implements IWorkflow {

    /**
     * The dice action for attacking. It contains the dice for this interaction.
     */
    private IDiceAction attackAction;

    /**
     * Service for rolling dice.
     */
    private IDiceRollManager diceRollManager;

    /**
     * Roll results that leads to automatic failure.
     */
    private Long autoFailureRoll;

    /**
     * Roll result that leads to automatic success.
     */
    private Long autoSuccessRoll;

    /**
     * Determination of damage effects and applying them on the character.
     */
    private IWorkflow damageWorkflow;

    /**
     * Key for saving the criticalness of the attack.
     */
    private InteractionResponseKey attackCriticalKey;

    /**
     * {@inheritDoc} Performs an attack on the given target.
     */
    @Override
    public final IInteractionResponse runWorkflow(
            final IInteraction interaction,
            final IInteractionResponse response) {

        // make an attack roll and add attack bonus
        Long atkRollResult
                = this.getDiceRollManager().rollDice(this.getAttackAction());

        // consider automatic failure and success
        if (atkRollResult <= this.getAutoFailureRoll()) {
            return response;
        }

        Long sourceAtkBonus = interaction.getTriggeringCharacter()
                .getAtkBoni().get(0);
        Long totalAtkResult = atkRollResult + sourceAtkBonus;

        Long targetAc = interaction.getTargetCharacters().get(0).getAc();

        // check against target's ac
        // not able to surpass foe's ac and also no auto success
        if (totalAtkResult < targetAc
                && atkRollResult < this.getAutoSuccessRoll()) {
            return response;
        }

        Boolean isCritical = this.determineCritical(atkRollResult,
                sourceAtkBonus, targetAc, interaction);

        // this is the result of the attack roll that is needed by the damage
        // roll.
        if (this.getAttackCriticalKey() != null) {
            response.setValue(this.getAttackCriticalKey(), isCritical);
        }

        if (this.getDamageWorkflow() != null) {
            return this.getDamageWorkflow().runWorkflow(interaction, response);
        }
        return response;
    }

    /**
     * Determines if an attack is critical or not.
     *
     * @param atkRollResult the result of the first attack roll.
     * @param sourceAtkBonus the bonus that is added to the attack roll.
     * @param targetAc the armor class of the target character.
     * @param interaction the interaction object.
     * @return the criticallity of the attack.
     */
    final Boolean determineCritical(final Long atkRollResult,
            final Long sourceAtkBonus, final Long targetAc,
            final IInteraction interaction) {

        ISituationContext sourceContext = interaction.getTriggeringCharacter()
                .getSituationContext();
        IWeapon sourceWeapon = (IWeapon) interaction.getTriggeringCharacter()
                .getBodySlotByType(sourceContext.getBodySlotType()).getItem();

        // when you are here you have hit the enemy!!!
        // it could be critical
        Boolean isThreat
                = atkRollResult >= sourceWeapon.getCriticalMinimumLevel();

        Boolean isCritical = Boolean.FALSE;
        if (isThreat) {
            Long secondAtkRollResult
                    = this.getDiceRollManager()
                    .rollDice(this.getAttackAction());

            isCritical
                    = (secondAtkRollResult + sourceAtkBonus) >= targetAc
                    || secondAtkRollResult >= this.getAutoSuccessRoll();
        }
        return isCritical;
    }

    /**
     * @return the attackAction
     */
    public final IDiceAction getAttackAction() {
        return attackAction;
    }

    /**
     * @param attackActionInput the attackAction to set
     */
    public final void setAttackAction(final IDiceAction attackActionInput) {
        this.attackAction = attackActionInput;
    }

    /**
     * @return the diceRollManager
     */
    public final IDiceRollManager getDiceRollManager() {
        return diceRollManager;
    }

    /**
     * @param diceRollManagerInput the diceRollManager to set
     */
    public final void setDiceRollManager(
            final IDiceRollManager diceRollManagerInput) {
        this.diceRollManager = diceRollManagerInput;
    }

    /**
     * @return the autoFailureRoll
     */
    public final Long getAutoFailureRoll() {
        return autoFailureRoll;
    }

    /**
     * @param autoFailureRollInput the autoFailureRoll to set
     */
    public final void setAutoFailureRoll(final Long autoFailureRollInput) {
        this.autoFailureRoll = autoFailureRollInput;
    }

    /**
     * @return the autoSuccessRoll
     */
    public final Long getAutoSuccessRoll() {
        return autoSuccessRoll;
    }

    /**
     * @param autoSuccessRollInput the autoSuccessRoll to set
     */
    public final void setAutoSuccessRoll(final Long autoSuccessRollInput) {
        this.autoSuccessRoll = autoSuccessRollInput;
    }

    /**
     * @return the damageWorkflow
     */
    public final IWorkflow getDamageWorkflow() {
        return damageWorkflow;
    }

    /**
     * @param damageWorkflowInput the damageWorkflow to set
     */
    public final void setDamageWorkflow(
            final IWorkflow damageWorkflowInput) {
        this.damageWorkflow = damageWorkflowInput;
    }

    /**
     * @return the attackCriticalKey
     */
    public final InteractionResponseKey getAttackCriticalKey() {
        return attackCriticalKey;
    }

    /**
     * @param attackCriticalKeyInput the attackCriticalKey to set
     */
    public final void setAttackCriticalKey(
            final InteractionResponseKey attackCriticalKeyInput) {
        this.attackCriticalKey = attackCriticalKeyInput;
    }

}
