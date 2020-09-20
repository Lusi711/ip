package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:";

    private int targetIndex;

    public DoneCommand(int args) {
        targetIndex = args - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task doneTask = tasks.markAsDone(targetIndex);
        ui.showFeedbackMessage(MESSAGE_SUCCESS, " " + doneTask);
    }
}
