package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static duke.ui.Messages.MESSAGE_TASKS_LISTED;

/**
 * Finds and lists all tasks in task list occurring on specific date input by user
 */
public class FindDateCommand extends Command {
    public static final String COMMAND_WORD = "date";

    private final String date;

    /**
     * Sets the specific date to search for
     *
     * @param date for searching
     */
    public FindDateCommand(String date) {
        this.date = date;
    }

    /**
     * Finds and lists all tasks in task list that occur on specific date
     *
     * @param tasks   the TaskList that stores the tasks input by the user
     * @param ui      the interface that interacts with the user
     * @param storage the file used to store task list data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> tasksFound = getTasksWithTimeEqualsDate(tasks, date);
        ui.showTaskList(MESSAGE_TASKS_LISTED, tasksFound);
    }

    /**
     * Retrieves all tasks in the task list that occur on specific date
     *
     * @param tasks the task list that stores all the task data
     * @param date for searching
     *
     * @return list of tasks found
     */
    private ArrayList<Task> getTasksWithTimeEqualsDate(TaskList tasks, String date) {
        String formattedTime;
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task instanceof Deadline) {
                formattedTime = ((Deadline) task).getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } else if (task instanceof Event) {
                formattedTime = ((Event) task).getAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } else {
                continue;
            }
            if (formattedTime.equals(date)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}
