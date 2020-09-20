package duke.task;

public class Deadline extends Task {

    protected String by;
    private static final String DESCRIPTOR = "[D]";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return DESCRIPTOR + super.toString() + " (by: " + by + ")";
    }

    public String encodeToFile() {
        return DESCRIPTOR + super.encodeToFile() + " | " + by;
    }

    public String getDescriptor() {
        return DESCRIPTOR;
    }
}