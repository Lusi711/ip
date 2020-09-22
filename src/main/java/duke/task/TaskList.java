package duke.task;

import java.util.ArrayList;

/**
 * Represents the entire task list. Contains the data of the task list.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the given data
     *
     * @param tasks {@code ArrayList<Task>} object that stores data from storage file
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new {@code Task} object to the task list
     *
     * @param task new task object input by user
     */
    public void addNewTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task identified using its last displayed index from the task list
     *
     * @param targetIndex last displayed index of task to delete
     * @throws IndexOutOfBoundsException if index is beyond the number of tasks in the list
     */
    public Task deleteTask(int targetIndex) {
        Task deletedTask = tasks.get(targetIndex);
        tasks.remove(deletedTask);
        return deletedTask;
    }

    /**
     * Sets up the index of the task to be marked as done
     *
     * @param targetIndex last displayed index of task to be marked as done
     * @return Task object that is marked done
     * @throws IndexOutOfBoundsException if index is beyond the number of tasks in the list
     */
    public Task markAsDone(int targetIndex) {
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
