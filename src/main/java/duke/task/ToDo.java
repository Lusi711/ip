package duke.task;

public class ToDo extends Task {

    private String descriptor;

    public ToDo(String description) {
        super(description);
        this.descriptor = "[T]";
    }

    @Override
    public String toString() {
        return descriptor + super.toFile();
    }
}