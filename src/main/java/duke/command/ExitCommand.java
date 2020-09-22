package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Saves the data from the task list into a storage file
     *
     * @param tasks   the TaskList that stores the tasks input by the user
     * @param ui      the interface that interacts with the user
     * @param storage the file used to store task list data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks, ui);
    }

    /**
     * Returns whether the user has keyed the exit command
     *
     * @param command command input by the user
     * @return whether the user has keyed the exit command
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
