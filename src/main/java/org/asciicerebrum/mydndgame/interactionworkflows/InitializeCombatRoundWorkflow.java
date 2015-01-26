package org.asciicerebrum.mydndgame.interactionworkflows;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.attribution.ConditionType;
import org.asciicerebrum.mydndgame.domain.gameentities.CombatRound;
import org.asciicerebrum.mydndgame.domain.gameentities.Condition;
import org.asciicerebrum.mydndgame.domain.core.attribution.WorldDate;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.gameentities.Conditions;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacters;
import org.asciicerebrum.mydndgame.domain.gameentities.prototypes.DiceAction;
import org.asciicerebrum.mydndgame.domain.transfer.Interaction;
import org.asciicerebrum.mydndgame.managers.DiceRollManager;
import org.asciicerebrum.mydndgame.services.application.ConditionApplicationService;
import org.asciicerebrum.mydndgame.services.statistics.InitiativeCalculationService;

/**
 *
 * @author species8472
 */
public class InitializeCombatRoundWorkflow implements IWorkflow {

    /**
     * Service for rolling dice.
     */
    private DiceRollManager diceRollManager;

    /**
     * The action for the initiative roll.
     */
    private DiceAction initiativeAction;

    /**
     * The condition type of beeing flat footed.
     */
    private ConditionType flatFootedType;

    /**
     * Calculating the initiative boni.
     */
    private InitiativeCalculationService initiativeCalculationService;

    /**
     * Apply conditionsn like flatfooted, etc.
     */
    private ConditionApplicationService conditionApplicationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void runWorkflow(
            final Interaction interaction) {

        final CombatRound combatRound = new CombatRound();
        interaction.setCombatRound(combatRound);

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
        Iterator<DndCharacter> participantIterator
                = interaction.getTargetCharacters().iterator();
        while (participantIterator.hasNext()) {
            final DndCharacter participant = participantIterator.next();

            final BonusValue initBonus = this.initiativeCalculationService
                    .calcInitBonus(participant);

            final DiceRoll totalInit = this.diceRollManager
                    .rollDice(this.initiativeAction).add(initBonus);

            CombatRoundPosition roundPosition
                    = new CombatRoundPosition(totalInit, initBonus);

            combatRound.addParticipant(participant, roundPosition);
        }

        this.resolveTies(combatRound);

        if (this.flatFootedType == null) {
            return;
        }

        // Set participants on flat-footed.
        participantIterator = combatRound.participantsIterator();
        while (participantIterator.hasNext()) {
            final DndCharacter participant = participantIterator.next();

            final WorldDate expiryDate = new WorldDate(
                    combatRound.getNextParticipationDate(participant));
            final WorldDate startingDate = combatRound.getCurrentDate();

            final Condition flatFooted = new Condition();
            flatFooted.setConditionType(this.flatFootedType);
            flatFooted.setExpiryDate(expiryDate);
            flatFooted.setStartingDate(startingDate);

            this.conditionApplicationService.applyCondition(participant,
                    new Conditions(flatFooted));
        }
    }

    /**
     * Resolves conflicts among participants with same init roll/bonus
     * combination. When there is a tie, they have to reroll till it is
     * resolved.
     *
     * @param combatRound the combat round in which the tie occurs.
     */
    final void resolveTies(final CombatRound combatRound) {
        // remove duplicates in roundPositions - make a reroll between tieing
        // characters (characters with equal totalInit and initbonus).
        DndCharacters tieingParticipants
                = this.getTieingParticipants(combatRound);
        while (tieingParticipants.hasEntries()) {

            final Iterator<DndCharacter> participantIterator
                    = tieingParticipants.iterator();

            while (participantIterator.hasNext()) {
                final DndCharacter tieParticipant = participantIterator.next();

                final DiceRoll initReroll
                        = this.diceRollManager.rollDice(
                                this.initiativeAction);
                final CombatRoundPosition newPosition
                        = new CombatRoundPosition(
                                combatRound.getPositionForParticipant(
                                        tieParticipant), initReroll);
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
    final DndCharacters getTieingParticipants(
            final CombatRound combatRound) {
        final DndCharacters tieingParticipants = new DndCharacters();

        final Iterator<CombatRoundPosition> positionIterator
                = combatRound.getOrderedPositions();
        while (positionIterator.hasNext()) {
            final CombatRoundPosition roundPosition = positionIterator.next();
            
            final DndCharacters candidates = combatRound
                    .getParticipantsForPosition(roundPosition);
            if (candidates.hasMultipleEntries()) {
                tieingParticipants.addDndCharacters(candidates);
            }
        }

        return tieingParticipants;
    }

    /**
     * @param initiativeActionInput the initiativeAction to set
     */
    public final void setInitiativeAction(
            final DiceAction initiativeActionInput) {
        this.initiativeAction = initiativeActionInput;
    }

    /**
     * @param diceRollManagerInput the diceRollManager to set
     */
    public final void setDiceRollManager(
            final DiceRollManager diceRollManagerInput) {
        this.diceRollManager = diceRollManagerInput;
    }

    /**
     * @param flatFootedTypeInput the flatFootedType to set
     */
    public final void setFlatFootedType(
            final ConditionType flatFootedTypeInput) {
        this.flatFootedType = flatFootedTypeInput;
    }

    /**
     * @param initCalcServiceInput the initiativeCalculationService to set
     */
    public final void setInitiativeCalculationService(
            final InitiativeCalculationService initCalcServiceInput) {
        this.initiativeCalculationService = initCalcServiceInput;
    }

    /**
     * @param conditionApplServiceInput the conditionApplicationService to set
     */
    public final void setConditionApplicationService(
            final ConditionApplicationService conditionApplServiceInput) {
        this.conditionApplicationService = conditionApplServiceInput;
    }

}
