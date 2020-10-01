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

/**
 * Finds and lists all tasks in task list with description that contains the keyword input by user.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private final String keyword;

    /**
     * Sets the specific keyword to search for
     *
     * @param keyword for searching
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds and lists all tasks in task list with description that contains the specified keyword
     *
     * @param tasks   the TaskList that stores all the task data
     * @param ui      the interface that interacts with the user
     * @param storage the file used to store task list data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> tasksFound = getTasksWithDescriptionContainingKeyword(tasks, keyword);
        ui.showTaskList(MESSAGE_TASKS_LISTED, tasksFound);
    }

    /**
     * Retrieves all tasks in the task list with description that contains the specified keyword
     *
     * @param tasks   the task list that stores all the task data
     * @param keyword for searching
     * @return list of tasks found
     */
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

    /**
     * Retrieves a listing of every word in the description, in order.
     */
    private List<String> getWordsInDescription(String description) {
        return Arrays.asList(description.toLowerCase().split(" "));
    }
}
