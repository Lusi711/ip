package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.MESSAGE_EMPTY_LIST;
import static duke.ui.Messages.MESSAGE_MISSING_INDEX;

/**
 * Indicates a task identified using its last displayed index as done
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_SUCCESS = "\tNice! I've marked this task as done:";

    private final int targetIndex;

    /**
     * Sets up the index of the task to be marked as done
     *
     * @param index index of the task to be marked as done
     */
    public DoneCommand(int index) {
        targetIndex = index - 1;
    }

    /**
     * Marks a task identified using its last displayed index as done and prints a confirmatory message
     *
     * @param tasks   the TaskList that stores the tasks input by the user
     * @param ui      the interface that interacts with the user
     * @param storage the file used to store task list data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int numberOfTasks = tasks.getNumberOfTasks();
        try {
            Task doneTask = tasks.markAsDone(targetIndex);
            ui.showFeedbackMessage(MESSAGE_SUCCESS, "\t " + doneTask);
        } catch (IndexOutOfBoundsException e) {
            if (tasks.getNumberOfTasks() > 0) {
                ui.showFeedbackMessage(MESSAGE_MISSING_INDEX + " between 1 and " + numberOfTasks + ": " + (targetIndex + 1));
            } else {
                ui.showFeedbackMessage(MESSAGE_EMPTY_LIST + COMMAND_WORD);
            }
        }
    }
}
