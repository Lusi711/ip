package duke.task;

public class ToDo extends Task {

    private String descriptor;

    public ToDo(String description) {
        super(description);
        this.descriptor = "[T]";
    }

    @Override
    public String toString() {
        return descriptor + super.toString();
    }

    @Override
    public String toFile() {
        return descriptor + super.toFile();
    }
}