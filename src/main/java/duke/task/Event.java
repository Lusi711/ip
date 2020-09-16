package duke.task;

public class Event extends Task {
    protected String at;
    private String descriptor;

    public Event(String description, String at) {
        super(description);
        this.descriptor = "[E]";
        this.at = at;
    }

    @Override
    public String toString() {
        return descriptor + super.toString() + " (at: " + at + ")";
    }

    public String toFile() {
        return descriptor + super.toFile() + " | " + at;
    }
}
