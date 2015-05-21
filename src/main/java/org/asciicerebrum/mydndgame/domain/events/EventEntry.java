package org.asciicerebrum.mydndgame.domain.events;

import org.asciicerebrum.mydndgame.domain.core.particles.EventFact;
import org.asciicerebrum.mydndgame.domain.core.particles.EventFacts;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueIds;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;

/**
 * Examples of Events:<br>
 *
 * Harsk gains condition flat-footed.<br>
 * Valeros loses condition flat-footed.<br>
 * Merisiel attacks Valeros with rapier.<br>
 * Seoni casts Sleep on goblin A.<br>
 *
 * @author species8472
 */
public class EventEntry {

    /**
     * The source of the event.
     */
    private UniqueId who;

    /**
     * The targets of the event.
     */
    private UniqueIds whom;

    /**
     * The date of the event.
     */
    private WorldDate when;

    /**
     * What kind of event is happening. E.g. Gaining condition.
     */
    private EventType eventType;

    /**
     * What is the center of the event. E.g. the flat-footed condition.
     */
    private UniqueId what;

    /**
     * The details of the event in an ordered list.
     */
    private final EventFacts how = new EventFacts();

    /**
     * Constructing an event entry with the needed event type.
     *
     * @param eventTypeInput the event type.
     */
    public EventEntry(final EventType eventTypeInput) {
        this.eventType = eventTypeInput;
    }

    /**
     * @return the who
     */
    public final UniqueId getWho() {
        return who;
    }

    /**
     * @param whoInput the who to set
     */
    public final void setWho(final UniqueId whoInput) {
        this.who = whoInput;
    }

    /**
     * @return the whom
     */
    public final UniqueIds getWhom() {
        return whom;
    }

    /**
     * @param whomInput the whom to set
     */
    public final void setWhom(final UniqueIds whomInput) {
        this.whom = whomInput;
    }

    /**
     * @return the when
     */
    public final WorldDate getWhen() {
        return when;
    }

    /**
     * @param whenInput the when to set
     */
    public final void setWhen(final WorldDate whenInput) {
        this.when = whenInput;
    }

    /**
     * @return the how
     */
    public final EventFacts getHow() {
        return how;
    }

    /**
     * Adding a single event fact to the how-collection.
     *
     * @param eventFact the fact to add.
     */
    public final void addEventFact(final EventFact eventFact) {
        this.how.add(eventFact);
    }

    /**
     * @return the eventType
     */
    public final EventType getEventType() {
        return eventType;
    }

    /**
     * @param eventTypeInput the eventType to set
     */
    public final void setEventType(final EventType eventTypeInput) {
        this.eventType = eventTypeInput;
    }

    /**
     * @return the what
     */
    public final UniqueId getWhat() {
        return what;
    }

    /**
     * @param whatInput the what to set
     */
    public final void setWhat(final UniqueId whatInput) {
        this.what = whatInput;
    }

}
