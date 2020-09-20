package duke.task;

public class ToDo extends Task {

    private static final String DESCRIPTOR = "[T]";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return DESCRIPTOR + super.toString();
    }

    @Override
    public String encodeToFile() {
        return DESCRIPTOR + super.encodeToFile();
    }

    public String getDescriptor() {
        return DESCRIPTOR;
    }
}