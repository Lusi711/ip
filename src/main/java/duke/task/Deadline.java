package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    private static final String DESCRIPTOR = "[D]";
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-d HHmm";
    private static final String DATETIME_ENCODED_FORMAT = "MMM d yyyy, h:mm a";

    /**
     * Constructor that sets up the text description and deadline of the task
     *
     * @param description text description of task
     * @param by          deadline of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT));
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return DESCRIPTOR + super.toString() + " (by: " + formatTimeToString() + ")";
    }

    /**
     * Converts the deadline to a presentable string format
     */
    private String formatTimeToString() {
        return by.format(DateTimeFormatter.ofPattern(DATETIME_ENCODED_FORMAT));
    }
    /**
     * Converts the object to an encoded, readable string format
     */
    @Override
    public String encodeToFile() {
        return DESCRIPTOR + super.encodeToFile() + " | " + formatTimeToString();
    }

}