package duke.command.addTask;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class AddToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:";

    private final ToDo toAdd;

    public AddToDoCommand(String description) {
        this.toAdd = new ToDo(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addNewTask(toAdd);
        int numberOfTasks = tasks.getNumberOfTasks();
        ui.showFeedbackMessage(MESSAGE_SUCCESS, "  " + toAdd);
        if (numberOfTasks == 1) {
            ui.showFeedbackMessage("Now you have " + numberOfTasks + " task in the list.");
        } else {
            ui.showFeedbackMessage("Now you have " + numberOfTasks + " tasks in the list.");
        }
    }
}
