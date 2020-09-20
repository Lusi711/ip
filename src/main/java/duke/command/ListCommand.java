package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

import static duke.ui.Messages.MESSAGE_DISPLAY_LIST;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }
}
