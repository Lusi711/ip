package duke.command.addTask;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a task with a specific deadline to the task list.
 */
public class AddDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_SUCCESS = "\tGot it. I've added this task:";

    private final Deadline toAdd;

    /**
     * Class constructor to set up a deadline
     *
     * @param description description of the task
     * @param by          deadline of the task
     */
    public AddDeadlineCommand(String description, String by) {
        this.toAdd = new Deadline(description, by);
    }

    /**
     * Adds a task with a specific deadline to the task list and prints a confirmatory message
     *
     * @param tasks   the TaskList that stores the tasks input by the user
     * @param ui      the interface that interacts with the user
     * @param storage the file used to store task list data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addNewTask(toAdd);
        int numberOfTasks = tasks.getNumberOfTasks();
        ui.showFeedbackMessage(MESSAGE_SUCCESS, "\t  " + toAdd);
        if (numberOfTasks == 1) {
            ui.showFeedbackMessage("\tNow you have " + numberOfTasks + " task in the list.");
        } else {
            ui.showFeedbackMessage("\tNow you have " + numberOfTasks + " tasks in the list.");
        }
    }
}
