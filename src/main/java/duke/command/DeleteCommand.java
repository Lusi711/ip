package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.MESSAGE_EMPTY_LIST;
import static duke.ui.Messages.MESSAGE_MISSING_INDEX;

/**
 * Deletes a task identified using its last displayed index from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "\tNoted. I've removed this task:";

    private final int targetIndex;

    /**
     * Sets up the index of the task to delete
     *
     * @param index index of the task to delete
     */
    public DeleteCommand(int index) {
        targetIndex = index - 1;
    }

    /**
     * Deletes a task identified using its last displayed index and prints a confirmatory message
     *
     * @param tasks   the TaskList that stores the tasks input by the user
     * @param ui      the interface that interacts with the user
     * @param storage the file used to store task list data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask;
        try {
            deletedTask = tasks.deleteTask(targetIndex);
        } catch (IndexOutOfBoundsException ioe) {
            if (tasks.getNumberOfTasks() > 0) {
                ui.showFeedbackMessage(MESSAGE_MISSING_INDEX + " between 1 and " + tasks.getNumberOfTasks() + ": " + (targetIndex + 1));
            } else {
                ui.showFeedbackMessage(MESSAGE_EMPTY_LIST + COMMAND_WORD);
            }
            return;
        }
        ui.showFeedbackMessage(MESSAGE_SUCCESS, "\t  " + deletedTask);
        int numberOfTasks = tasks.getNumberOfTasks();
        if (numberOfTasks == 1) {
            ui.showFeedbackMessage("\tNow you have " + numberOfTasks + " task in the list.");
        } else {
            ui.showFeedbackMessage("\tNow you have " + numberOfTasks + " tasks in the list.");
        }
    }
}
