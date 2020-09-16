package duke.task;

public class Deadline extends Task {

    protected String by;
    private String descriptor;

    public Deadline(String description, String by) {
        super(description);
        this.descriptor = "[D]";
        this.by = by;
    }

    @Override
    public String toString() {
        return descriptor + super.toString() + " (by: " + by + ")";
    }

    public String toFile() {
        return descriptor + super.toFile() + " | " + by;
    }
}