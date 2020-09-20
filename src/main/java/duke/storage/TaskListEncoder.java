package duke.storage;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class TaskListEncoder {
    private static final String TODO_DESCRIPTOR = "[T]";
    private static final String DEADLINE_DESCRIPTOR = "[D]";
    private static final String EVENT_DESCRIPTOR = "[E]";

    public static List<String> encodeTaskList(TaskList toSave) {
        List<String> encodedTaskList = new ArrayList<>();
        ArrayList<Task> tasks = toSave.getTasks();
        for (Task task : tasks) {
            encodedTaskList.add(task.encodeToFile());
        }

        return encodedTaskList;
    }
}
