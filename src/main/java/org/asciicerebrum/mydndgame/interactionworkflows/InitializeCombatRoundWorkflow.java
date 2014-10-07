package org.asciicerebrum.mydndgame.interactionworkflows;

import java.util.HashSet;
import java.util.Set;
import org.asciicerebrum.mydndgame.CombatRound;
import org.asciicerebrum.mydndgame.InteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;
import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;
import org.asciicerebrum.mydndgame.interfaces.managers.IDiceRollManager;

/**
 *
 * @author species8472
 */
public class InitializeCombatRoundWorkflow implements IWorkflow {

    /**
     * Format string for the elements of the round position.
     */
    private static final String ROUND_POSITION_FORMAT = "%03d";

    /**
     * Service for rolling dice.
     */
    private IDiceRollManager diceRollManager;

    /**
     * The action for the initiative roll.
     */
    private IDiceAction initiativeAction;

    /**
     * {@inheritDoc}
     */
    @Override
    public final IInteractionResponse runWorkflow(
            final IInteraction interaction) {
        return this.runWorkflow(interaction, new InteractionResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IInteractionResponse runWorkflow(
            final IInteraction interaction,
            final IInteractionResponse response) {

        ICombatRound combatRound = new CombatRound();
        response.setValue(InteractionResponseKey.COMBAT_ROUND, combatRound);

        // roll initiative on each character and save results!
        // Determination of the combat round position:
        // The throws and subsequent rethrows are saved in the following way:
        // totalInit + initBonus + reRoll1 + reRoll2 + ...
        // as concatenated strings, padded with zeroes, string format: %03d
        // here is an example:
        // 2304 (= 23 + 04) etc.
        // 210814
        // 21080519
        // 21080501
        // 1210
        // when a reroll is not necessary, nothing is appended. A string-based
        // sorting of this list gives the correct order of participants!
        // As long as two numbers are the same, a reroll-result is appended
        // until uniqueness is accomplished.
        for (ICharacter participant : interaction.getTargetCharacters()) {
            Long initBonus = participant.getInitBonus();
            Long totalInit = initBonus
                    + this.getDiceRollManager().rollDice(
                            this.getInitiativeAction());

            String roundPosition
                    = String.format(ROUND_POSITION_FORMAT, totalInit)
                    + String.format(ROUND_POSITION_FORMAT, initBonus);

            combatRound.addParticipant(participant, roundPosition);
        }

        this.resolveTies(combatRound);

        //TODO set participants on flat-footed.
        return response;
    }

    /**
     * Resolves conflicts among participants with same init roll/bonus
     * combination. When there is a tie, they have to reroll till it is
     * resolved.
     *
     * @param combatRound the combat round in which the tie occurs.
     */
    final void resolveTies(final ICombatRound combatRound) {
        // remove duplicates in roundPositions - make a reroll between tieing
        // characters (characters with equal totalInit and initbonus).
        Set<ICharacter> tieingParticipants
                = this.getTieingParticipants(combatRound);
        while (!tieingParticipants.isEmpty()) {
            for (ICharacter tieParticipant : tieingParticipants) {
                Long initReroll
                        = this.getDiceRollManager().rollDice(
                                this.getInitiativeAction());
                String newPosition
                        = combatRound.getPositionForParticipant(
                                tieParticipant)
                        + String.format(ROUND_POSITION_FORMAT, initReroll);
                combatRound.addParticipant(
                        tieParticipant, newPosition);
            }
            tieingParticipants = this.getTieingParticipants(combatRound);
        }
    }

    /**
     * Determines all participants of the current combat round which share their
     * round position with at least one other participant (they have a tie).
     *
     * @param combatRound the current round of combat.
     * @return the list of tieing participants.
     */
    final Set<ICharacter> getTieingParticipants(
            final ICombatRound combatRound) {
        Set<ICharacter> tieingParticipants = new HashSet<ICharacter>();

        for (String roundPosition
                : combatRound.getOrderedPositions()) {
            Set<ICharacter> candidates = combatRound
                    .getParticipantsForPosition(roundPosition);
            if (candidates.size() > 1) {
                tieingParticipants.addAll(candidates);
            }
        }

        return tieingParticipants;
    }

    /**
     * @return the initiativeAction
     */
    public final IDiceAction getInitiativeAction() {
        return initiativeAction;
    }

    /**
     * @param initiativeActionInput the initiativeAction to set
     */
    public final void setInitiativeAction(
            final IDiceAction initiativeActionInput) {
        this.initiativeAction = initiativeActionInput;
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

}
