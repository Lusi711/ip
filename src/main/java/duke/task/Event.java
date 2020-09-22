package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event
 */
public class Event extends Task {

    protected LocalDateTime at;
    private static final String DESCRIPTOR = "[E]";

    /**
     * Constructor that sets up the text description and start time of the event
     *
     * @param description text description of event
     * @param at          start time of event
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-d HHmm"));
    }

    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public String toString() {
        return DESCRIPTOR + super.toString() + " (at: " + formatTimeToString() + ")";
    }

    /**
     * Converts the deadline to a presentable string format
     */
    private String formatTimeToString() {
        return at.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a"));
    }

    @Override
    public String encodeToFile() {
        return DESCRIPTOR + super.encodeToFile() + " | " + formatTimeToString();
    }
}
