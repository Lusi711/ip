package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Noted. I've removed this task:";

    private int targetIndex;

    public DeleteCommand(int args) {
        targetIndex = args - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.deleteTask(targetIndex);
        ui.showFeedbackMessage(MESSAGE_SUCCESS, "  "+deletedTask);

        int numberOfTasks = tasks.getNumberOfTasks();
        if (numberOfTasks == 1) {
            ui.showFeedbackMessage("Now you have " + numberOfTasks + " task in the list.");
        } else {
            ui.showFeedbackMessage("Now you have " + numberOfTasks + " tasks in the list.");
        }
    }
}
