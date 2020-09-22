package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command{
    public final String feedbackToUser;

    /**
     * Class constructor that receives the feedback message to output to the user
     *
     * @param feedbackToUser feedback message to output to the user
     */
    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Prints the feedback message based on the type of error in the command
     *
     * @param tasks the TaskList that stores the tasks input by the user
     * @param ui the interface that interacts with the user
     * @param storage the file used to store task list data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFeedbackMessage(feedbackToUser);
    }
}
