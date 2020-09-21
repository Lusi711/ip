package duke.command.addTask;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:";

    private final Event toAdd;

    public AddEventCommand(String description, String at) {
        this.toAdd = new Event(description, at);
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
