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

public class Storage {

    private String filePath;
    private static final String MESSAGE_IOEXCEPTION = "Something went wrong.";
    private static final String MESSAGE_MISSING_FILEPATH = "No file name is provided."


    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createFile(Ui ui) {
        try {
            File folder = new File(filePath.substring(0,filePath.indexOf("/")));
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
            ui.showFeedbackMessage(MESSAGE_MISSING_FILEPATH,ioe.getMessage());
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException, ArrayIndexOutOfBoundsException, DukeException, NullPointerException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = TaskListDecoder.decodeTaskList(s);
        s.close();

        return tasks;
    }

    public void save(TaskList taskList, Ui ui) {
        try {
            FileWriter fw = new FileWriter(filePath);
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
            for (String output : encodedTaskList) {
                fw.write(output+"\n");
            }
            fw.close();
        } catch (IOException ioe) {
            ui.showFeedbackMessage(MESSAGE_IOEXCEPTION, ioe.getMessage());
        }
    }
}
