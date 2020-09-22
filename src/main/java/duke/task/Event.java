package duke.task;

public class Event extends Task {
    protected String at;
    private static final String DESCRIPTOR = "[E]";

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return DESCRIPTOR + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String encodeToFile() {
        return DESCRIPTOR + super.encodeToFile() + " | " + at;
    }
}
