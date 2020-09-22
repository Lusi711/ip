package duke.task;

/**
 * Represents a task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor that sets up the text description of the task
     *
     * @param description text description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Converts the object to a presentable string format
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Converts the object to an encoded, readable string format
     */
    public String encodeToFile() {
        return getStatusIcon() + " " + description;
    }
}