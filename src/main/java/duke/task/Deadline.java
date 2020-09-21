package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    private static final String DESCRIPTOR = "[D]";

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-d HHmm"));
    }

    @Override
    public String toString() {
        return DESCRIPTOR + super.toString() + " (by: " + formatTimeToString() + ")";
    }

    private String formatTimeToString() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a"));
    }

    public String encodeToFile() {
        return DESCRIPTOR + super.encodeToFile() + " | " + formatTimeToString();
    }

    public String getDescriptor() {
        return DESCRIPTOR;
    }
}