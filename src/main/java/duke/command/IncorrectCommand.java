package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class IncorrectCommand extends Command {
    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFeedbackMessage(feedbackToUser);
    }
}
