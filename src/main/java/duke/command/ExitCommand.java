package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    private static final String MESSAGE_IOEXCEPTION = "Something went wrong.";

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks, ui);
    }
}
