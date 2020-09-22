package duke.storage;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the file used to store address book data.
 */
public class Storage {

    private final String filePath;
    private static final String MESSAGE_IOEXCEPTION = "Something went wrong.";
    private static final String MESSAGE_MISSING_FILEPATH = "No file name is provided.";

    /**
     * Sets up the filepath for the storage file
     *
     * @param filePath path of the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new storage file with the input filepath
     *
     * @param ui the interface that interacts with the user
     */
    public void createFile(Ui ui) {
        try {
            File folder = new File(filePath.substring(0, filePath.indexOf("/")));
            folder.mkdirs();
        } catch (NullPointerException npe) {
            ui.showFeedbackMessage(MESSAGE_MISSING_FILEPATH, npe.getMessage());
        }
        try {
            File f = new File(filePath);
            f.createNewFile();
        } catch (NullPointerException npe) {
            ui.showFeedbackMessage(MESSAGE_IOEXCEPTION, npe.getMessage());
        } catch (IOException ioe) {
            ui.showFeedbackMessage(MESSAGE_MISSING_FILEPATH, ioe.getMessage());
        }
    }

    /**
     * Loads the {@code ArrayList<Task>} data from this storage file, and then returns it.
     * Returns an empty {@code ArrayList<Task>} if the file does not exist, or is not a regular file.
     *
     * @return ArrayList<Task> data from this storage file
     * @throws FileNotFoundException if the file is not found.
     * @throws DukeException         if there were errors reading and/or converting data from file.
     */
    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = TaskListDecoder.decodeTaskList(s);
        s.close();

        return tasks;
    }

    /**
     * Saves the {@code TaskList} data to the storage file.
     *
     * @param taskList TaskList that stores the tasks input by the user
     * @param ui       the interface that interacts with the user
     */
    public void save(TaskList taskList, Ui ui) {
        try {
            FileWriter fw = new FileWriter(filePath);
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
            for (String output : encodedTaskList) {
                fw.write(output + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            ui.showFeedbackMessage(MESSAGE_IOEXCEPTION, ioe.getMessage());
        }
    }
}
