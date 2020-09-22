package duke.task;

/**
 * Represents an event
 */
public class Event extends Task {
    protected String at;
    private static final String DESCRIPTOR = "[E]";

    /**
     * Constructor that sets up the text description and start time of the event
     *
     * @param description text description of event
     * @param at          start time of event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return DESCRIPTOR + super.toString() + " (at: " + at + ")";
    }

    public String encodeToFile() {
        return DESCRIPTOR + super.encodeToFile() + " | " + at;
    }

    public String getDescriptor() {
        return DESCRIPTOR;
    }
}
