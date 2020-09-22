package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command
     *
     * @param tasks the TaskList that stores the tasks input by the user
     * @param ui the interface that interacts with the user
     * @param storage the file used to store task list data
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) { }
}
