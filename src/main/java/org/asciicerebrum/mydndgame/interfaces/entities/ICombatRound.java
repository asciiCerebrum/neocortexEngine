package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.Set;

/**
 *
 * @author species8472
 */
public interface ICombatRound {

    /**
     * @return the participants
     */
    Set<ICharacter> getParticipants();

    /**
     * @return the positions available, in correct order.
     */
    Set<String> getOrderedPositions();

    /**
     * Adds or replaces the given character. If the participant is already
     * present, its round positions is being updated.
     *
     * @param participant the participants to add
     * @param roundPosition the string defining the position in the combat
     * round, e.g. based on initiative.
     */
    void addParticipant(ICharacter participant, String roundPosition);

    /**
     * @param participant the participant in question.
     * @return the position in the current round.
     */
    String getPositionForParticipant(ICharacter participant);

    /**
     * @param roundPosition the position in question.
     * @return a set of participants with that position. An empty list if no
     * participant could be found.
     */
    Set<ICharacter> getParticipantsForPosition(String roundPosition);

    /**
     * @return the world date of the current turn in the current round.
     */
    IWorldDate getCurrentDate();

    /**
     * @return the currently active participant.
     */
    ICharacter getCurrentParticipant();

    /**
     * @param currentDate the currentDate to set
     */
    void setCurrentDate(IWorldDate currentDate);

    /**
     * The next position is made active and returned.
     */
    void moveToNextPosition();

}
