package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueIds;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.DndCharacters;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.mydndgame.domain.ruleentities.ConditionType;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Condition;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Conditions;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.RollResult;
import org.asciicerebrum.mydndgame.mechanics.managers.RollResultManager;
import org.asciicerebrum.mydndgame.services.application.ConditionApplicationService;
import org.asciicerebrum.mydndgame.services.context.EntityPoolService;
import org.asciicerebrum.mydndgame.services.statistics.InitiativeCalculationService;

/**
 *
 * @author species8472
 */
public class InitializeCombatRoundWorkflow implements IWorkflow {

    /**
     * Service for rolling dice.
     */
    private RollResultManager rollResultManager;

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
     * The entity pool service.
     */
    private EntityPoolService entityPoolService;

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
        this.rollInitiative(interaction.getTargetCharacters().iterator(),
                combatRound, interaction.getCampaign());
        this.resolveTies(combatRound, interaction.getCampaign());
        combatRound.setCurrentDate(null);

        this.applyFlatFooted(combatRound);
    }

    /**
     * Set participants on flat-footed.
     *
     * @param combatRound the combat round of this encounter.
     */
    final void applyFlatFooted(final CombatRound combatRound) {
        Iterator<UniqueId> participantIterator
                = combatRound.participantsIterator();
        while (participantIterator.hasNext()) {
            final UniqueId participantId = participantIterator.next();

            // the first participant is not flat footed!
            if (participantId.equals(combatRound.getCurrentParticipantId())) {
                continue;
            }

            final WorldDate expiryDate = new WorldDate(
                    combatRound.getNextParticipationDate(participantId));
            final WorldDate startingDate = combatRound.getCurrentDate();

            final Condition flatFooted = new Condition();
            flatFooted.setConditionType(this.getFlatFootedType());
            flatFooted.setExpiryDate(expiryDate);
            flatFooted.setStartingDate(startingDate);

            this.getConditionApplicationService().applyCondition(
                    (DndCharacter) this.getEntityPoolService()
                    .getEntityById(participantId),
                    new Conditions(flatFooted));
        }
    }

    /**
     * Let every participant roll for initiative.
     *
     * @param participantIterator the iterator of the list of participants.
     * @param combatRound the combat round of this encounter.
     * @param campaign the campaign for the combat round.
     */
    final void rollInitiative(final Iterator<DndCharacter> participantIterator,
            final CombatRound combatRound, final Campaign campaign) {
        while (participantIterator.hasNext()) {
            final DndCharacter participant = participantIterator.next();

            final BonusValue initBonus = this.getInitiativeCalculationService()
                    .calcInitBonus(participant);

            final RollResult initRollResult = this.getRollResultManager()
                    .retrieveRollResult(initBonus,
                            this.getInitiativeAction(), null,
                            participant, null,
                            //TODO this is problematic because the combat round
                            // might not yet have a current date when it has
                            // just started and everybody rolls initiative!
                            combatRound.getCurrentDate(),
                            campaign);

            CombatRoundPosition roundPosition
                    = new CombatRoundPosition(initRollResult.calcTotalResult(),
                            initBonus);

            combatRound.addParticipant(participant.getUniqueId(),
                    roundPosition);
        }
    }

    /**
     * Resolves conflicts among participants with same init roll/bonus
     * combination. When there is a tie, they have to reroll till it is
     * resolved.
     *
     * @param combatRound the combat round in which the tie occurs.
     * @param campaign the campaign for the combat round.
     */
    final void resolveTies(final CombatRound combatRound,
            final Campaign campaign) {
        // remove duplicates in roundPositions - make a reroll between tieing
        // characters (characters with equal totalInit and initbonus).
        DndCharacters tieingParticipants
                = this.getTieingParticipants(combatRound);
        while (tieingParticipants.hasEntries()) {
            tieingParticipants = this.tieResolutionStep(
                    tieingParticipants, combatRound, campaign);
        }
    }

    /**
     * A single step in the tie resolving process.
     *
     * @param tieingParticipants the participants with the same init bonus
     * combination.
     * @param combatRound the combat round in which the tie occurs.
     * @param campaign the campaign for the combat round.
     * @return the participants with changed combat round position.
     */
    final DndCharacters tieResolutionStep(
            final DndCharacters tieingParticipants,
            final CombatRound combatRound, final Campaign campaign) {
        final Iterator<DndCharacter> participantIterator
                = tieingParticipants.iterator();

        while (participantIterator.hasNext()) {
            final DndCharacter tieParticipant = participantIterator.next();

            final RollResult initRerollResult = this.getRollResultManager()
                    .retrieveRollResult(new BonusValue(),
                            this.getInitiativeAction(), null,
                            tieParticipant, null,
                            combatRound.getCurrentDate(),
                            campaign);

            final CombatRoundPosition newPosition
                    = new CombatRoundPosition(
                            combatRound.getPositionForParticipant(
                                    tieParticipant.getUniqueId()),
                            initRerollResult.calcTotalResult());
            combatRound.addParticipant(tieParticipant.getUniqueId(),
                    newPosition);
        }
        return this.getTieingParticipants(combatRound);
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

            final UniqueIds candidateIds = combatRound
                    .getParticipantsForPosition(roundPosition);
            if (candidateIds.hasMultipleEntries()) {
                final DndCharacters candidates = new DndCharacters();
                candidates.merge(this.getEntityPoolService()
                        .getEntitiesByIds(candidateIds));

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

    /**
     * @return the initiativeAction
     */
    public final DiceAction getInitiativeAction() {
        return initiativeAction;
    }

    /**
     * @return the flatFootedType
     */
    public final ConditionType getFlatFootedType() {
        return flatFootedType;
    }

    /**
     * @return the initiativeCalculationService
     */
    public final InitiativeCalculationService
            getInitiativeCalculationService() {
        return initiativeCalculationService;
    }

    /**
     * @return the conditionApplicationService
     */
    public final ConditionApplicationService getConditionApplicationService() {
        return conditionApplicationService;
    }

    /**
     * @return the entityPoolService
     */
    public final EntityPoolService getEntityPoolService() {
        return entityPoolService;
    }

    /**
     * @param entityPoolServiceInput the entityPoolService to set
     */
    public final void setEntityPoolService(
            final EntityPoolService entityPoolServiceInput) {
        this.entityPoolService = entityPoolServiceInput;
    }

    /**
     * @return the rollResultManager
     */
    public final RollResultManager getRollResultManager() {
        return rollResultManager;
    }

    /**
     * @param rollResultManagerInput the rollResultManager to set
     */
    public final void setRollResultManager(
            final RollResultManager rollResultManagerInput) {
        this.rollResultManager = rollResultManagerInput;
    }

}
