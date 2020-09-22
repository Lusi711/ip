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

public class FindDateCommand extends Command {
    public static final String COMMAND_WORD = "date";

    private final String date;

    public FindDateCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> tasksFound = getTasksWithTimeEqualsDate(tasks, date);
        ui.showTaskList(MESSAGE_TASKS_LISTED, tasksFound);
    }

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
