package duke.task;

/**
 * Represents a task with a specific deadline
 */
public class Deadline extends Task {

    protected String by;
    private static final String DESCRIPTOR = "[D]";

    /**
     * Constructor that sets up the text description and deadline of the task
     *
     * @param description text description of task
     * @param by deadline of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /** Converts the object to a presentable string format */
    @Override
    public String toString() {
        return DESCRIPTOR + super.toString() + " (by: " + by + ")";
    }

    /** Converts the object to an encoded, readable string format */
    public String encodeToFile() {
        return DESCRIPTOR + super.encodeToFile() + " | " + by;
    }

    public String getDescriptor() {
        return DESCRIPTOR;
    }
}