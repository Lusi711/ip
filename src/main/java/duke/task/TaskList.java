package duke.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addNewTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int targetIndex) throws IndexOutOfBoundsException {
        Task deletedTask = tasks.get(targetIndex);
        tasks.remove(deletedTask);
        return deletedTask;
    }

    public Task markAsDone(int targetIndex) throws IndexOutOfBoundsException {
        tasks.get(targetIndex).markAsDone();
        return tasks.get(targetIndex);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }
}
