package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.MESSAGE_EMPTY_LIST;
import static duke.ui.Messages.MESSAGE_MISSING_INDEX;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Noted. I've removed this task:";

    private int targetIndex;

    public DeleteCommand(int args) {
        targetIndex = args - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int numberOfTasks = tasks.getNumberOfTasks();
        Task deletedTask;
        try {
            deletedTask = tasks.deleteTask(targetIndex);
        } catch (IndexOutOfBoundsException ioe) {
            if (tasks.getNumberOfTasks() > 0) {
                ui.showFeedbackMessage(MESSAGE_MISSING_INDEX + " between 1 and " + numberOfTasks + ": " + (targetIndex + 1));
            } else {
                ui.showFeedbackMessage(MESSAGE_EMPTY_LIST + COMMAND_WORD);
            }
            return;
        }
        ui.showFeedbackMessage(MESSAGE_SUCCESS, "  " + deletedTask);

        if (numberOfTasks == 1) {
            ui.showFeedbackMessage("Now you have " + numberOfTasks + " task in the list.");
        } else {
            ui.showFeedbackMessage("Now you have " + numberOfTasks + " tasks in the list.");
        }
    }
}
