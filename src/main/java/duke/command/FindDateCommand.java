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
 * Finds and lists all deadlines and events in task list occurring on a specific date input by the user.
 */
public class FindDateCommand extends Command {

    public static final String COMMAND_WORD = "get";

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
     * Finds and lists all deadlines and events in task list which occur on {@code date}.
     *
     * @param tasks   the TaskList that stores all the task data
     * @param ui      the interface that interacts with the user
     * @param storage the file used to store task list data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> tasksFound = getTasksWithTimeEqualsDate(tasks, date);
        ui.showTaskList(MESSAGE_TASKS_LISTED, tasksFound);
    }

    /**
     * Retrieves all deadlines/events in the task list which occur on specific {@code date}
     *
     * @param tasks the TaskList that stores all the task data
     * @param date  for searching
     * @return the list of tasks found
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
