package duke.storage;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Encodes the {@code TaskList} object into a data file for storage.
 */
public class TaskListEncoder {
    private static final String TODO_DESCRIPTOR = "[T]";
    private static final String DEADLINE_DESCRIPTOR = "[D]";
    private static final String EVENT_DESCRIPTOR = "[E]";

    /**
     * Encodes all the {@code Task} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */
    public static List<String> encodeTaskList(TaskList toSave) {
        List<String> encodedTaskList = new ArrayList<>();
        ArrayList<Task> tasks = toSave.getTasks();
        for (Task task : tasks) {
            encodedTaskList.add(task.encodeToFile());
        }

        return encodedTaskList;
    }
}
