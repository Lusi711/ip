package duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.descriptor = "[D]";
        this.by = by;
    }

    @Override
    public String toString() {
        return descriptor + super.toString() + " (by: " + by + ")";
    }
}