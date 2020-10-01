package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    protected Command() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }
}
