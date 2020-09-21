package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.MESSAGE_EMPTY_LIST;
import static duke.ui.Messages.MESSAGE_MISSING_INDEX;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:";

    private int targetIndex;

    public DoneCommand(int args) {
        targetIndex = args - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task doneTask;
        try {
            doneTask = tasks.markAsDone(targetIndex);
        } catch (IndexOutOfBoundsException e) {
            if (tasks.getNumberOfTasks() > 0) {
                ui.showFeedbackMessage(MESSAGE_MISSING_INDEX + " between 1 and " + tasks.getNumberOfTasks() + ": " + (targetIndex + 1));
            } else {
                ui.showFeedbackMessage(MESSAGE_EMPTY_LIST + COMMAND_WORD);
            }
            return;
        }
        ui.showFeedbackMessage(MESSAGE_SUCCESS, " " + doneTask);
    }
}
