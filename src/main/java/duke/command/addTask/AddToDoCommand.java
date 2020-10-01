package duke.command.addTask;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Adds a task to the task list.
 */
public class AddToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_SUCCESS = "\tGot it. I've added this task:";

    private final ToDo toAdd;

    /**
     * Class constructor to set up a task
     *
     * @param description description of the task
     */
    public AddToDoCommand(String description) {
        this.toAdd = new ToDo(description);
    }

    /**
     * Adds a task to the task list and prints a confirmatory message
     *
     * @param tasks   the TaskList that stores the tasks input by the user
     * @param ui      the interface that interacts with the user
     * @param storage the file used to store task list data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int numberOfTasks = tasks.getNumberOfTasks();
        tasks.addNewTask(toAdd);
        ui.showFeedbackMessage(MESSAGE_SUCCESS, "\t  " + toAdd);
        if (numberOfTasks == 1) {
            ui.showFeedbackMessage("\tNow you have " + numberOfTasks + " task in the list.");
        } else {
            ui.showFeedbackMessage("\tNow you have " + numberOfTasks + " tasks in the list.");
        }
    }
}
