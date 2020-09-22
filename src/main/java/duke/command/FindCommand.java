package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static duke.ui.Messages.MESSAGE_TASKS_LISTED;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> tasksFound = getTasksWithDescriptionContainingKeyword(tasks, keyword);
        ui.showTaskList(MESSAGE_TASKS_LISTED, tasksFound);
    }

    private ArrayList<Task> getTasksWithDescriptionContainingKeyword(TaskList tasks, String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            Set<String> wordsInDescription = new HashSet<>(getWordsInDescription(task.getDescription()));
            if (wordsInDescription.contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    private List<String> getWordsInDescription(String description) {
        return Arrays.asList(description.split(" "));
    }
}
